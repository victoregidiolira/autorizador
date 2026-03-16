package com.vito.autorizador.service;

import com.vito.autorizador.dto.TransactionRequest;
import com.vito.autorizador.dto.TransactionResponse;
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

    private TransactionResponse buildResponse(String status, Long accountId, BigDecimal amount, BigDecimal previousBalance, BigDecimal currentBalance){

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setStatus(status);
        transactionResponse.setAccountId(accountId);
        transactionResponse.setAmount(amount);
        transactionResponse.setPreviousBalance(previousBalance);
        transactionResponse.setCurrentBalance(currentBalance);

        return transactionResponse;
    }


    public TransactionResponse authorize(TransactionRequest request){
        Optional<Account> optionalAccount = accountRepository.findById(request.getAccountId());

        if (optionalAccount.isEmpty()){

            saveTransaction(null, request.getAmount(),request.getMerchant(),"REJECTED");
            return buildResponse("REJECTED: ACCOUNT NOT FOUND.", request.getAccountId(), request.getAmount(), null, null);
        }

        Account account = optionalAccount.get();
        BigDecimal previousBalance = account.getBalance();

        if (account.getBalance().compareTo(request.getAmount()) < 0){
            saveTransaction(account, request.getAmount(),request.getMerchant(),"REJECTED");
            return buildResponse("REJECTED: INSUFFICIENT FUNDS.", account.getId(), request.getAmount(),previousBalance,previousBalance);
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        accountRepository.save(account);

        saveTransaction(account,request.getAmount(), request.getMerchant(), "APPROVED");
        return buildResponse("APPROVED", account.getId(), request.getAmount(), previousBalance, account.getBalance());

    }

}
