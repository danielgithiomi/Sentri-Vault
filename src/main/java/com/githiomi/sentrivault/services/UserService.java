package com.githiomi.sentrivault.services;

import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.model.User;

import java.util.List;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(String id);

    UserDTO createUser(User user);

    UserDTO updateUser(String id, User user);

    void deleteUserById(String id);

    UserDTO verifyUserById(String id);

}
