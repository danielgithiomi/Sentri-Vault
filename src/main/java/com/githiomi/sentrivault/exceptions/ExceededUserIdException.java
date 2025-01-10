package com.githiomi.sentrivault.exceptions;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/
public class ExceededUserIdException extends RuntimeException{

    public ExceededUserIdException(String message){
        super(message);
    }
}
