package com.githiomi.sentrivault.repositories.impl;

import com.githiomi.sentrivault.data.utils.Queries;
import com.githiomi.sentrivault.repositories.AuthenticationRepository;
import com.githiomi.sentrivault.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 20, Jan 2025
 **/

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private final UserRepository userRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void lockAccountByUsername(String userId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("is_locked", Boolean.TRUE);

        jdbcTemplate.update(Queries.UPDATE_USER_IS_LOCKED_QUERY, params);

    }
}
