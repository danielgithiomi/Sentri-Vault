package com.githiomi.sentrivault.data.enums;

import lombok.Getter;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 09, Jan 2025
 **/

@Getter
public enum Role {

    USER(1, "USER"),
    ADMIN(2, "ADMIN"),
    SUPER_USER(3, "SUPER_USER");

    private final Integer id;
    private final String role;

    Role(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

}
