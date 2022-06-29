package com.example.ledgerbook.service;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.entity.Account;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.AccountApiRequest;
import com.example.ledgerbook.model.network.response.AccountApiResponse;
import com.example.ledgerbook.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Header<AccountApiResponse> update(Header<AccountApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
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
