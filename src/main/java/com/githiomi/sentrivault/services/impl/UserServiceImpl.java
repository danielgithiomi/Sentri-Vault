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
        log.info("Getting user with id: {}", id);

        // Get the user corresponding role
        String roleName = this.userRoleRepository.getUserRole(id);
        if (roleName.isEmpty()) throw new CustomException("There was no role for the user with id: " + id);
        Role role = Role.valueOf(roleName);

        // Get the user
        User user = this.userRepository.findById(id);

        // Convert user to DTO
        UserDTO userDTO = toUserDTO(user);
        userDTO.setRole(role);

        log.info("Returning userDTO: {}", userDTO);

        return userDTO;
    }

    @Override
    public UserDTO createUser(User user) {
        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
        UserDTO dto = toUserDTO(this.userRepository.createUser(newUser));
        this.userRoleRepository.createUserRoleEntry(newUser.getUserId(), user.getRole().toUpperCase());
        return dto;
    }
}
