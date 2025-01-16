package com.githiomi.sentrivault.repositories;

import com.githiomi.sentrivault.data.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

public interface UserRepository {

    List<User> findAllUsers();

    User findUserById(String id);

    User createUser(User user);

    void updateUser(User user);

    void deleteUser(String id);

}
