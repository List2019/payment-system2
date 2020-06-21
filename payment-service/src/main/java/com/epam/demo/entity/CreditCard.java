package com.epam.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "credit_card")
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard{

    @Column(name = "id_users")
    @Getter
    @Setter
    private long idUsers;

    @Id
    @Column(name = "number_card")
    @Getter
    @Setter
    private long numberCard;

    @Column(name = "balance")
    @Getter
    @Setter
    private BigDecimal balance;

    @Column(name = "block")
    @Getter
    @Setter
    private boolean block;

}

