package com.githiomi.sentrivault.controller;

import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.model.User;
import com.githiomi.sentrivault.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("user_id") String id) {
        return ResponseEntity.status(200).body(this.userService.getUserById(id.toUpperCase()));
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(this.userService.createUser(user));
    }

    @PutMapping(value = "/{user_id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user, @PathVariable("user_id") String id) {
        return ResponseEntity.status(200).body(this.userService.updateUser(id.toUpperCase(), user));
    }

    @DeleteMapping(value = "/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable("user_id") String id) {
        id = id.toUpperCase();
        this.userService.deleteUserById(id);
        return ResponseEntity.status(OK.value()).body("The user with id " + id + " was deleted successfully.");
    }

}
