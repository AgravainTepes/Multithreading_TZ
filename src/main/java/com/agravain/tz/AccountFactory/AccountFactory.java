package com.agravain.tz.AccountFactory;

import com.agravain.tz.Account.Account;

import java.util.concurrent.ConcurrentHashMap;

public interface AccountFactory {

ConcurrentHashMap<Integer, Account> createAccounts(int count);
}
