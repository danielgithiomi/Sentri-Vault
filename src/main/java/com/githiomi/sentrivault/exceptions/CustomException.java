package com.githiomi.sentrivault.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

@Getter
@Setter
public class CustomException extends RuntimeException {

    private HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public CustomException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

}
