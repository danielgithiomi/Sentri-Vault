package com.githiomi.sentrivault.services.impl;

import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.enums.Role;
import com.githiomi.sentrivault.data.mapper.UserDTOMapper;
import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.exceptions.CustomException;
import com.githiomi.sentrivault.repositories.BlogRepository;
import com.githiomi.sentrivault.repositories.UserRepository;
import com.githiomi.sentrivault.repositories.UserRoleRepository;
import com.githiomi.sentrivault.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.githiomi.sentrivault.data.mapper.UserDTOMapper.toUserDTO;
import static com.githiomi.sentrivault.data.utils.Methods.getRoleEnumFromString;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

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
    public List<UserDTO> getAllUsers() {
        return this.userRepository.findAllUsers().stream().map(UserDTOMapper::toUserDTO).toList().reversed();
    }

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

        // Create and retrieve the saved user
        UserDTO dto = toUserDTO(this.userRepository.createUser(newUser));

        // Set UserDTO role
        dto.setRole(role);

        // Add entry to user_Role Table
        this.userRoleRepository.createUserRoleEntry(newUser.getUserId(), user.getRole().toUpperCase());

        return dto;
    }

    @Override
    public UserDTO updateUser(String userId, User user) {

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

    @Override
    public void deleteUserById(String id) {

        // Confirm user exits in the database
        this.userRepository.deleteUser(id);

    }

    @Override
    public UserDTO verifyUserById(String id) {

        // Confirm user exists in database
        User user = this.userRepository.findUserById(id);

        // Get verification status
        if (user.getIsVerified())
            throw new CustomException(BAD_REQUEST, "The user with ID " + id + " is already verified!");

        // Update database with new verification
        User updatedUser = this.userRepository.verifyUserById(id);

        // Convert updated user to DTO
        UserDTO dto = toUserDTO(updatedUser);

        // Get associated role from DB
        String userRole = this.userRoleRepository.getRoleByUserId(id);

        dto.setRole(getRoleEnumFromString(userRole));

        return dto;
    }
}
