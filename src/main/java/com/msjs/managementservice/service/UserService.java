package com.msjs.managementservice.service;

import com.msjs.managementservice.model.User;
import com.msjs.managementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jakub on 30.03.2017.
 */

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        user.setPassword(PasswordEncoderGenerator
                .generate(user.getPassword()));
        return userRepository.save(user);
    }
}
