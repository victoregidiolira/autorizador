package com.vito.autorizador.controller;

import com.vito.autorizador.dto.TransactionRequest;
import com.vito.autorizador.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> authorize(@RequestBody TransactionRequest request){
        String status = transactionService.authorize(request);

        if (status.equals("APPROVED")){
            return ResponseEntity.ok(status);
        } else {
            return  ResponseEntity.status(422).body(status);
        }
    }

}
