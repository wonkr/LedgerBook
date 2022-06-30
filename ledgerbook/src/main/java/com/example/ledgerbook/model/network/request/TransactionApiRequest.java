package com.example.ledgerbook.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionApiRequest {
    private Long id;
    private LocalDate date;
    private String type;
    private String description;
    private BigDecimal amount;
    private String detailedDescription;
    private Long accountId;
    private Long categoryId;

    private Long subCategoryId;

}
