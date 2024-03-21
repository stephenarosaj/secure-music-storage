package edu.brown.cs.securemusicstorage.dto;

public record ChangePasswordRequest(String id, String oldPassword, String newPassword) {
}
