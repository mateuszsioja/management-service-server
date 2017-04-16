package com.msjs.managementservice.core.service;

import com.msjs.managementservice.core.model.Role;
import com.msjs.managementservice.core.model.User;
import com.msjs.managementservice.core.repository.UserRepository;
import com.msjs.managementservice.security.PasswordEncoderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 30.03.2017.
 */

@Service
public class UserService {

    private static final String ROLE_PREFIX = "ROLE_";

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user, Long id) {
        user.setId(id);
        user.setPassword(PasswordEncoderGenerator
                .generate(user.getPassword()));
        return userRepository.save(user);
    }

    public User saveUser(User user) {
        user.setRole(Role.ROLE_USER);
        user.setPassword(PasswordEncoderGenerator
                .generate(user.getPassword()));
        user.setCredentialsNotExpired(true);
        return userRepository.save(user);
    }

    public List<User> getUsersByRole(String role) {
        Role roleType = Role.valueOf(ROLE_PREFIX.concat(role).toUpperCase());
        return userRepository.findAllByRole(roleType);
    }

    public User changeUserRole(String role, Long id) {
        User user = userRepository.findOne(id);
        user.setRole(Role.valueOf(role));
        return userRepository.save(user);
    }
}