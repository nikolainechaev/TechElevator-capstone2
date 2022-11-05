package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class JdbcAccountDao implements AccountDao{
private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getBalanceByUserId(int id) {
        final String sql = "SELECT balance\n" +
                            "FROM account\n" +
                           "WHERE user_id = ?";

    BigDecimal balance = this.jdbcTemplate.queryForObject(sql, BigDecimal.class, id);
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
