package com.githiomi.sentrivault.data.model;

import com.githiomi.sentrivault.exceptions.CustomException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private static int USER_COUNTER = 8;

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
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
        this.role = role;
    }

    private static String generateUserId() {
        String base = "USR";

        if (USER_COUNTER >= 100 && USER_COUNTER < 1000) {
            return base + USER_COUNTER;
            //throw new CustomException("The number of current users exceeds 100");
        }

        return USER_COUNTER < 10
                ? base + "00" + USER_COUNTER
                : base + "0" + USER_COUNTER;
    }

    private static String generateUsername(String firstName, String lastName) {
        if (firstName.length() < 3 || lastName.length() < 3)
            throw new CustomException("The first name and last name must be at least 3 characters");
        return (firstName.substring(0, 3) + lastName.substring(0, 3)).toUpperCase();
    }

    public static void increaseUserCounter() {
        USER_COUNTER++;
    }
}
