package com.performanceengineering;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads2 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.execute(new Task2());
        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class Task2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread id: " + Thread.currentThread().getId());
    }

}
