package edu.brown.cs.securemusicstorage.Service;

import edu.brown.cs.securemusicstorage.Entity.User;
import edu.brown.cs.securemusicstorage.dto.CreateUserRequest;
import edu.brown.cs.securemusicstorage.dto.LoginRequest;
import edu.brown.cs.securemusicstorage.dto.BasicUser;

import java.util.List;

/**
 * UserService is an interface that defines the contract for the user service.
 * It includes methods for user management such as getting a user by email or username,
 * creating a user, logging in and out, updating a user, deleting a user, and validating a token.
 */
public interface UserService {

    /**
     * Retrieves a BasicUser object by email.
     *
     * @param email The email of the user.
     * @return The BasicUser object if found, throws an exception otherwise.
     * @throws Exception if the user could not be found.
     */
    BasicUser getUserByEmail(String email) throws Exception;

    /**
     * Retrieves a BasicUser object by username.
     *
     * @param username The username of the user.
     * @return The BasicUser object if found, throws an exception otherwise.
     * @throws Exception if the user could not be found.
     */
    BasicUser getUserByUsername(String username) throws Exception;

    /**
     * Retrieves all BasicUser objects.
     *
     * @return A list of all BasicUser objects.
     */
    List<BasicUser> getAllBasicUsers();

    /**
     * Creates a new user.
     *
     * @param createUserRequest The request object containing the user details.
     * @throws Exception if the user could not be created.
     */
    void createUser(CreateUserRequest createUserRequest) throws Exception;

    /**
     * Logs in a user.
     *
     * @param loginRequest The request object containing the login details.
     * @return The JWT token if the login was successful, throws an exception otherwise.
     * @throws Exception if the login was not successful.
     */
    String login(LoginRequest loginRequest) throws Exception;

    /**
     * Logs out a user.
     *
     * @param userId The ID of the user.
     * @param token The JWT token of the user.
     * @throws Exception if the user could not be logged out.
     */
    void logout(String userId, String token) throws Exception;

    User updateUser(String id, User user);

    void deleteUser(String id);

    /**
     * Validates a JWT token.
     *
     * @param username The username for which the token was generated.
     * @param token The JWT token to be validated.
     * @throws Exception if the token could not be verified.
     */
    void validateToken(String username, String token) throws Exception;

}
