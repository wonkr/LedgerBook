package com.example.ledgerbook.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountApiResponse {
    private Long id;
    private String name;
    private String currency;
    private BigDecimal initialBalance;
    private BigDecimal balance;
}
