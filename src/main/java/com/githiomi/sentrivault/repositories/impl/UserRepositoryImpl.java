package com.githiomi.sentrivault.repositories.impl;

import com.githiomi.sentrivault.data.mapper.UserRowMapper;
import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.exceptions.CustomException;
import com.githiomi.sentrivault.repositories.RoleRepository;
import com.githiomi.sentrivault.repositories.UserRepository;
import com.githiomi.sentrivault.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.githiomi.sentrivault.data.model.User.increaseUserCounter;
import static com.githiomi.sentrivault.data.utils.Queries.*;
import static org.springframework.http.HttpStatus.*;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

@Slf4j
@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    //
    private static final int ID_TYPE = 1;
    private static final int USERNAME_TYPE = 2;

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllUsers() {
        return this.jdbcTemplate.query(GET_ALL_USERS_QUERY, new UserRowMapper());
    }

    @Override
    public User findUserById(String id) {

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("user_id", id);
        return getUserFromDB(ID_TYPE, id, GET_USER_BY_ID_QUERY, params);

    }

    @Override
    public User findUserByUsername(String username) {

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("username", username);
        return getUserFromDB(USERNAME_TYPE, username, GET_USER_BY_USERNAME_QUERY, params);

    }

    private User getUserFromDB(int type, String identifier, String query, MapSqlParameterSource params) {

        try {
            return jdbcTemplate.queryForObject(query, params, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {

            String error = type == ID_TYPE
                    ? "No user found in the database with ID: " + identifier + " -> " + e.getMessage()
                    : "No user found with username " + identifier + " -> " + e.getMessage();

            log.error(error);
            throw new CustomException(NOT_FOUND, error);
        }

    }

    @Override
    public User createUser(User user) {

        try {
            // Save new user record to the database
            SqlParameterSource input = createUserSqlParameterSource(user);
            jdbcTemplate.update(CREATE_NEW_USER_QUERY, input);

            // Increase user counter
            increaseUserCounter();

        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("User with ID: " + user.getUserId() + " already exists in the database >>> " + e.getLocalizedMessage());
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(EXPECTATION_FAILED, "The user could not be created as user data does not match database requirements: " + user + " >>> " + e);
        } catch (Exception e) {
            throw new CustomException(EXPECTATION_FAILED, "An error occurred while creating new user: " + user);
        }

        // Get created user and return
        return this.findUserById(user.getUserId());
    }

    @Override
    public void updateUser(User user) {
        SqlParameterSource input = updateUserSqlParameterSource(user);
        jdbcTemplate.update(UPDATE_USER_BY_USER_ID_QUERY, input);
    }

    // Custom Methods
    private SqlParameterSource createUserSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("user_id", user.getUserId())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("username", user.getUsername())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());
    }

    private SqlParameterSource updateUserSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("user_id", user.getUserId())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("username", verifyUsername(user.getUsername()))
                .addValue("email", user.getEmail())
                .addValue("age", user.getAge())
                .addValue("phone", user.getPhoneNumber())
                .addValue("is_verified", user.getIsVerified())
                .addValue("is_locked", user.getIsLocked())
                .addValue("image_url", user.getImageUrl())
                .addValue("last_updated", user.getLastUpdated());
    }

    private String verifyUsername(String username) {
        if (username.length() != 6)
            throw new CustomException(EXPECTATION_FAILED, "The username must be 6 characters long");
        return username.toUpperCase();
    }

    @Override
    public void deleteUser(String id) {

        // Confirm that user exists
        User user = this.findUserById(id);

        // If found then delete
        MapSqlParameterSource deleteParams = new MapSqlParameterSource().addValue("user_id", user.getUserId());
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, deleteParams);

    }

    @Override
    public User verifyUserById(String userId) {
        boolean verified = true;
        LocalDateTime now = LocalDateTime.now();

        try {

            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("is_verified", verified)
                    .addValue("last_updated", now)
                    .addValue("user_id", userId);

            jdbcTemplate.update(UPDATE_USER_VERIFICATION_BY_ID_QUERY, params);

        } catch (Exception e) {
            log.error("An error occurred while verifying user by id: {} >>> {}", userId, e.getMessage());
            throw new CustomException(INTERNAL_SERVER_ERROR, "Error verifying the user with ID: " + userId);
        }

        return this.findUserById(userId);
    }
}
