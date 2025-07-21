package com.example.payments.service;

import com.example.payments.model.Payee;
import com.example.payments.model.User;
import com.example.payments.repository.PayeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayeeService {
    private final PayeeRepository payeeRepository;

    public PayeeService(PayeeRepository payeeRepository) {
        this.payeeRepository = payeeRepository;
    }

    public List<Payee> getPayeesForUser(User user) {
        return payeeRepository.findByUser(user);
    }

    public Payee addPayee(Payee payee) {
        return payeeRepository.save(payee);
    }

    public Payee getPayeeById(Long id) {
        return payeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payee not found"));
    }
}
