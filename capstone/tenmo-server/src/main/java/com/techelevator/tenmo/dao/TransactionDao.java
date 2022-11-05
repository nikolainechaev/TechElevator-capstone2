package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component

public interface TransactionDao {
    Transaction sendTransaction(int senderId, int recipientId, BigDecimal amount);

    List <Transaction> allTransactions(int userId);
}


