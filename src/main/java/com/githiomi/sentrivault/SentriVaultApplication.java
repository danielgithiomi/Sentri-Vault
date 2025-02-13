package com.githiomi.sentrivault;

import com.githiomi.sentrivault.data.domain.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(value = {"*", "http://localhost:5173"})
@EnableConfigurationProperties(Properties.class)
public class SentriVaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentriVaultApplication.class, args);
    }

}
