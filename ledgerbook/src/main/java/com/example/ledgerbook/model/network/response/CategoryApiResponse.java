package com.example.ledgerbook.model.network.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiResponse {
    private Long id;
    private String name;
    private String type;

    private Long categoryId;
    private Long budgetId;

}
