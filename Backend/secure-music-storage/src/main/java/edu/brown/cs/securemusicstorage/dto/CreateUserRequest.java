package edu.brown.cs.securemusicstorage.dto;

public record CreateUserRequest(String name, String email, String password, String username) {
}
