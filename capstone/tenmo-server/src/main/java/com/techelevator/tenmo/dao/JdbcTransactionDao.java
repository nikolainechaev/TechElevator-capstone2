package com.techelevator.tenmo.dao;

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
    public List<Transaction> getAllUsers() {
        final String sql = "SELECT account_id FROM account";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        List<Transaction> recipientList = new ArrayList<>();
        while (results.next()) {
            Transaction t = mapRowToTransaction(results);
            recipientList.add(t);
        }
        return recipientList;
    }

    @Override
    public boolean sendTransaction(int senderId, int recipientId, BigDecimal amount) {

        if (recipientId == senderId || amount.compareTo(BigDecimal.ZERO) <= 0) { return false;}
        else {
            String sql =
                    "BEGIN TRANSACTION;\n" +
                            "\n" +
                            "\tINSERT INTO transaction VALUES (?, ?, ?) RETURNING transaction_id;\n" +
                            "\n" +
                            "\tUPDATE account SET balance - (SELECT amount FROM transaction WHERE account_id = ?) WHERE account_id = \n" +
                            "\t\t(SELECT sender_id FROM transaction WHERE sender_id = ?);\n" +
                            "\t\t\n" +
                            "\tUPDATE account SET balance + (SELECT amount FROM transaction WHERE account_id = ?) WHERE account_id =\n" +
                            "\t\t(SELECT recipient_id FROM transaction WHERE recipient_id = ?);\n" +
                            "\t\t\n" +
                            "COMMIT;";

            this.jdbcTemplate.update(sql, senderId, recipientId, amount, senderId, senderId, recipientId, recipientId);
        }
        return true;
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
