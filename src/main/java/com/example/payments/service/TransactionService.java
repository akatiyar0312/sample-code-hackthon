package com.example.payments.service;

import com.example.payments.model.Transaction;
import com.example.payments.model.Account;
import com.example.payments.model.Payee;
import com.example.payments.model.User;
import com.example.payments.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final PayeeService payeeService;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountService accountService,
                              PayeeService payeeService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.payeeService = payeeService;
    }

    public Transaction initiateTransaction(User user, Long accountId, Long payeeId, BigDecimal amount, LocalDate scheduledFor) {
        Account fromAccount = accountService.getAccountById(accountId);
        Payee payee = payeeService.getPayeeById(payeeId);

        Transaction tx = new Transaction();
        tx.setUser(user);
        tx.setFromAccount(fromAccount);
        tx.setPayee(payee);
        tx.setAmount(amount);
        tx.setScheduledFor(scheduledFor);
        tx.setStatus("PENDING");
        return transactionRepository.save(tx);
    }

    public Transaction confirmTransaction(Transaction tx) {
        tx.setStatus("CONFIRMED");
        tx.setConfirmedAt(java.time.Instant.now());
        return transactionRepository.save(tx);
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}
