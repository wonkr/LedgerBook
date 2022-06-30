package com.example.ledgerbook.controller.api;

import com.example.ledgerbook.ifs.CrudInterface;
import com.example.ledgerbook.model.network.Header;
import com.example.ledgerbook.model.network.request.TransactionApiRequest;
import com.example.ledgerbook.model.network.response.TransactionApiResponse;
import com.example.ledgerbook.service.TransactionApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/transaction")
public class TransactionApiController implements CrudInterface<TransactionApiRequest, TransactionApiResponse> {

    @Autowired
    TransactionApiLogicService transactionApiLogicService;
    @Override
    @PostMapping("")
    public Header<TransactionApiResponse> create(@RequestBody Header<TransactionApiRequest> request) {
        log.info("create: {}", request);
        return transactionApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<TransactionApiResponse> read(@PathVariable(name="id") Long id) {
        log.info("read: {}",id);
        return transactionApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<TransactionApiResponse> update(@RequestBody Header<TransactionApiRequest> request) {
        log.info("update: {}", request);
        return transactionApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable(name="id") Long id) {
        log.info("delete: {}", id);
        return transactionApiLogicService.delete(id);
    }
}
