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

    @Query(value = "SELECT u.user_id, u.username, r.role_name, r.role_description FROM sentri_vault_schema.users u JOIN sentri_vault_schema.user_roles ur ON u.user_id = ur.user_id JOIN sentri_vault_schema.roles r ON ur.role_id = r.role_id LIMIT 1",
    nativeQuery = true)
    String getUserAndRole();

}
