package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Transaction sendTransaction(int senderId, int recipientId, BigDecimal amount) {
//"transfer amount must be greater than 0 argument": amount.compareTo(BigDecimal.ZERO) <= 0 ||

        if (recipientId == senderId || amount.compareTo(sender.getBalance(senderId)) < 0)
        else {
            String sql =

                            "INSERT INTO transaction (sender_id, recipient_id, amount) VALUES (?, ?, ?) RETURNING transaction_id;\n" +
                            "\n" +
                            "\tUPDATE account SET balance = (balance - ?) WHERE account_id = ? \n;" +
                            "\tUPDATE account SET balance = (balance + ?) WHERE account_id = ? \n;";

            this.jdbcTemplate.update(sql, senderId, recipientId, amount, amount, senderId, amount, recipientId);
        }

    }


    private Transaction mapRowToTransaction(SqlRowSet rowSet) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rowSet.getInt("transaction_id"));
        transaction.setSenderId(rowSet.getInt("sender_id"));
        transaction.setRecipientId(rowSet.getInt("recipient_id"));
        transaction.setAmount(rowSet.getBigDecimal("amount"));
        return transaction;

    }
}
