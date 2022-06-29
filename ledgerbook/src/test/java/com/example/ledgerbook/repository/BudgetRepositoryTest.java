package com.example.ledgerbook.repository;

import com.example.ledgerbook.LedgerbookApplicationTests;
import com.example.ledgerbook.model.entity.Budget;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class BudgetRepositoryTest extends LedgerbookApplicationTests {

    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void create(){
        Budget budget = new Budget();
        budget.setAmount(BigDecimal.valueOf(100));
        categoryRepository.findByName("Shopping").ifPresent(c->{
            budget.setCategory(c);
        });
        budget.setPeriod("Monthly");
        budget.setStartAt(LocalDate.of(2022, 6, 1));

        budgetRepository.save(budget);
    }

    @Test
    @Transactional
    public void read(){
        Budget budget = budgetRepository.getReferenceById(1L);
        System.out.println(budget.getCategory().getName());
        System.out.println(budget.getAmount());
        budget.getCategory().getTransactionList().stream().forEach(transaction -> {
            System.out.println(transaction.getDescription());
            System.out.println(transaction.getAmount());
        });

    }
}
