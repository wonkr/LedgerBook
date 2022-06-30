package com.example.ledgerbook.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"category"})
@Builder
@Accessors(chain = true)
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal amount;

    private String period;

    private LocalDate startAt;

    @OneToOne(mappedBy = "budget")
    private Category category;

    @OneToOne(mappedBy = "budget")
    private SubCategory subCategory;
}
