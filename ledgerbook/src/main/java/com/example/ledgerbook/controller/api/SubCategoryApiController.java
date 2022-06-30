package com.example.ledgerbook.controller.api;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.CategoryApiRequest;
import com.example.ledgerbook.model.network.request.SubCategoryApiRequest;
import com.example.ledgerbook.model.network.response.CategoryApiResponse;
import com.example.ledgerbook.model.network.response.SubCategoryApiResponse;
import com.example.ledgerbook.service.CategoryApiLogicService;
import com.example.ledgerbook.service.SubCategoryApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryApiController implements CrudInterface<SubCategoryApiRequest, SubCategoryApiResponse> {

    @Autowired
    SubCategoryApiLogicService subCategoryApiLogicService;

    @Override
    @PostMapping("")
    public Header<SubCategoryApiResponse> create(@RequestBody Header<SubCategoryApiRequest> request) {
        return subCategoryApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<SubCategoryApiResponse> read(@PathVariable(name = "id") Long id) {
        return subCategoryApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<SubCategoryApiResponse> update(@RequestBody Header<SubCategoryApiRequest> request) {
        return subCategoryApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable(name="id") Long id) {
        return subCategoryApiLogicService.delete(id);
    }
}
