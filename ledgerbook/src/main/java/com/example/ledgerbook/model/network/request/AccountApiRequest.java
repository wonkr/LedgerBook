package com.example.ledgerbook.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountApiRequest {
    private Long id;
    private String name;
    private BigDecimal initialBalance;
    private BigDecimal balance;
    private String currency;
}
