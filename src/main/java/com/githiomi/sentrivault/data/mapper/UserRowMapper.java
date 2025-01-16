package com.githiomi.sentrivault.data.mapper;

import com.githiomi.sentrivault.data.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 11, Jan 2025
 **/
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet result, int rowNum) throws SQLException {

        return User.builder()
                .userId(result.getString("user_id"))
                .firstName(result.getString("first_name"))
                .lastName(result.getString("last_name"))
                .username(result.getString("username"))
                .username(result.getString("username"))
                .email(result.getString("email"))
                .age(result.getInt("age"))
                .phoneNumber(result.getObject("phone", Long.class))
                .isVerified(result.getBoolean("is_verified"))
                .isLocked(result.getBoolean("is_locked"))
                .imageUrl(result.getString("image_url"))
                .createdAt(result.getObject("created_at", LocalDateTime.class))
                .lastUpdated(result.getObject("last_updated", LocalDateTime.class))
                .build();
    }
}
