package com.agravain.tz.PropertiesLoader;

import com.agravain.tz.ThreadAndTaskManager.ThreadAndTaskManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {
    public PropertiesLoader() {
        Properties properties = getProperties();
        this.transactionsNumbers =
                getIntValue(properties,"transactions.numbers");
        this.accountsNumbers =
                getIntValue(properties,"accounts.numbers");
        this.threadsNumbers =
                getIntValue(properties,"threads.numbers");
        this.initMoney =
                getIntValue(properties,"money.defaultValue");
    }

    private static String propertiesPath = "src/main/resources/application.properties";
    private final static Logger logger = LogManager.getLogger(ThreadAndTaskManager.class);

    private final int transactionsNumbers;
    private final int accountsNumbers;
    private final int threadsNumbers;
    private final int initMoney;
    private static Properties getProperties() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(propertiesPath));
        } catch (IOException e) {
            logger.error("Properties file is not found.");
        }
        return properties;


    }
    private static int getIntValue(Properties properties, String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public int getTransactionsNumbers() {
        return transactionsNumbers;
    }

    public int getAccountsNumbers() {
        return accountsNumbers;
    }

    public int getThreadsNumbers() {
        return threadsNumbers;
    }

    public int getInitMoney() {
        return initMoney;
    }
}