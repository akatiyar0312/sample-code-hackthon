package com.example.payments.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.payments.model.Payment;
import com.example.payments.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(Payment payment) {
        payment.setStatus("PROCESSED");
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payment refundPayment(Long id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            Payment p = optional.get();
            p.setStatus("REFUNDED");
            return paymentRepository.save(p);
        } else {
            throw new RuntimeException("Payment not found");
        }
    }
}

