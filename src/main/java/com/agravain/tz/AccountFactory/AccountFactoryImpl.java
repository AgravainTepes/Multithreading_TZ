package com.agravain.tz.AccountFactory;

import com.agravain.tz.Account.Account;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountFactoryImpl implements AccountFactory {
    private final AtomicInteger startupAccountBalance =
            new AtomicInteger(10000);

    @Override
    public String generateUUID(int i) {
        return UUID.randomUUID().toString() + "||A" + (i + 1);
    }

    @Override
    public ConcurrentHashMap<Integer, Account> createAccounts(int count) {
        ConcurrentHashMap<Integer, Account> container =
                new ConcurrentHashMap<Integer, Account>();
        for (int i = 0; i < count; i++) {

            Random random = new Random();

            String generatedString = generateUUID(i);

            Account account = new Account(generatedString,
                    startupAccountBalance);
            container.put(i, account);
        }
        if(container.isEmpty())
            return null;
        return container;
    }
}
