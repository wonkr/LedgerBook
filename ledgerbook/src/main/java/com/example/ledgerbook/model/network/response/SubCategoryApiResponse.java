package com.example.ledgerbook.model.network.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryApiResponse {
    private Long id;
    private String name;
    private String type;

    private Long budgetId;

    private Long categoryId;

}
