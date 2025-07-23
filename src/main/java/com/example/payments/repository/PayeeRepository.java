package com.example.payments.repository;

import com.example.payments.model.Payee;
import com.example.payments.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {
    List<Payee> findByUser(User user);
    
}
