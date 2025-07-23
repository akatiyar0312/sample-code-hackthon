package com.example.payments.service;

import com.example.payments.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


 
}
