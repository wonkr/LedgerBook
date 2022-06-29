package com.example.ledgerbook.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"transactionList"})
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal initialBalance;

    private BigDecimal balance;

    private String currency;

    // account : transaction = 1:N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Transaction> transactionList;
}
