package com.example.payments.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payments.model.Payee;
import com.example.payments.model.User;
import com.example.payments.repository.PayeeRepository;
import com.example.payments.repository.UserRepository;
import com.example.payments.util.TokenUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/payees")
public class PayeeController {
    @Autowired 
    private PayeeRepository payeeRepository;
    @Autowired 
    private UserRepository userRepository;

    @GetMapping
    public List<Payee> getPayees(@RequestHeader("Authorization") String token) {
        Long userId = TokenUtil.extractUserId(token);
        return payeeRepository.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Payee> addPayee(@RequestBody Payee payee, @RequestHeader("Authorization") String token) {
        Long userId = TokenUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                  .orElseThrow(() -> new RuntimeException("User not found"));
        payee.setUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(payeeRepository.save(payee));
    }
}
