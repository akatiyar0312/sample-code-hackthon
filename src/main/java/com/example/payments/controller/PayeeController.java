package com.example.payments.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payments.model.Payee;
import com.example.payments.model.User;
import com.example.payments.repository.PayeeRepository;
import com.example.payments.repository.UserRepository;
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
public ResponseEntity<List<Payee>> getPayees() {
    try {
      //  logger.info("TEST LOG FROM PayeeController");

        //Long userId = TokenUtil.extractUserId(token);
        System.err.println("Fetching payees for userId: {}" + 11);

       // List<Payee> payees = payeeRepository.findBy(1l);
        //logger.info("Found {} payees for userId {}", payees.size(), userId);

       // return ResponseEntity.ok(payees);
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    } catch (Exception e) {
        System.out.println("Error fetching payees" + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


    @PostMapping
public ResponseEntity<Payee> addPayee(@RequestBody Payee payee) {
    try {
        //System.out.println("Fetching payees for userId: {}" + TokenUtil.extractUserId(token));
       // Long userId = TokenUtil.extractUserId(token);
        System.out.println("Extracted userId from token: {}");

        User user = userRepository.findById(1l)
            .orElseThrow(() -> {
                return new RuntimeException("User not found");
            });

        payee.setUser(user);
        Payee savedPayee = payeeRepository.save(payee);
        System.out.println("New payee added for userId {}: {}");

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayee);

    } catch (Exception e) {
        System.out.println("Error occurred while adding payee");  // Logs full stack trace
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
}
