package com.example.ledgerbook.service;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.entity.Budget;
import com.example.ledgerbook.model.entity.Category;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.BudgetApiRequest;
import com.example.ledgerbook.model.network.request.CategoryApiRequest;
import com.example.ledgerbook.model.network.response.BudgetApiResponse;
import com.example.ledgerbook.model.network.response.CategoryApiResponse;
import com.example.ledgerbook.repository.BudgetRepository;
import com.example.ledgerbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BudgetRepository budgetRepository;
    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();


        Category category = Category.builder()
                .name(categoryApiRequest.getName())
                .type(categoryApiRequest.getType())
                .build();

        if (!isNull(categoryApiRequest.getBudgetId())) {
            return budgetRepository.findById(categoryApiRequest.getBudgetId())
                    .map(budget -> {
                        category.setBudget(budget);
                        return category;
                    }).map(newCategory -> categoryRepository.save(category))
                    .map(newCategory -> response(newCategory))
                    .orElseGet(() -> Header.ERROR("budget id does not exist"));
        }
        return response(categoryRepository.save(category));
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {

        return categoryRepository.findById(id)
                .map(category -> response(category))
                .orElseGet(()-> Header.ERROR("No data"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        Optional<Category> optional = categoryRepository.findById(categoryApiRequest.getId());

        return optional.map(category -> {
            category.setName(categoryApiRequest.getName())
                    .setType(categoryApiRequest.getType());

            budgetRepository.findById(categoryApiRequest.getBudgetId())
                    .map(budget -> category.setBudget(budget));
            return category;
        })
                .map(category -> categoryRepository.save(category))
                .map(newCategory -> response(newCategory))
                .orElseGet(()->Header.ERROR("No data"));
    }

    @Override
    public Header delete(Long id) {

        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return Header.OK("OK");
                })
                .orElseGet(()->Header.ERROR("No data"));
    }

    private Header<CategoryApiResponse> response(Category category){
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .build();

        if(!isNull(category.getBudget()))
            categoryApiResponse.setBudgetId(category.getBudget().getId());

        return Header.OK(categoryApiResponse);

    }
}
