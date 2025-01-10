package com.githiomi.sentrivault.repositories.impl;

import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.exceptions.CustomException;
import com.githiomi.sentrivault.repositories.RoleRepository;
import com.githiomi.sentrivault.repositories.UserRepository;
import com.githiomi.sentrivault.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.githiomi.sentrivault.data.model.User.increaseUserCounter;
import static com.githiomi.sentrivault.data.utils.Queries.CREATE_NEW_USER_QUERY;

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
    public User createUser(User user) {

        try {

            // Save new user record to the database
            SqlParameterSource input = userSqlParameterSource(user);
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

    // Custom Methods
    private SqlParameterSource userSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("user_id", user.getUserId())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("username", user.getUsername())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());
    }

}
