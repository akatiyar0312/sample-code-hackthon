package com.example.payments.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "payees")
public class Payee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payeeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String sortCode;

    @Column(nullable = false)
    private boolean isInternal;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public void setInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    // Getters and Setters
    
}
