package com.agravain.tz.AccountFactory;

import com.agravain.tz.Account.Account;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class AccountFactoryImpl implements AccountFactory {


    @Override
    public ConcurrentHashMap<Integer, Account> createAccounts(int count) {
        ConcurrentHashMap<Integer, Account> container =
                new ConcurrentHashMap<Integer, Account>();
        for (int i = 0; i < count; i++) {

            Account account = new Account();

            Random random = new Random();

            String generatedString =
                    random.ints(97, 123)
                    .limit(5)
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            account.setId(generatedString);
            container.put(i, account);
        }
        if(container.isEmpty())
            return null;
        return container;
    }
}
