package com.demo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bank_card")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankCard {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "card_number")
    private long cardNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "blocked")
    private boolean isBlocked;
}
