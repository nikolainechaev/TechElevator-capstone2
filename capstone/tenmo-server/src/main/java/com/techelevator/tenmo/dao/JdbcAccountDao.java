package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;

public class JdbcAccountDao implements AccountDao{
private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getBalance(String username, String password) {
        final String sql = "SELECT balance\n" +
                "FROM account\n" +
                "WHERE user_id = (SELECT user_id FROM tenmo_user WHERE username LIKE ? AND password_hash LIKE ?);";
        BigDecimal balance = null;


    //    SqlRowSet results = this.jdbcTemplate.queryForObject(sql,   );
    //    if (results.next()) {
    //        balance = this.mapRowToAccount(results);
    //    }
        return balance;
    }
    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setUserId(rs.getInt("user_id"));
        account.setAccountId(rs.getInt("account_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }


}
