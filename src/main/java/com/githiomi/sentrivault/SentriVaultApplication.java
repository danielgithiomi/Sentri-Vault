package com.githiomi.sentrivault;

import com.githiomi.sentrivault.model.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(Properties.class)
@SpringBootApplication
public class SentriVaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentriVaultApplication.class, args);
    }

}
