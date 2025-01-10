package com.githiomi.sentrivault.data.dto;

import com.githiomi.sentrivault.data.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 09, Jan 2025
 **/

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Integer age;
    private Role role;
    private Boolean isLocked;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

}
