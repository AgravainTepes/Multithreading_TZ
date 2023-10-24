package com.agravain.tz.ThreadAndTaskManager;

import com.agravain.tz.Account.Account;
import com.agravain.tz.AccountFactory.AccountFactory;
import com.agravain.tz.AccountFactory.AccountFactoryImpl;
import com.agravain.tz.PropertiesLoader.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadAndTaskManager {
    private PropertiesLoader propLoader = new PropertiesLoader();
    private final static Logger logger =
            LogManager.getLogger(ThreadAndTaskManager.class);
    private AccountFactory factory = new AccountFactoryImpl();
    private ConcurrentHashMap<Integer, Account> container =
            factory.createAccounts(propLoader.getAccountsNumbers());

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long Delay =
                    ThreadLocalRandom
                            .current()
                            .nextInt(1000, 2000);
            try {
                Thread.sleep(Delay);

            } catch (InterruptedException e) {
                logger.info(e.getMessage());
                e.printStackTrace();
            }
            Integer firstAccountKey =
                    ThreadLocalRandom
                            .current()
                            .nextInt(0, container.size());
            Integer secondAccountKey =
                    ThreadLocalRandom
                            .current()
                            .nextInt(0, container.size());

            while (firstAccountKey == secondAccountKey) {
                secondAccountKey =
                        ThreadLocalRandom
                                .current()
                                .nextInt(0, container.size());
            }

            Account firstAccount = container.get(firstAccountKey);
            Account secondAccount = container.get(secondAccountKey);
            transaction(firstAccount, secondAccount);
            container.put(firstAccountKey, firstAccount);
            container.put(secondAccountKey, secondAccount);
        }
    };

    private void transaction(Account firstAccount, Account secondAccount) {
        logger.info("Transaction for accounts : " + firstAccount.getId() +
                " with balance:" + firstAccount.getBalance() + " and " +
                secondAccount.getId() + " with balance:" +
                secondAccount.getBalance() + " starts.");
        int firstBalance = firstAccount
                .getBalance()
                .get();
        int secondBalance = secondAccount
                .getBalance()
                .get();
        int victim = ThreadLocalRandom
                .current()
                .nextInt(0, 2);

        if (victim == 0) {
            int transactionValue =
                    ThreadLocalRandom
                            .current()
                            .nextInt(0, firstBalance);
            firstAccount
                    .setBalance(new AtomicInteger(
                            firstBalance - transactionValue));
            secondAccount
                    .setBalance(new AtomicInteger(
                            secondBalance + transactionValue));
            logger.info(firstAccount.getId() + " transfers " +
                    transactionValue + " to the " + secondAccount.getId());
        } else {
            int transactionValue =
                    ThreadLocalRandom
                            .current()
                            .nextInt(0, secondBalance);
            firstAccount
                    .setBalance(new AtomicInteger(
                            firstBalance + transactionValue));
            secondAccount
                    .setBalance(new AtomicInteger(
                            secondBalance - transactionValue));
            logger.info(secondAccount.getId() + " transfers " +
                    transactionValue + " to the " + firstAccount.getId());
        }
        logger.info("state of accounts at the end of the transaction : " +
                firstAccount.getId() +
                " balance:" + firstAccount.getBalance() + " and " +
                secondAccount.getId() + " balance:" +
                secondAccount.getBalance() + ". Transaction ends. ");

    }

    public void run() {
        AccountFactory factory =
                new AccountFactoryImpl();
        AtomicInteger transactionCount =
                new AtomicInteger(0);
        ExecutorService executorService =
                Executors.newFixedThreadPool(propLoader.getThreadsNumbers());

       while (transactionCount.incrementAndGet() <= propLoader.getTransactionsNumbers()) {

            executorService
                    .execute(runnable);

        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(40, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Termination failed!");
        }
    }
}
