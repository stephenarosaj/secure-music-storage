package edu.brown.cs.securemusicstorage.Entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String id;
    private String name;
    private String email;

    public User() {
    }

    public User(String username, String password, String id, String name, String email) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.name = name;
        this.email = email;
    }
}