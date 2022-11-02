package com.techelevator.tenmo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping

public class UserController {


@GetMapping
    public BigDecimal getBalanceById (int accountId) {
    return this.userDao.getBalanceById();
}


}
