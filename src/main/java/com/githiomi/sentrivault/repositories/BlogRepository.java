package com.githiomi.sentrivault.repositories;


import com.githiomi.sentrivault.data.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 09, Jan 2025
 **/

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {

//    @Query(value = "SELECT new com.yourpackage.dto.UserRoleDTO(u.userId, u.username, r.name, r.description) \" +\n" +
//            "           \"FROM UserRole ur \" +\n" +
//            "           \"JOIN ur.user u \" +\n" +
//            "           \"JOIN ur.role r\"")
    @Query(value = "SELECT u.user_id, u.username, r.name, r.description FROM sentri_vault_schema.user_roles ur JOIN sentri_vault_schema.users u ON ur.user_id = u.user_id JOIN sentri_vault_schema.roles r ON ur.role_id = r.role_id",
    nativeQuery = true)
    String getUserAndRole();
}
