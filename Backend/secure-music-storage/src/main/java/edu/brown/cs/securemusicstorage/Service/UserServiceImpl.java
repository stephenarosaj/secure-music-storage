package edu.brown.cs.securemusicstorage.Service;

import edu.brown.cs.securemusicstorage.Entity.User;
import edu.brown.cs.securemusicstorage.FireStore.FireStoreService;
import edu.brown.cs.securemusicstorage.Security.JwtTokenProvider;
import edu.brown.cs.securemusicstorage.Util.Constants;
import edu.brown.cs.securemusicstorage.dto.CreateUserRequest;
import edu.brown.cs.securemusicstorage.dto.LoginRequest;
import edu.brown.cs.securemusicstorage.dto.BasicUser;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JwtTokenProvider jwtTokenProvider;

    @Resource
    private FireStoreService fireStoreService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public BasicUser getUserByUsername(String username) throws Exception {
        User user = getUserInfoByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        return new BasicUser(user);
    }

    @Override
    public List<BasicUser> getAllBasicUsers() {
        List<User> users = getAllUsers();
        List<BasicUser> basicUsers = new ArrayList<>();
        for (User user : users) {
            basicUsers.add(new BasicUser(user));
        }
        return basicUsers;
    }

    private User getUserInfoByUsername(String username) throws Exception {
        List<User> users = getAllUsers();
        return users.stream().filter(u -> u.getUsername() != null).filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public BasicUser getUserByEmail(String email) throws Exception {
        User user = getUserInfoByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        return new BasicUser(user);
    }

    private User getUserInfoByEmail(String email) throws Exception {
        List<User> users = getAllUsers();
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
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
    public String login(LoginRequest loginRequest) throws Exception {
        if (StringUtils.isEmpty(loginRequest.username()) && StringUtils.isEmpty(loginRequest.email())) {
            throw new Exception("Missing username or email");
        }
        if (StringUtils.isEmpty(loginRequest.password())) {
            throw new Exception("Missing password");
        }
        User user = null;
        if (StringUtils.isNotEmpty(loginRequest.username())) {
            user = getUserInfoByUsername(loginRequest.username());
        }
        if (StringUtils.isNotEmpty(loginRequest.email())) {
            user = getUserInfoByEmail(loginRequest.email());
        }
        if (user == null) {
            throw new Exception("User not found");
        }
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new Exception("Invalid password");
        }
        return authenticateUser(loginRequest.username());
    }

    private String authenticateUser(String username) {
        return jwtTokenProvider.generateToken(username);
    }

    @Override
    public void logout(String userId, String token) throws Exception {
        fireStoreService.save(Constants.BLACKLIST_AUTH_TOKEN_COLLECTION, token);
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
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getUsername().equals(user.username())) {
                throw new Exception("Username already exists");
            }
            if (u.getEmail().equals(user.email())) {
                throw new Exception("Email already exists");
            }
        }
    }

    @Override
    public User updateUser(String id, User user) {
        return null;
    }

    @Override
    public void deleteUser(String id) {
    }

    @Override
    public void validateToken(String username, String token) throws Exception {
        if (StringUtils.isEmpty(username)) {
            throw new Exception("Username cannot be empty");
        }
        if (StringUtils.isEmpty(token)) {
            throw new Exception("Token cannot be empty");
        }
        jwtTokenProvider.validateToken(username, token);
    }

    private List<User> getAllUsers() {
        List<User> users = fireStoreService.getAll(Constants.USER_COLLECTION, User.class);
        return users.stream().filter(u -> u.getUsername() != null && u.getName() != null && u.getEmail() != null).collect(Collectors.toList());
    }
}
