package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")

public class AccountController {

private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping("/{id}")
    public BigDecimal getBalanceByAccountId (@PathVariable int accountId) {
    return this.accountDao.getBalanceByAccountId(accountId);
}


}
