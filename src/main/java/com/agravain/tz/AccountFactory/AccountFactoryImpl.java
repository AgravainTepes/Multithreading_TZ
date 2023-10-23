package com.agravain.tz.AccountFactory;

import com.agravain.tz.Account.Account;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountFactoryImpl implements AccountFactory {


    @Override
    public ConcurrentHashMap<Integer, Account> createAccounts(int count) {
        ConcurrentHashMap<Integer, Account> container =
                new ConcurrentHashMap<Integer, Account>();
        for (int i = 0; i < count; i++) {



            Random random = new Random();

            String generatedString =
                    random.ints(97, 123)
                    .limit(5)
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            Account account = new Account(generatedString,
                    new AtomicInteger(10000));
            container.put(i, account);
        }
        if(container.isEmpty())
            return null;
        return container;
    }
}
