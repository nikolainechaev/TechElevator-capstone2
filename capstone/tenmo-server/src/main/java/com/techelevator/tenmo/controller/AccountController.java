package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping

public class AccountController {

private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping("/account/{id}")
    public BigDecimal getBalanceByUserId (@PathVariable int id) {
    return this.accountDao.getBalanceByUserId(id);
}


}
