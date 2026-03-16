package com.vito.autorizador.controller;

import com.vito.autorizador.dto.TransactionRequest;
import com.vito.autorizador.dto.TransactionResponse;
import com.vito.autorizador.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> authorize(@RequestBody TransactionRequest request){
        TransactionResponse status = transactionService.authorize(request);

        if (status.getStatus().equals("APPROVED")){
            return ResponseEntity.ok(status);
        } else {
            return  ResponseEntity.status(422).body(status);
        }
    }

}
