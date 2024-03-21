package edu.brown.cs.securemusicstorage.Controller;

import edu.brown.cs.securemusicstorage.Response.ApiResponse;
import edu.brown.cs.securemusicstorage.Response.ResponseCode;
import edu.brown.cs.securemusicstorage.Service.UserService;
import edu.brown.cs.securemusicstorage.dto.CreateUserRequest;
import edu.brown.cs.securemusicstorage.dto.LoginRequest;
import edu.brown.cs.securemusicstorage.dto.BasicUser;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserController is a REST controller that handles user-related requests.
 * It provides endpoints for creating an account, logging in, logging out,
 * searching for a user by username or email, getting all users, and
 * validating a token(for testing).
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * Creates a new user account.
     *
     * @param createUserRequest The request body containing the user's details.
     * @return ApiResponse indicating the result of the operation.
     */
    @PostMapping("/createAccount")
    public ApiResponse createAccount(@RequestBody CreateUserRequest createUserRequest) {
        ApiResponse response = new ApiResponse<>();
        try {
            userService.createUser(createUserRequest);
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * Searches for a user by username.
     *
     * @param username The username to search for.
     * @return ApiResponse containing the BasicUser object of the user.
     */
    @GetMapping("/searchUserByUsername")
    public ApiResponse<BasicUser> searchUserByUsername(@RequestParam String username) {
        ApiResponse<BasicUser> response = new ApiResponse<>();
        try {
            BasicUser basicUser = userService.getUserByUsername(username);
            response.setData(basicUser);
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * Searches for a user by email.
     *
     * @param email The email to search for.
     * @return ApiResponse containing the BasicUser object of the user.
     */
    @GetMapping("/searchUserByEmail")
    public ApiResponse<BasicUser> searchUserByEmail(@RequestParam String email) {
        ApiResponse<BasicUser> response = new ApiResponse<>();
        try {
            BasicUser basicUser = userService.getUserByEmail(email);
            response.setData(basicUser);
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * Gets all users.
     *
     * @return ApiResponse containing a list of all BasicUser objects.
     */
    @GetMapping("/getAllUsers")
    public ApiResponse<List<BasicUser>> getAllUsers() {
        ApiResponse<List<BasicUser>> response = new ApiResponse<>();
        try {
            response.setData(userService.getAllBasicUsers());
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return response;
    }
}