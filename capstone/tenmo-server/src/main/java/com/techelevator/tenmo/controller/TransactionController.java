package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionDao transactionDao;

    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }


    @PostMapping("")
    public boolean sendTransaction(@RequestParam int senderId, @RequestParam int recipientId, @RequestParam BigDecimal amount) {
        return this.transactionDao.sendTransaction(senderId, recipientId, amount);}

}
