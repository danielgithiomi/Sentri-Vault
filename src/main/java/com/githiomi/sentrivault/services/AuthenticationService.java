package com.githiomi.sentrivault.services;

import com.githiomi.sentrivault.data.records.LoginCredentials;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 20, Jan 2025
 **/
public interface AuthenticationService {

    boolean authenticate(LoginCredentials credentials);

}
