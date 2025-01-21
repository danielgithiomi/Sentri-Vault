package com.githiomi.sentrivault.repositories;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 20, Jan 2025
 **/
public interface AuthenticationRepository {

    void lockAccountByUsername(String username);

}
