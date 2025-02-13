package com.githiomi.sentrivault.controller;

import com.githiomi.sentrivault.data.records.LoginCredentials;
import com.githiomi.sentrivault.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 20, Jan 2025
 **/

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentials credentials) {
        return this.authenticationService.authenticate(credentials)
                ? ResponseEntity.status(OK).body("Login")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

}
