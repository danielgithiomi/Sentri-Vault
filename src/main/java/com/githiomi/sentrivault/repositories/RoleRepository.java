package com.githiomi.sentrivault.repositories;

import com.githiomi.sentrivault.data.enums.Role;
import com.githiomi.sentrivault.data.utils.Queries;
import org.springframework.data.jpa.repository.Query;

import static com.githiomi.sentrivault.data.utils.Queries.*;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

public interface RoleRepository {

    @Query(value = GET_ROLE_BY_ID_QUERY, nativeQuery = true)
    int getRoleId(String roleName);

    @Query(value = GET_USER_ROLE_BY_USER_ID_QUERY, nativeQuery = true)
    Role getRoleByUserId(String user_id);

    @Query(value = GET_ROLE_BY_NAME_QUERY, nativeQuery = true)
    Role getRoleByName(String name);

}
