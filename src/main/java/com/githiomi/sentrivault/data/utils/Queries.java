package com.githiomi.sentrivault.data.utils;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/
public class Queries {

    public static final String CREATE_NEW_USER_QUERY = "INSERT INTO users (user_id, first_name, last_name, username, email, password)" +
                                                                  "VALUES (:user_id, :first_name, :last_name, :username, :email, :password);";

    public static final String GET_USER_BY_ID_QUERY = "SELECT * FROM users WHERE user_id = :user_id;";

    public static final String GET_ROLE_BY_ID_QUERY = "SELECT role_id FROM roles WHERE role_id = :role_id;";

    public static final String GET_ROLE_ID_BY_NAME_QUERY = "SELECT role_id FROM roles WHERE role_name = :role_name;";

    public static final String GET_USER_ROLE_BY_USER_ID_QUERY = """
            SELECT r.role_name FROM sentri_vault_schema.user_roles ur
                JOIN sentri_vault_schema.users u
                ON ur.user_id = u.user_id
                JOIN sentri_vault_schema.roles r
                ON ur.role_id = r.role_id
                WHERE u.user_id = :user_id""";

    public static final String UPDATE_USER_BY_USER_ID_QUERY = """
            UPDATE users
            SET first_name = :first_name,
                last_name = :last_name,
                username = :username,
                email = :email,
                age = :age,
                phone = :phone,
                is_verified = :is_verified,
                is_locked = :is_locked,
                image_url = :image_url,
                last_updated = :last_updated
            WHERE user_id = :user_id;""";

    public static final String DELETE_USER_BY_ID_QUERY = """
            DELETE FROM users WHERE user_id = :user_id;
            """;

    public static final String SAVE_USER_AND_ROLE_QUERY = "INSERT INTO user_roles (user_id, role_id) " +
            "VALUES (:user_id, :role_id);";

    public static final String UPDATE_USER_ROLE_ENTRY_BY_USER_ID_QUERY = """
            UPDATE user_roles
            SET role_id = :role_id
            WHERE user_id = :user_id;""";

}
