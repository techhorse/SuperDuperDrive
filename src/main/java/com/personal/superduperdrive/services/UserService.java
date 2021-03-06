package com.personal.superduperdrive.services;

import com.personal.superduperdrive.mapper.UserMapper;
import com.personal.superduperdrive.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    public static User loggedinuser;

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUserByUsername(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insertUser(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstname(), user.getLastname()));
    }

    public User getUser(String username) {
        return userMapper.getUserByUsername(username);
    }
}

