package com.vito.autorizador.service;

import com.vito.autorizador.dto.TransactionRequest;
import com.vito.autorizador.model.Account;
import com.vito.autorizador.model.Transaction;
import com.vito.autorizador.repository.AccountRepository;
import com.vito.autorizador.repository.TransactionRepository;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository){

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;

    }

    private void saveTransaction(Account account,BigDecimal amount, String merchant, String status) {

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setMerchant(merchant);
        transaction.setStatus(status);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

    }

    public String authorize(TransactionRequest request){
        Optional<Account> optionalAccount = accountRepository.findById(request.getAccountId());

        if (optionalAccount.isEmpty()){

            saveTransaction(null, request.getAmount(),request.getMerchant(),"REJECTED");
            return "REJECTED: ACCOUNT NOT FOUND.";
        }

        Account account = optionalAccount.get();

        if (account.getBalance().compareTo(request.getAmount()) < 0){
            saveTransaction(account, request.getAmount(),request.getMerchant(),"REJECTED");
            return "REJECTED: INSUFFICIENT FUNDS.";
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        accountRepository.save(account);

        saveTransaction(account,request.getAmount(), request.getMerchant(), "APPROVED");
        return "APPROVED";

    }

}
