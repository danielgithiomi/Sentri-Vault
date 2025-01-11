package com.githiomi.sentrivault.services;

import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.model.User;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

public interface UserService {

    UserDTO getUserById(String id);

    UserDTO createUser(User user);

}
