package edu.brown.cs.securemusicstorage.dto;

import edu.brown.cs.securemusicstorage.Entity.User;
import lombok.Getter;

/**
 * BasicUser is a Data Transfer Object (DTO) that represents a simplified view of a User entity.
 * It is used to transfer data between processes and to hide unnecessary information about an entity from the client.
 */
@Getter
public class BasicUser {
    private final String name;
    private final String email;
    private final String username;

    public BasicUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}