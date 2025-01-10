package com.githiomi.sentrivault.repositories.impl;

import com.githiomi.sentrivault.exceptions.CustomException;
import com.githiomi.sentrivault.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.githiomi.sentrivault.data.utils.Queries.GET_ROLE_BY_NAME_QUERY;
import static com.githiomi.sentrivault.data.utils.Queries.SAVE_USER_AND_ROLE_QUERY;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 11, Jan 2025
 **/

@Slf4j
@Repository
@AllArgsConstructor
public class UserRoleRepositoryImpl implements UserRoleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean createUserRoleEntry(String userId, String roleName) {

        // Get the role from the database
        MapSqlParameterSource roleParams = new MapSqlParameterSource()
                .addValue("role_name", roleName);

        // Get id for the role passed
        Integer roleId = jdbcTemplate.queryForObject(GET_ROLE_BY_NAME_QUERY, roleParams, Integer.class);

        if (roleId == null) throw new CustomException("No role with name " + roleName + " was found in the database!");

        // Create params for entry in user_role table
        MapSqlParameterSource userRoleParams = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("role_id", roleId);

        // Insert record in user_role table
        jdbcTemplate.update(SAVE_USER_AND_ROLE_QUERY, userRoleParams);
        return true;

    }

}
