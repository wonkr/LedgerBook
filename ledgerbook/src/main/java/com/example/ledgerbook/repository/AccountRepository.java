package com.example.ledgerbook.repository;

import com.example.ledgerbook.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByName(String name);

    Account getReferenceByName(String name);
}
