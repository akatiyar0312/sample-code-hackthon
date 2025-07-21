package com.example.payments.service;

import com.example.payments.model.User;
import com.example.payments.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> login(String username, String rawPassword) {
        return userRepository.findByUsername(username)
            .filter(user -> passwordEncoder.matches(rawPassword, user.getPasswordHash()));
    }

    public boolean validateTxPassword(User user, String txPassword) {
        return passwordEncoder.matches(txPassword, user.getTxPasswordHash());
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
