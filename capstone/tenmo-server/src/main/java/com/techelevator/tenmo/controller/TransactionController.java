package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionDao transactionDao;
    private AccountDao accountDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }


    @PostMapping("")
    public Transaction sendTransaction(@RequestParam int senderId, @RequestParam int recipientId, @RequestParam @Valid BigDecimal amount) {
        if (senderId == recipientId) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiving account cannot be same as sending account.");}
        else if (this.accountDao.getBalanceByAccountId(senderId).compareTo(amount) < 0) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds.");}
        else System.out.println("Approved");
        return this.transactionDao.sendTransaction(senderId, recipientId, amount);}
}
