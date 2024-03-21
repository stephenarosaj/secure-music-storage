package edu.brown.cs.securemusicstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // for testing purposes
public class SecureMusicStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureMusicStorageApplication.class, args);
    }

}
