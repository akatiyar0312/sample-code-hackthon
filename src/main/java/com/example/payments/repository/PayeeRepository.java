package com.example.payments.repository;

import com.example.payments.model.Payee;
import com.example.payments.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayeeRepository extends JpaRepository<Payee, Long> {
    List<Payee> findByUser(User user);
    List<Payee> findByUserId(Long id);
    
}
