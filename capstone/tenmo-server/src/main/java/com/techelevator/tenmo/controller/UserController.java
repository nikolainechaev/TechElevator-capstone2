package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    private TransactionDao transactionDao;

    public UserController(UserDao userDao, TransactionDao transactionDao) {
        this.userDao = userDao;
        this.transactionDao = transactionDao;
    }

    @GetMapping
    List<User> findAll() {
        return this.userDao.findAll();
    }

    @GetMapping("/{id}/balance")
    BigDecimal getBalanceByUserId(@PathVariable Long id) {
        return this.userDao.getBalanceByUserId(id);
    }

@GetMapping("/{id}/transaction")
    List<Transaction> allTransactionsByUserId(@PathVariable int userId) {
        return this.transactionDao.allTransactions(userId);
}

}
