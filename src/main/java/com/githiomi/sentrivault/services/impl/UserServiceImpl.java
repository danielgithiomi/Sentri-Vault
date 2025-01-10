package com.githiomi.sentrivault.services.impl;


import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.mapper.UserDTOMapper;
import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.repositories.BlogRepository;
import com.githiomi.sentrivault.repositories.UserRepository;
import com.githiomi.sentrivault.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final BlogRepository blogRepository;

    @Override
    public String getUserById(String id) {
        log.info("Getting user with id: {}", id);
        return this.blogRepository.getUserAndRole();
//        return UserDTOMapper.toUserDTO(this.userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id: " + id)));
    }

    @Override
    public UserDTO createUser(User user) {
        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        this.userRepository.createUser(newUser);
        return UserDTOMapper.toUserDTO(newUser);
    }
}
