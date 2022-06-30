package com.example.ledgerbook.repository;

import com.example.ledgerbook.LedgerbookApplicationTests;
import com.example.ledgerbook.model.entity.Budget;
import com.example.ledgerbook.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CategoryRepositoryTest extends LedgerbookApplicationTests {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void create(){
        String type = "expense";
        String name = "Shopping";
        Category category = new Category();
        category.setType(type);
        category.setName(name);

        Category newCategory = categoryRepository.save(category);

        Assertions.assertNotNull(newCategory);

        Assertions.assertEquals(newCategory.getName(),name);
        Assertions.assertEquals(newCategory.getType(), type);


    }

    @Test
    @Transactional
    public void read(){
        Category category = categoryRepository.getReferenceById(1L);

    }
}
