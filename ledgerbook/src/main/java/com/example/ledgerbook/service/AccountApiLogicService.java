package com.example.ledgerbook.service;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.entity.Account;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.AccountApiRequest;
import com.example.ledgerbook.model.network.response.AccountApiResponse;
import com.example.ledgerbook.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountApiLogicService implements CrudInterface<AccountApiRequest, AccountApiResponse> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Header<AccountApiResponse> create(Header<AccountApiRequest> request) {
        AccountApiRequest accountApiRequest = request.getData();

        Account account = Account.builder()
                .name(accountApiRequest.getName())
                .initialBalance(accountApiRequest.getInitialBalance())
                .balance(accountApiRequest.getInitialBalance())
                .currency(accountApiRequest.getCurrency())
                .build();

        Account newAccount = accountRepository.save(account);


        return response(newAccount);
    }

    @Override
    public Header<AccountApiResponse> read(Long id) {

        return accountRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(()->Header.ERROR("No Data with id : "+id));

    }

    @Override
    public Header<AccountApiResponse> update(Header<AccountApiRequest> request) {
        AccountApiRequest accountApiRequest = request.getData();

        Optional<Account> optional = accountRepository.findById(accountApiRequest.getId());

        return optional.map(account -> {
            account.setName(accountApiRequest.getName())
                    .setBalance(accountApiRequest.getBalance())
                    .setInitialBalance(accountApiRequest.getInitialBalance())
                    .setCurrency(accountApiRequest.getCurrency());
            return account;
        })
                .map(account -> accountRepository.save(account))
                .map(updateAccount -> response(updateAccount))
                .orElseGet(()->Header.ERROR("No data"));

    }

    @Override
    public Header delete(Long id) {
        Optional<Account> optional = accountRepository.findById(id);

        return optional.map(account -> {
            accountRepository.delete(account);
            return Header.OK();
        })
                .orElseGet(()-> Header.ERROR("No Data"));
    }

    private Header<AccountApiResponse> response(Account account){
        AccountApiResponse accountApiResponse = AccountApiResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .initialBalance(account.getInitialBalance())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .build();

        return Header.OK(accountApiResponse);
    }
}
