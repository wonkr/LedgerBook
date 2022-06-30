package com.example.ledgerbook.repository;

import com.example.ledgerbook.LedgerbookApplicationTests;
import com.example.ledgerbook.model.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountRepositoryTest extends LedgerbookApplicationTests {

    @Autowired
    AccountRepository accountRepository;

    @Test
    @Transactional
    public void create(){

        String name = "CHASE";
        BigDecimal initialBalance = BigDecimal.valueOf(10000);
        BigDecimal balance = BigDecimal.valueOf(10000);
        String currency = "USD";

        Account account = new Account();
        account.setName(name);
        account.setInitialBalance(initialBalance);
        account.setBalance(balance);
        account.setCurrency(currency);

        Account newAccount = accountRepository.save(account);

        Assertions.assertNotNull(newAccount);
        Assertions.assertEquals(newAccount.getBalance(), balance);
        Assertions.assertEquals(newAccount.getCurrency(), currency);
        Assertions.assertEquals(newAccount.getInitialBalance(), initialBalance);
        Assertions.assertEquals(newAccount.getName(), name);

    }

    @Test
    @Transactional
    public void read(){
        Optional<Account> optionalAccount = accountRepository.findByName("CHASE");

        optionalAccount.ifPresent(a -> {
            System.out.println(a.getId());
            System.out.println(a.getName());
            System.out.println(a.getCurrency());
            System.out.println(a.getBalance());
            System.out.println(a.getInitialBalance());

            a.getTransactionList().stream().forEach(transaction ->{
                System.out.println(transaction.getDescription());
                System.out.println(transaction.getCategory().getName());
                System.out.println(transaction.getAmount());

            });
        });

    }
}
