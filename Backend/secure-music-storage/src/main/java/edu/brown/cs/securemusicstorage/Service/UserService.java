package edu.brown.cs.securemusicstorage.Service;

import edu.brown.cs.securemusicstorage.Entity.User;
import edu.brown.cs.securemusicstorage.dto.ChangePasswordRequest;
import edu.brown.cs.securemusicstorage.dto.CreateUserRequest;
import edu.brown.cs.securemusicstorage.dto.BasicUser;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * UserService is an interface that defines the contract for the user service.
 * It includes methods for user management such as getting a user by email or username,
 * creating a user, logging in and out, updating a user, deleting a user, and validating a token.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param createUserRequest The request object containing the user details.
     * @throws Exception if the user could not be created.
     */
    void createUser(CreateUserRequest createUserRequest) throws Exception;

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
     * Updates the username of a user.
     *
     * @param id The ID of the user.
     * @param username The new username.
     */
    void updateUsername(String id, String username);

    /**
     * Updates the email of a user.
     *
     * @param id The ID of the user.
     * @param email The new email.
     */
    void updateEmail(String id, String email);

    /**
     * Update the password of a user.
     *
     * @param changePasswordRequest The request object containing the user's ID, old password, and new password.
     */
    void changePassword(ChangePasswordRequest changePasswordRequest);

    /**
     * Deletes a user.
     *
     * @param id The ID of the user to be deleted.
     * @throws Exception if the user could not be deleted.
     */
    void deleteUser(String id) throws Exception;
}
