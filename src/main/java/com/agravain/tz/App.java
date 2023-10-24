package com.agravain.tz;

import com.agravain.tz.ThreadAndTaskManager.ThreadAndTaskManager;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ThreadAndTaskManager threadAndTaskManager = new ThreadAndTaskManager();
        threadAndTaskManager.run();
    }
}
