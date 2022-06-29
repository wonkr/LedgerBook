package com.example.ledgerbook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"account", "category"})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String type;

    private String description;

    private BigDecimal amount;

    private String detailedDescription;

    // Transaction : Account = N : 1
    @ManyToOne
    private Account account;

    @ManyToOne
    private Category category;
}
