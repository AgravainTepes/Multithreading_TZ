package com.agravain.tz;


import com.agravain.tz.Account.Account;
import com.agravain.tz.AccountFactory.AccountFactory;
import com.agravain.tz.AccountFactory.AccountFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private final static Logger logger = LogManager.getLogger(App.class);
    public static void transaction(Account firstAccount, Account secondAccount) {
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

    public static void main(String[] args) throws InterruptedException {
        AccountFactory factory = new AccountFactoryImpl();
        ConcurrentHashMap<Integer, Account> container =
                factory.createAccounts(4);
        ExecutorService executorService =
                Executors.newFixedThreadPool(2);

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
                System.out.println(container);


            }
        };
        for (int i = 0; i < 15; i++) {

            executorService
                    .execute(runnable);
            executorService
                    .execute(runnable);
        }
        executorService.shutdown();
        executorService.awaitTermination(40, TimeUnit.SECONDS);

    }
}
