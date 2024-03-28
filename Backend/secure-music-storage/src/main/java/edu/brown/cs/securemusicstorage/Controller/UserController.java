package edu.brown.cs.securemusicstorage.Controller;

import edu.brown.cs.securemusicstorage.Response.ApiResponse;
import edu.brown.cs.securemusicstorage.Response.ResponseCode;
import edu.brown.cs.securemusicstorage.Service.UserService;
import edu.brown.cs.securemusicstorage.dto.ChangePasswordRequest;
import edu.brown.cs.securemusicstorage.dto.CreateUserRequest;
import edu.brown.cs.securemusicstorage.dto.BasicUser;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * Updates the username of a user.
     *
     * @param id The ID of the user.
     * @param username The new username.
     * @return ApiResponse indicating the result of the operation.
     */
    @PostMapping("/updateUsername")
    public ApiResponse updateUsername(@RequestParam String id, @RequestParam String username) {
        ApiResponse response = new ApiResponse<>();
        try {
            userService.updateUsername(id, username);
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * Updates the email of a user.
     *
     * @param id The ID of the user.
     * @param email The new email.
     * @return ApiResponse indicating the result of the operation.
     */
    @PostMapping("/updateEmail")
    public ApiResponse updateEmail(@RequestParam String id, @RequestParam String email) {
        ApiResponse response = new ApiResponse<>();
        try {
            userService.updateEmail(id, email);
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * Changes the password of a user.
     *
     * @param changePasswordRequest The request body containing the user's ID, old password, and new password.
     * @return ApiResponse indicating the result of the operation.
     */
    @PostMapping("/changePassword")
    public ApiResponse changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        ApiResponse response = new ApiResponse<>();
        try {
            userService.changePassword(changePasswordRequest);
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * Deletes a user account.
     *
     * @param id The ID of the user to delete.
     * @return ApiResponse indicating the result of the operation.
     */
    @DeleteMapping("/deleteUser")
    public ApiResponse deleteUser(@RequestParam String id) {
        ApiResponse response = new ApiResponse<>();
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return response;
    }
}