package com.example.payments.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payments.repository.AccountRepository;
import com.example.payments.util.TokenUtil;
import com.example.payments.model.Account;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired private AccountRepository accountRepository;

        private static final Logger logger = LogManager.getLogger(PayeeController.class);


    @GetMapping
    public List<Account> getAccounts(@RequestHeader("Authorization") String token) {
        Long userId = TokenUtil.extractUserId(token); // stub/mocked
        return accountRepository.findByUserId(userId);
    }
}