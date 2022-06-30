package com.example.ledgerbook.controller.api;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.AccountApiRequest;
import com.example.ledgerbook.model.network.response.AccountApiResponse;
import com.example.ledgerbook.service.AccountApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountApiController implements CrudInterface<AccountApiRequest, AccountApiResponse> {
    @Autowired
    private AccountApiLogicService accountApiLogicService;

    @Override
    @PostMapping("")
    public Header<AccountApiResponse> create(@RequestBody Header<AccountApiRequest> request) {
        log.info("{}", request);
        return accountApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<AccountApiResponse> read(@PathVariable(name="id") Long id) {
        log.info("read id : {}", id);
        return accountApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<AccountApiResponse> update(@RequestBody Header<AccountApiRequest> request) {
        log.info("update: {}", request);
        return accountApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable(name="id") Long id) {
        log.info("delete id : {}", id);
        return accountApiLogicService.delete(id);
    }
}
