package com.example.ledgerbook.model.network.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryApiRequest {
    private Long id;
    private String name;
    private String type;

    private Long categoryId;

    private Long budgetId;
}
