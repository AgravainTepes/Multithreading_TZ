package com.agravain.tz.Account;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Account {

    public Account(String id, AtomicInteger balance) {
        this.id = id;
        this.balance = balance;
    }

    private String id;
    private AtomicInteger balance;

    public String getId() {
        return id;
    }

    public AtomicInteger getBalance() {
        return balance;
    }

    public void setBalance(AtomicInteger balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }


}
