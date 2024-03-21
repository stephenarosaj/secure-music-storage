package edu.brown.cs.securemusicstorage.Service;

import edu.brown.cs.securemusicstorage.Entity.User;
import edu.brown.cs.securemusicstorage.FireStore.FireStoreService;
import edu.brown.cs.securemusicstorage.Util.Constants;
import edu.brown.cs.securemusicstorage.dto.*;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private FireStoreService fireStoreService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) throws Exception {
        validateUser(createUserRequest);
        String id = fireStoreService.generateId(Constants.USER_COLLECTION);
        String encodedPassword = passwordEncoder.encode(createUserRequest.password());
        User user = new User(createUserRequest.username(), encodedPassword, id, createUserRequest.name(), createUserRequest.email());
        fireStoreService.save(Constants.USER_COLLECTION, user, id);
    }

    @Override
    public BasicUser getUserByUsername(String username) throws Exception {
        List<BasicUser> basicUsers = fireStoreService.get(Constants.USER_COLLECTION,
                Collections.singletonMap("username", username), BasicUser.class);
        if (basicUsers.isEmpty()) {
            throw new Exception("User not found");
        }
        return basicUsers.get(0);
    }

    @Override
    public BasicUser getUserByEmail(String email) throws Exception {
        List<BasicUser> basicUsers = fireStoreService.get(Constants.USER_COLLECTION,
                Collections.singletonMap("email", email), BasicUser.class);
        if (basicUsers.isEmpty()) {
            throw new Exception("User not found");
        }
        return basicUsers.get(0);
    }

    @Override
    public List<BasicUser> getAllBasicUsers() {
        return fireStoreService.getAll(Constants.USER_COLLECTION, BasicUser.class);
    }

    @Override
    public void updateUsername(String id, String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        fireStoreService.updateField(Constants.USER_COLLECTION, id, "username", username);
    }

    @Override
    public void updateEmail(String id, String email) {
        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        fireStoreService.updateField(Constants.USER_COLLECTION, id, "email", email);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        if (StringUtils.isBlank(changePasswordRequest.id())) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        User user = fireStoreService.get(Constants.USER_COLLECTION, changePasswordRequest.id(), User.class);
        if (!passwordEncoder.matches(changePasswordRequest.oldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        String encodedPassword = passwordEncoder.encode(changePasswordRequest.newPassword());
        fireStoreService.updateField(Constants.USER_COLLECTION, changePasswordRequest.id(), "password", encodedPassword);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        fireStoreService.delete(Constants.USER_COLLECTION, id);
    }

    private void validateUser(CreateUserRequest user) throws Exception {
        if (user.username() == null || user.username().isEmpty()) {
            throw new Exception("Username cannot be empty");
        }
        if (user.password() == null || user.password().isEmpty()) {
            throw new Exception("Password cannot be empty");
        }
        if (user.email() == null || user.email().isEmpty()) {
            throw new Exception("Email cannot be empty");
        }
        if (user.name() == null || user.name().isEmpty()) {
            throw new Exception("Name cannot be empty");
        }
        if (getUserByUsername(user.username()) != null) {
            throw new Exception("Username already exists");
        }
        if (getUserByEmail(user.email()) != null) {
            throw new Exception("Email already exists");
        }
    }
}
