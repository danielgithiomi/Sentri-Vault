package com.githiomi.sentrivault.services.impl;

import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.enums.Role;
import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.exceptions.CustomException;
import com.githiomi.sentrivault.repositories.BlogRepository;
import com.githiomi.sentrivault.repositories.UserRepository;
import com.githiomi.sentrivault.repositories.UserRoleRepository;
import com.githiomi.sentrivault.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.githiomi.sentrivault.data.mapper.UserDTOMapper.toUserDTO;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BlogRepository blogRepository;

    @Override
    public UserDTO getUserById(String id) {

        // Get the user
        User user = this.userRepository.findUserById(id);

        // Get the user corresponding role
        String roleName = this.userRoleRepository.getUserRole(id);
        Role role = Role.valueOf(roleName);

        // Convert user to DTO
        UserDTO userDTO = toUserDTO(user);
        userDTO.setRole(role);

        return userDTO;
    }

    @Override
    public UserDTO createUser(User user) {
        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());

        // Get Role Object
        Role role;
        try {
            role = Role.valueOf(user.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomException("There is no role found with the name: {" + user.getRole() + "}");
        }

        // Convert User to UserDTO
        UserDTO dto = toUserDTO(this.userRepository.createUser(newUser));

        // Set UserDTO role
        dto.setRole(role);

        // Add entry to user_Role Table
        this.userRoleRepository.createUserRoleEntry(newUser.getUserId(), user.getRole().toUpperCase());

        return dto;
    }

    @Override
    public UserDTO updateUser(String id, User user) {

        // Find the user in the database
        User dbUser = this.userRepository.findUserById(id);

        // Double Check user role
        String currentRole = dbUser.getRole();
        String newRole = user.getRole();
        checkUserRole(currentRole, newRole);

        // Update the lastUpdate field
        user.setLastUpdated(LocalDateTime.now());

        this.userRepository.updateUser(user);

    }

    private void checkUserRole(String currentRole, String newRole) {

        if (currentRole.equals(newRole)) return;

        // When role has been changed
        this.userRoleRepository.

    }
}
