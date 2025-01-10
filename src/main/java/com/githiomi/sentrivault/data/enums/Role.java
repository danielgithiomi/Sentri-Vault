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

    USER("USER"),
    ADMIN("ADMIN"),
    SUPER_USER("SUPER_USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

}
