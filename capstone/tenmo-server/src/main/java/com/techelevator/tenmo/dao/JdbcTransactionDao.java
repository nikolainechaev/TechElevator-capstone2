package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
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

        String sql1 = "INSERT INTO transaction (sender_id, recipient_id, amount) VALUES (?, ?, ?) RETURNING transaction_id;";

        Transaction transaction;
        try {
            transaction = this.jdbcTemplate.queryForObject(sql1, Transaction.class, senderId, recipientId, amount);
            return transaction;
        } catch (DataAccessException e){
            System.out.println("Bad Request");
        }



        String sql2 = "UPDATE account SET balance = (balance - ?) WHERE account_id = ? \n;" +
                    "\tUPDATE account SET balance = (balance + ?) WHERE account_id = ?;";
        try {
            this.jdbcTemplate.update(sql2, amount, senderId, amount, recipientId);
        } catch (DataAccessException e) {
            System.out.println("Bad Request");
        }
        return null;
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
