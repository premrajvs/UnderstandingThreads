package com.performanceengineering;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class Threads5 {

    public static void main(String[] args) {
        CustomThreadpool es = new CustomThreadpool(3);
        for (int i = 0; i < 1000; i++) {
            es.execute(new Task5());
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Task5 implements Runnable {
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

class CustomThreadpool implements Executor {
    static int capacity;
    static int currentCapacity;
    static LinkedBlockingQueue<Runnable> linkedBlockingQueue;
    Execution e;

    public CustomThreadpool(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        linkedBlockingQueue = new LinkedBlockingQueue<Runnable>();
        e = new Execution();
    }

    @Override
    public void execute(Runnable r) {
        linkedBlockingQueue.add(r);
        e.executeMyMethod();
    }
}

class Execution implements Runnable {
    void executeMyMethod() {
        if (CustomThreadpool.currentCapacity < CustomThreadpool.capacity) {
            System.out.println("CustomThreadpool Capacity : " +
                    CustomThreadpool.capacity);
            System.out.println("CustomThreadpool.currentCapacity : " +
                    CustomThreadpool.currentCapacity);
            CustomThreadpool.currentCapacity++;
            Thread t = new Thread(new Execution());
            t.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (CustomThreadpool.linkedBlockingQueue.size() != 0) {
                // System.out.println("LinkedBlockingQueue Size : " +
                // CustomThreadpool.linkedBlockingQueue.size());
                CustomThreadpool.linkedBlockingQueue.poll().run();
            }
        }
    }
}

class CustomExecutors {
    int capacity;

    static CustomThreadpool CustomThreadPool(int capacity) {
        return new CustomThreadpool(capacity);
    }
}