package com.githiomi.sentrivault.repositories;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 11, Jan 2025
 **/
public interface UserRoleRepository {

    String getRoleByUserId(String id);

    void createUserRoleEntry(String userId, String roleName);

    void updateUserRoleEntry(String userId, int roleId);

}
