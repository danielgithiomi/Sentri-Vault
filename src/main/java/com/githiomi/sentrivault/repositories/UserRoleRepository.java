package com.githiomi.sentrivault.repositories;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 11, Jan 2025
 **/
public interface UserRoleRepository {

    boolean createUserRoleEntry(String userId, String roleName);

}
