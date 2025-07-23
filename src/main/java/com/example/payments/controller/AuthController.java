package com.example.payments.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payments.service.UserService;


@RestController
@RequestMapping("/api")
public class AuthController {


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody Map<String, Object> payload) {
    logger.info("Received login request with payload: {}", payload);

    // Intentionally throw an exception to trigger 500 error
    throw new RuntimeException("Simulated server error for testing");

    // This line won't be reached
    // return ResponseEntity.ok("Hello, World!");
}

}
