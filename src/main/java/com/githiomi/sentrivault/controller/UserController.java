package com.githiomi.sentrivault.controller;

import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("user_id") String id) {
        log.info("get user with id {}", id);
        return ResponseEntity.status(200).body(this.userService.getUserById(id.toUpperCase()));
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(this.userService.createUser(user));
    }

}
