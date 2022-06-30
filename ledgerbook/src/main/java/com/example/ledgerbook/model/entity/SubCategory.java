package com.example.ledgerbook.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Accessors(chain = true)
@ToString(exclude = {"transactionList", "budget", "category"})
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Transaction> transactionList;

    @OneToOne
    private Budget budget;

    @ManyToOne
    private Category category;

}
