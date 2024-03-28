package edu.brown.cs.securemusicstorage.Entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String username;
    private String password;
    private String id;
    private String name;
    private String email;
    private List<String> friends;
    private List<String> friendRequests;
    private List<String> friendRequestsSent;

    public User() {
    }

    public User(String username, String password, String id, String name, String email) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.name = name;
        this.email = email;
        this.friends = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
        this.friendRequestsSent = new ArrayList<>();
    }
}