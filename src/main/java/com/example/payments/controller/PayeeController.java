package com.example.payments.controller;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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

	private static final Logger logger = LoggerFactory.getLogger(PayeeController.class);


    @GetMapping
public ResponseEntity<List<Payee>> getPayees(@RequestHeader("Authorization") String token) {
    try {
        logger.info("TEST LOG FROM PayeeController");

        Long userId = TokenUtil.extractUserId(token);
        logger.info("Fetching payees for userId: {}", userId);

        List<Payee> payees = payeeRepository.findByUserId(userId);
        logger.info("Found {} payees for userId {}", payees.size(), userId);

        return ResponseEntity.ok(payees);

    } catch (Exception e) {
        logger.error("Error fetching payees", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


    @PostMapping
public ResponseEntity<Payee> addPayee(@RequestBody Payee payee, @RequestHeader("Authorization") String token) {
    try {
        Long userId = TokenUtil.extractUserId(token);
        logger.info("Extracted userId from token: {}", userId);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> {
                logger.warn("User not found with ID: {}", userId);
                return new RuntimeException("User not found");
            });

        payee.setUser(user);
        Payee savedPayee = payeeRepository.save(payee);
        logger.info("New payee added for userId {}: {}", userId, savedPayee.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayee);

    } catch (Exception e) {
        logger.error("Error occurred while adding payee", e);  // Logs full stack trace
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
}
