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

    SUPER_USER(1, "SUPER_USER"),
    ADMIN(2, "ADMIN"),
    USER(3, "USER");

    private final Integer id;
    private final String role;

    Role(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

}
