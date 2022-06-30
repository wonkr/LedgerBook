package com.example.ledgerbook.service;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.entity.Account;
import com.example.ledgerbook.model.entity.Category;
import com.example.ledgerbook.model.entity.SubCategory;
import com.example.ledgerbook.model.entity.Transaction;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.TransactionApiRequest;
import com.example.ledgerbook.model.network.response.TransactionApiResponse;
import com.example.ledgerbook.repository.AccountRepository;
import com.example.ledgerbook.repository.CategoryRepository;
import com.example.ledgerbook.repository.SubCategoryRepository;
import com.example.ledgerbook.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionApiLogicService implements CrudInterface<TransactionApiRequest, TransactionApiResponse> {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public Header<TransactionApiResponse> create(Header<TransactionApiRequest> request) {
        TransactionApiRequest transactionApiRequest = request.getData();

        Transaction transaction = Transaction.builder()
                .date(transactionApiRequest.getDate())
                .type(transactionApiRequest.getType())
                .description(transactionApiRequest.getDescription())
                .amount(transactionApiRequest.getAmount())
                .detailedDescription(transactionApiRequest.getDetailedDescription())
                .build();

        if (transactionApiRequest.getAccountId()!=null)
            accountRepository.findById((transactionApiRequest.getAccountId()))
                    .map(account -> transaction.setAccount(account));
        if (transactionApiRequest.getCategoryId()!=null)
            categoryRepository.findById(transactionApiRequest.getCategoryId())
                    .map(category -> transaction.setCategory(category));
        if (transactionApiRequest.getSubCategoryId()!=null)
            subCategoryRepository.findById(transactionApiRequest.getSubCategoryId())
                    .map(subCategory -> transaction.setSubCategory(subCategory));

        return response(transactionRepository.save(transaction));
    }

    @Override
    public Header<TransactionApiResponse> read(Long id) {

        return transactionRepository.findById(id)
                .map(transaction -> response(transaction))
                .orElseGet(()-> Header.ERROR("No data"));
    }

    @Override
    public Header<TransactionApiResponse> update(Header<TransactionApiRequest> request) {

        TransactionApiRequest transactionApiRequest = request.getData();
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionApiRequest.getId());

        return transactionOptional.map(transaction->{
            transaction.setDate(transactionApiRequest.getDate())
                    .setType(transactionApiRequest.getType())
                    .setDescription(transactionApiRequest.getDescription())
                    .setAmount(transactionApiRequest.getAmount())
                    .setDetailedDescription(transactionApiRequest.getDetailedDescription());

            if (transactionApiRequest.getAccountId()!=null)
                accountRepository.findById((transactionApiRequest.getAccountId()))
                                .map(account -> transaction.setAccount(account));
            if (transactionApiRequest.getCategoryId()!=null)
                categoryRepository.findById(transactionApiRequest.getCategoryId())
                                .map(category -> transaction.setCategory(category));
            if (transactionApiRequest.getSubCategoryId()!=null)
                subCategoryRepository.findById(transactionApiRequest.getSubCategoryId())
                        .map(subCategory -> transaction.setSubCategory(subCategory));

            return transaction;
        })
                .map(transaction -> transactionRepository.save(transaction))
                .map(newTransaction -> response(newTransaction))
                .orElseGet(()-> Header.ERROR("No Data"));
    }

    @Override
    public Header delete(Long id) {

        return transactionRepository.findById(id)
                .map(transaction -> {
                    transactionRepository.delete(transaction);
                    return Header.OK("OK");
                })
                .orElseGet(()-> Header.ERROR("No data"));
    }

    private Header<TransactionApiResponse> response(Transaction transaction) {
        TransactionApiResponse transactionApiResponse = TransactionApiResponse.builder()
                .id(transaction.getId())
                .date(transaction.getDate())
                .type(transaction.getType())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .detailedDescription(transaction.getDetailedDescription())
                .accountId(transaction.getAccount().getId())
                .build();
        if(transaction.getCategory()!=null)
            transactionApiResponse.setCategoryId(transaction.getCategory().getId());
        if(transaction.getSubCategory()!=null)
            transactionApiResponse.setSubCategoryId(transaction.getSubCategory().getId());
        return Header.OK(transactionApiResponse);
    }
}
