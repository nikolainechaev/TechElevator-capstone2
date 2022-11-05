package com.techelevator.tenmo.dao;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public interface AccountDao {

    BigDecimal getBalanceByUserId (int id);

}
