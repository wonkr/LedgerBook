package com.example.ledgerbook.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BudgetApiRequest {
    private Long id;

    private String name;
    private BigDecimal amount;
    private String period;
    private LocalDate startAt;
}
