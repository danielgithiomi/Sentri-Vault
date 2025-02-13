package com.githiomi.sentrivault.services.impl;

import com.githiomi.sentrivault.data.records.LoginCredentials;
import com.githiomi.sentrivault.data.domain.User;
import com.githiomi.sentrivault.exceptions.CustomException;
import com.githiomi.sentrivault.repositories.AuthenticationRepository;
import com.githiomi.sentrivault.services.AuthenticationService;
import com.githiomi.sentrivault.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.githiomi.sentrivault.data.utils.Constants.PASSWORD_TRIAL_LIMIT;
import static org.springframework.http.HttpStatus.LOCKED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 20, Jan 2025
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationRepository authenticationRepository;
    private static final Map<String, Integer> trialTracker = new HashMap<>();

    @Override
    public boolean authenticate(LoginCredentials credentials) {

        String username = credentials.username().toUpperCase();
        String password = credentials.password();

        // Confirm user exists
        User user = this.userService.getUserByUsername(username);

        // Check if the account is locked
        if (user.getIsLocked()) throw new CustomException(UNAUTHORIZED, "This user account is already locked");

        // Compare passwords
        return comparePasswords(password, user);
    }

    private boolean comparePasswords(String inputPassword, User user) {

        String userId = user.getUserId();
        String userPassword = user.getPassword();
        String hashedInputPassword = passwordEncoder.encode(inputPassword);

        log.info("userPassword: {}", userPassword);
        log.info("inputPassword: {}", inputPassword);
        log.info("hashedInputPassword: {}", hashedInputPassword);

        System.out.println("Map after adding user trial " + trialTracker.toString());

        if (!passwordEncoder.matches(inputPassword, userPassword)) {

            // Check if user exists in the map
            trialTracker.compute(userId, (id, trialCounter) -> trialCounter == null ? 1 : trialCounter + 1);

            System.out.println("Map after failed user trial " + trialTracker.toString());

            int trialCounter = trialTracker.get(userId);
            log.info("Current trial counter for {} : {}", user.getUsername(), trialCounter);
            log.info("You have {} trials remaining", PASSWORD_TRIAL_LIMIT - trialCounter);

            if (trialCounter == PASSWORD_TRIAL_LIMIT) lockUserAccount(user);

            return false;
        }

        // Remove from map
        trialTracker.remove(userId);

        System.out.println("Map after removing user trial " + trialTracker.toString());

        log.info("Logging in the user with username: {}", user.getUsername());
        return true;
    }

    private void lockUserAccount(User user) {

        log.error("Trials exceeded. Locking the user account");

        // Set is locked to true
        user.setIsLocked(Boolean.TRUE);

        this.authenticationRepository.lockAccountByUsername(user.getUserId());

        throw new CustomException(LOCKED, "Trials exceeded. This user account has been locked");

    }

}
