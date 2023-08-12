package com.payment_system.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "blocked")
    private boolean isBlocked;

    @ManyToOne

    @JsonBackReference
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Override
    public String toString() {
        return "Account{" +
                "number='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
