package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class Transaction {

int transactionId;
int senderId;
int recipientId;
@Min(0)
BigDecimal amount;

    public Transaction(int transactionId, int senderId, int recipientId, BigDecimal amount) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }
    public Transaction() {

    }

    public int getTransactionId() {return transactionId;}

    public void setTransactionId(int transactionId) {this.transactionId = transactionId;}

    public int getSenderId() {return senderId;}

    public void setSenderId(int senderId) {this.senderId = senderId;}

    public int getRecipientId() {return recipientId;}

    public void setRecipientId(int recipientId) {this.recipientId = recipientId;}

    public BigDecimal getAmount() {return amount;}

    public void setAmount(BigDecimal amount) {this.amount = amount;}
}
