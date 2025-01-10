package com.githiomi.sentrivault.repositories.impl;

import com.githiomi.sentrivault.data.enums.Role;
import com.githiomi.sentrivault.data.utils.Queries;
import com.githiomi.sentrivault.repositories.RoleRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 11, Jan 2025
 **/

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Override
    public int getRoleId(String roleName) {
        return 0;
    }

    @Override
    public Role getRoleByUserId(String user_id) {
        return null;
    }

    @Override
    @Query(value = Queries.GET_ROLE_BY_NAME_QUERY, nativeQuery = true)
    public Role getRoleByName(String roleName) {
        return null;
    }

}
