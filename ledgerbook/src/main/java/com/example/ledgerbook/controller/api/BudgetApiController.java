package com.example.ledgerbook.controller.api;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.BudgetApiRequest;
import com.example.ledgerbook.model.network.response.BudgetApiResponse;
import com.example.ledgerbook.service.BudgetApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/budget")
public class BudgetApiController implements CrudInterface<BudgetApiRequest, BudgetApiResponse>  {

    @Autowired
    BudgetApiLogicService budgetApiLogicService;

    @Override
    @PostMapping("")
    public Header<BudgetApiResponse> create(@RequestBody Header<BudgetApiRequest> request) {
        log.info("create: {}", request);
        return budgetApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<BudgetApiResponse> read(@PathVariable(name="id") Long id) {
        log.info("read: {}", id);
        return budgetApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<BudgetApiResponse> update(@RequestBody Header<BudgetApiRequest> request) {
        log.info("update: {}", request);
        return budgetApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable(name="id") Long id) {
        log.info("delete: {}", id);
        return budgetApiLogicService.delete(id);
    }
}
