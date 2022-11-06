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
        } catch (DataAccessException e) {
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

    public List<Transaction> allTransactions(Long userId) {
        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT transaction_id, sender_id, recipient_id, amount\n" +
                     "FROM transaction as t\n" +
                     "JOIN account AS a ON t.sender_id = a.account_id\n" +
                     "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            transactions.add(mapRowToTransaction(results));

        }
        return transactions;
    }

    public Transaction getTransaction (int transactionId) {

        String sql = "SELECT transaction_id, sender_id, recipient_id, amount \n" +
                     "FROM transaction \n" +
                     "WHERE transaction_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transactionId);
        Transaction transaction = null;
        if (result.next()) {
            transaction = mapRowToTransaction(result);
        }
        return transaction;

    }


    public Transaction mapRowToTransaction(SqlRowSet rowSet) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rowSet.getInt("transaction_id"));
        transaction.setSenderId(rowSet.getInt("sender_id"));
        transaction.setRecipientId(rowSet.getInt("recipient_id"));
        transaction.setAmount(rowSet.getBigDecimal("amount"));
        return transaction;

    }
}
