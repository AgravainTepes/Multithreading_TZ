package com.agravain.tz.Account;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Account {
    private String id;
    private AtomicInteger balance = new AtomicInteger(10000);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
