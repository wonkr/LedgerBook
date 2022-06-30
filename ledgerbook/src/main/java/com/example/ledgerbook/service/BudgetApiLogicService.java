package com.example.ledgerbook.service;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.entity.Budget;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.BudgetApiRequest;
import com.example.ledgerbook.model.network.response.BudgetApiResponse;
import com.example.ledgerbook.repository.BudgetRepository;
import com.example.ledgerbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetApiLogicService implements CrudInterface<BudgetApiRequest, BudgetApiResponse> {

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public Header<BudgetApiResponse> create(Header<BudgetApiRequest> request) {
        BudgetApiRequest budgetApiRequest = request.getData();


        Budget budget = Budget.builder()
                    .name(budgetApiRequest.getName())
                    .amount(budgetApiRequest.getAmount())
                    .period(budgetApiRequest.getPeriod())
                    .startAt(budgetApiRequest.getStartAt())
                    .build();

        return response(budgetRepository.save(budget));

    }

    @Override
    public Header<BudgetApiResponse> read(Long id) {

        return budgetRepository.findById(id)
                .map(budget -> response(budget))
                .orElseGet(()->Header.ERROR("No data"));
    }

    @Override
    public Header<BudgetApiResponse> update(Header<BudgetApiRequest> request) {
        BudgetApiRequest budgetApiRequest = request.getData();
        return budgetRepository.findById(budgetApiRequest.getId())
                .map(budget -> {
                    budget.setName(budgetApiRequest.getName())
                            .setAmount(budgetApiRequest.getAmount())
                            .setStartAt(budgetApiRequest.getStartAt())
                            .setPeriod(budgetApiRequest.getPeriod());
                    return budget;
                })
                .map(budget -> budgetRepository.save(budget))
                .map(newBudget -> response(newBudget))
                .orElseGet(()->Header.ERROR("No data"));
    }

    @Override
    public Header delete(Long id) {

        return budgetRepository.findById(id)
                .map(budget -> {
                    budgetRepository.delete(budget);
                    return Header.OK("OK");
                })
                .orElseGet(()-> Header.ERROR("No data"));
    }

    private Header<BudgetApiResponse> response(Budget budget) {
        BudgetApiResponse budgetApiResponse = BudgetApiResponse.builder()
                .id(budget.getId())
                .name(budget.getName())
                .amount(budget.getAmount())
                .period(budget.getPeriod())
                .startAt(budget.getStartAt())
                .build();

        return  Header.OK(budgetApiResponse);
    }
}
