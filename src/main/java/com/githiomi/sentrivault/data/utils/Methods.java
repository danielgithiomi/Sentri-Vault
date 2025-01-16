package com.githiomi.sentrivault.data.utils;

import com.githiomi.sentrivault.data.enums.Role;
import com.githiomi.sentrivault.exceptions.CustomException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 16, Jan 2025
 **/
public class Methods {

    public static Role getRoleEnumFromString(String roleName) {

        roleName = roleName.toUpperCase();
        Role role;

        try {
            role = Role.valueOf(roleName);
            return role;
        } catch (IllegalArgumentException e) {
            throw new CustomException(NOT_FOUND, "Couldn't find a ROLE with name: " + roleName);
        }

    }
}
