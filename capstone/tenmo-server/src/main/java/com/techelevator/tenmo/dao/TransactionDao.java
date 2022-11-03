package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {
    List<Transaction> getAllUsers();
    boolean sendTransaction(int senderId, int recipientId, BigDecimal amount);
}
