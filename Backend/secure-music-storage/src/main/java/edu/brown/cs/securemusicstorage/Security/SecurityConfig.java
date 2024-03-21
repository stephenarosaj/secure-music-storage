package edu.brown.cs.securemusicstorage.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    /**
     * Defines a PasswordEncoder bean that uses the BCrypt strong hashing function.
     * The purpose of this bean is to encode passwords before they are stored in the database.
     *
     * @return A BCryptPasswordEncoder object.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
