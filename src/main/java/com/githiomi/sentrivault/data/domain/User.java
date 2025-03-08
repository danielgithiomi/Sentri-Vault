package com.githiomi.sentrivault.data.domain;

import com.githiomi.sentrivault.exceptions.CustomException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.LENGTH_REQUIRED;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private static int USER_COUNTER = 3;

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int trialCounter;
    private Integer age;
    private Long phoneNumber;
    private String role;
    private Boolean isLocked;
    private Boolean isVerified;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    public User(String firstName, String lastName, String email, String password, String role) {
        this.userId = generateUserId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = generateUsername(firstName, lastName);
        this.email = email;
        this.password = password;
        this.role = role.toUpperCase();
    }

    private static String generateUserId() {
        String base = "USR";

        if (USER_COUNTER >= 100 && USER_COUNTER < 1000) return base + USER_COUNTER;

        return USER_COUNTER < 10
                ? base + "00" + USER_COUNTER
                : base + "0" + USER_COUNTER;
    }

    private static String generateUsername(String firstName, String lastName) {
        if (firstName.length() < 3 || lastName.length() < 3)
            throw new CustomException(LENGTH_REQUIRED, "The first name and last name must be at least 3 characters");
        return (firstName.substring(0, 3) + lastName.substring(0, 3)).toUpperCase();
    }

    public static void increaseUserCounter() {
        USER_COUNTER++;
    }
}
