package com.githiomi.sentrivault.repositories.impl;

import com.githiomi.sentrivault.data.mapper.UserRowMapper;
import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.exceptions.CustomException;
import com.githiomi.sentrivault.repositories.RoleRepository;
import com.githiomi.sentrivault.repositories.UserRepository;
import com.githiomi.sentrivault.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.githiomi.sentrivault.data.model.User.increaseUserCounter;
import static com.githiomi.sentrivault.data.utils.Queries.*;

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

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public User findUserById(String id) {

        try {
            MapSqlParameterSource params = new MapSqlParameterSource().addValue("user_id", id);
            return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, params, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error("No user found in the database with ID: {} -> {}", id, e.getMessage());
            throw new CustomException("No user found in the database with ID: " + id);
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
            log.info("Created new user: {}", user);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("User with ID: " + user.getUserId() + " already exists in the database >>> " + e.getLocalizedMessage());
        } catch (Exception e) {
            throw new CustomException("An error occurred while creating new user: " + user, e);
        }

        return user;
    }

    @Override
    public User updateUser(User user) {

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
                .addValue("username", user.getUsername())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("age", user.getAge())
                .addValue("phone", user.getPhoneNumber())
                .addValue("is_verified", user.getIsVerified())
                .addValue("is_locked", user.getIsLocked())
                .addValue("image_url", user.getImageUrl())
                .addValue("created_at", user.getCreatedAt())
                .addValue("last_updated", user.getLastUpdated());
    }

}
