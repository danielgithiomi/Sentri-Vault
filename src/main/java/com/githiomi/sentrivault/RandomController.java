package com.githiomi.sentrivault;


import com.githiomi.sentrivault.repositories.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 09, Jan 2025
 **/

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1")
public class RandomController {

    private final BlogRepository blogRepository;

    @GetMapping("")
    public String random() {
        return this.blogRepository.getUserAndRole();
    }

}
