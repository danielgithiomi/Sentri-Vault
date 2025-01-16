package com.githiomi.sentrivault.services.impl;

import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.enums.Role;
import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.data.utils.Methods;
import com.githiomi.sentrivault.exceptions.CustomException;
import com.githiomi.sentrivault.repositories.BlogRepository;
import com.githiomi.sentrivault.repositories.UserRepository;
import com.githiomi.sentrivault.repositories.UserRoleRepository;
import com.githiomi.sentrivault.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.githiomi.sentrivault.data.mapper.UserDTOMapper.toUserDTO;
import static com.githiomi.sentrivault.data.utils.Methods.getRoleEnumFromString;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO getUserById(String id) {

        // Get the user
        User user = this.userRepository.findUserById(id);

        // Get the user corresponding role
        String roleName = this.userRoleRepository.getRoleByUserId(id);
        Role role = Role.valueOf(roleName);

        // Convert user to DTO
        UserDTO userDTO = toUserDTO(user);
        userDTO.setRole(role);

        return userDTO;
    }

    @Override
    public UserDTO createUser(User user) {
        // Password Encode
        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getRole());

        // Get Role Object
        Role role = getRoleEnumFromString(user.getRole());

        // Create user in the database
        this.userRepository.createUser(newUser);

        // Retrieve the saved user
        UserDTO dto = toUserDTO(this.userRepository.findUserById(newUser.getUserId()));

        // Set UserDTO role
        dto.setRole(role);

        // Add entry to user_Role Table
        this.userRoleRepository.createUserRoleEntry(newUser.getUserId(), user.getRole().toUpperCase());

        return dto;
    }

    @Override
    public UserDTO updateUser(String userId, User user) {

        log.info("Updating user with id {} and data {}", userId, user);
        // Check if user exists in the database
        User foundDBUser = this.userRepository.findUserById(userId);

        // Find the role in the database
        String currentRole = this.userRoleRepository.getRoleByUserId(userId);
        String newRole = user.getRole();

        // Check and update user role
        if (!currentRole.equalsIgnoreCase(newRole)) updateDbUserRole(userId, newRole);

        // Update the lastUpdate field
        user.setCreatedAt(foundDBUser.getCreatedAt());
        user.setLastUpdated(LocalDateTime.now());

        // Update user record in DB and return DTO
        this.userRepository.updateUser(user);

        UserDTO dto = toUserDTO(user);
        dto.setRole(getRoleEnumFromString(newRole));
        return dto;

    }

    private void updateDbUserRole(String userId, String newRole) {

        // Get Role ID
        int roleId = getRoleEnumFromString(newRole).getId();

        this.userRoleRepository.updateUserRoleEntry(userId, roleId);

    }
}
