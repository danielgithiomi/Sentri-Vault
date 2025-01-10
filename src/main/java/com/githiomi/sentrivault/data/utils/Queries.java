package com.githiomi.sentrivault.data.utils;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/
public class Queries {

    public static final String CREATE_NEW_USER_QUERY = "INSERT INTO users(user_id, first_name, last_name, username, email, password)" +
                                                        "VALUES (:user_id, :first_name, :last_name, :username, :email, :password)";

}
