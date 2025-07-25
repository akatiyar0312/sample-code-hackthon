package com.example.payments.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // 1. Controller for login requests
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, Object> payload) {
        logger.info("Received login request with payload: {}", payload);

        try {
            if (payload == null || payload.isEmpty()) {
                logger.error("Payload is null or empty");
                return ResponseEntity.status(400).body("Error: Invalid payload");
            }

            // Assume successful login if username and password exist
            if (payload.containsKey("username") && payload.containsKey("password")) {
                logger.info("Login successful for user: {}", payload.get("username"));
                return ResponseEntity.ok("Login successful");
            } else {
                logger.error("Missing username or password");
                return ResponseEntity.status(400).body("Error: Missing username or password");
            }
        } catch (Exception e) {
            // Log the exception details here
            logger.error("Exception occurred during login", e);
            return ResponseEntity.status(500).body("Error: Internal Server Error");
        }
    }

    // 2. Controller to add a user
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody Map<String, Object> payload) {
        logger.info("Received add user request with payload: {}", payload);

        try {
            if (payload == null || payload.isEmpty()) {
                logger.error("Payload is null or empty");
                return ResponseEntity.status(400).body("Error: Invalid payload");
            }

            if (!payload.containsKey("username") || !payload.containsKey("password")) {
                logger.error("Missing username or password in the payload");
                return ResponseEntity.status(400).body("Error: Missing required fields: username and password");
            }

            // Simulate adding the user to the database
            logger.info("User added successfully: {}", payload.get("username"));
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            // Log the exception details here
            logger.error("Exception occurred while adding user", e);
            return ResponseEntity.status(500).body("Error: Internal Server Error");
        }
    }

    // 3. Controller to delete a user
    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, Object> payload) {
        logger.info("Received delete user request with payload: {}", payload);

        try {
            if (payload == null || payload.isEmpty()) {
                logger.error("Payload is null or empty");
                return ResponseEntity.status(400).body("Error: Invalid payload");
            }

            if (!payload.containsKey("username")) {
                logger.error("Missing username in the payload");
                return ResponseEntity.status(400).body("Error: Missing required field: username");
            }

            // Simulate deleting the user from the database
            logger.info("User deleted successfully: {}", payload.get("username"));
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            // Log the exception details here
            logger.error("Exception occurred while deleting user", e);
            return ResponseEntity.status(500).body("Error: Internal Server Error");
        }
    }

    // 4. Controller to throw a TypeCastException for type casting failure
    @PostMapping("/cast")
    public ResponseEntity<String> cast(@RequestBody Map<String, Object> payload) {
        logger.info("Received cast request with payload: {}", payload);

        try {
            // Trying to cast an object to an incompatible type
            String value = (String) payload.get("someInteger"); // Will throw ClassCastException if "someInteger" is not a String
        } catch (ClassCastException e) {
            // Log the exception details here
            logger.error("ClassCastException occurred", e);
            return ResponseEntity.status(500).body("Error: Invalid type casting");
        }

        return ResponseEntity.ok("Cast successful");
    }
}
