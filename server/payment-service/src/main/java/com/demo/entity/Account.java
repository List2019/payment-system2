package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @Column(name = "account_number")
    private String number;

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
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
