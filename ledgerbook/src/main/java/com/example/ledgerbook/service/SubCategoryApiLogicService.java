package com.example.ledgerbook.service;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.entity.Budget;
import com.example.ledgerbook.model.entity.Category;
import com.example.ledgerbook.model.entity.SubCategory;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.CategoryApiRequest;
import com.example.ledgerbook.model.network.request.SubCategoryApiRequest;
import com.example.ledgerbook.model.network.response.CategoryApiResponse;
import com.example.ledgerbook.model.network.response.SubCategoryApiResponse;
import com.example.ledgerbook.repository.BudgetRepository;
import com.example.ledgerbook.repository.CategoryRepository;
import com.example.ledgerbook.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class SubCategoryApiLogicService implements CrudInterface<SubCategoryApiRequest, SubCategoryApiResponse> {

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Header<SubCategoryApiResponse> create(Header<SubCategoryApiRequest> request) {
        SubCategoryApiRequest subCategoryApiRequest = request.getData();


        SubCategory subCategory = SubCategory.builder()
                .name(subCategoryApiRequest.getName())
                .type(subCategoryApiRequest.getType())
                .build();

        if (!isNull(subCategoryApiRequest.getBudgetId())) {
            Optional<Budget> budgetOptional = budgetRepository.findById(subCategoryApiRequest.getBudgetId());
            if (budgetOptional.isEmpty())
                return Header.ERROR("budget with the budget_id does not exist");
            subCategory.setBudget(budgetOptional.get());
        }

        if(isNull(subCategoryApiRequest.getCategoryId())) {
            return Header.ERROR("CategoryId must be assigned");
        }

        Optional<Category> categoryOptional = categoryRepository.findById(subCategoryApiRequest.getCategoryId());

        if (categoryOptional.isEmpty()) {
            return Header.ERROR("category with the category_id does not exist");
        }

        subCategory.setCategory(categoryOptional.get());

        return response(subCategoryRepository.save(subCategory));
    }

    @Override
    public Header<SubCategoryApiResponse> read(Long id) {

        return subCategoryRepository.findById(id)
                .map(subCategory -> response(subCategory))
                .orElseGet(()-> Header.ERROR("No data"));
    }

    @Override
    public Header<SubCategoryApiResponse> update(Header<SubCategoryApiRequest> request) {
        SubCategoryApiRequest subCategoryApiRequest = request.getData();
        Optional<SubCategory> optional = subCategoryRepository.findById(subCategoryApiRequest.getId());

        return optional.map(subCategory -> {
                    subCategory.setName(subCategoryApiRequest.getName())
                    .setType(subCategoryApiRequest.getType());

            budgetRepository.findById(subCategoryApiRequest.getBudgetId())
                    .map(budget -> subCategory.setBudget(budget));
            categoryRepository.findById(subCategoryApiRequest.getCategoryId())
                    .map(category->subCategory.setCategory(category));
            return subCategory;
        })
                .map(subCategory -> subCategoryRepository.save(subCategory))
                .map(newCategory -> response(newCategory))
                .orElseGet(()->Header.ERROR("No data"));
    }

    @Override
    public Header delete(Long id) {

        return subCategoryRepository.findById(id)
                .map(subCategory -> {
                    subCategoryRepository.delete(subCategory);
                    return Header.OK("OK");
                })
                .orElseGet(()->Header.ERROR("No data"));
    }

    private Header<SubCategoryApiResponse> response(SubCategory subCategory){
        SubCategoryApiResponse subCategoryApiResponse = SubCategoryApiResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .type(subCategory.getType())
                .categoryId(subCategory.getCategory().getId())
                .build();

        if(!isNull(subCategory.getBudget()))
            subCategoryApiResponse.setBudgetId(subCategory.getBudget().getId());

        return Header.OK(subCategoryApiResponse);

    }
}
