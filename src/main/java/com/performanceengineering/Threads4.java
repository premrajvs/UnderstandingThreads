package com.performanceengineering;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Threads4 {

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(
                5, // CorePoolSize
                10, // MaxPoolSize
                120, // KeepAliveTime
                TimeUnit.SECONDS, // Time out Unit
                new ArrayBlockingQueue<Runnable>(10) // Queue Type
        );

        for (int i = 0; i < 50; i++) {
            es.execute(new Task4());
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Task4 implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            System.out.println("Thread id: " + Thread.currentThread().getId());
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}