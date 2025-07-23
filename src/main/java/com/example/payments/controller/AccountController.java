package com.example.payments.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payments.repository.AccountRepository;
import com.example.payments.repository.UserRepository;
import com.example.payments.util.TokenUtil;
import com.example.payments.model.Account;
import com.example.payments.model.User;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired 
    private AccountRepository accountRepository;
    @Autowired 
    private UserRepository userRepository;

    @GetMapping
    public List<Account> getAccounts(@RequestHeader("Authorization") String token) {
        Long userId = TokenUtil.extractUserId(token);
        User user = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"))).get();
        return accountRepository.findByUser(user);
    }
}