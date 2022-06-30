package com.example.ledgerbook.repository;

import com.example.ledgerbook.LedgerbookApplicationTests;
import com.example.ledgerbook.model.entity.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class TransactionRepositoryTest extends LedgerbookApplicationTests {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void create(){
        String type = "expense";
        LocalDate date = LocalDate.now();
        String description = "headphone";
        BigDecimal amount = BigDecimal.valueOf(299.99);

        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDate(date);
        transaction.setDescription((description));
        transaction.setAmount(amount);
        transaction.setAccount(accountRepository.getReferenceByName("CHASE"));
        transaction.setCategory(categoryRepository.getReferenceByName("Shopping"));

        Transaction newTransaction = transactionRepository.save(transaction);

        Assertions.assertNotNull(newTransaction);
        Assertions.assertEquals(newTransaction.getType(), type);
        Assertions.assertEquals(newTransaction.getAmount(), amount);
        Assertions.assertEquals(newTransaction.getDate(), date);
        Assertions.assertEquals(newTransaction.getDescription(), description);



    }

    @Test
    @Transactional
    public void read(){

        Optional<Transaction> optionalTransaction = transactionRepository.findByDate(LocalDate.now());

        optionalTransaction.ifPresent(t -> {
            System.out.println(t.getId());
            System.out.println(t.getDate());
            System.out.println(t.getType());
            System.out.println(t.getDescription());
            System.out.println(t.getAmount());
            System.out.println(t.getCategory().getName());
            System.out.println(t.getAccount().getName());
        });


    }
}
