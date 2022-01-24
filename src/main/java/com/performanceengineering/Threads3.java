package com.performanceengineering;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class Threads3 {

    public static void main(String[] args) {
        CustomExecutorService es = CustomExecutors.CustomThreadPool(3);
        for (int i = 0; i < 1000; i++) {
            es.execute(new Task3());
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Task3 implements Runnable {
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

class CustomExecutorService implements Executor {
    static int capacity;
    static int currentCapacity;
    static LinkedBlockingQueue<Runnable> linkedBlockingQueue;
    Execution e;

    public CustomExecutorService(int capacity) {
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
        if (CustomExecutorService.currentCapacity < CustomExecutorService.capacity) {
            // System.out.println("CustomExecutorService Capacity : " +
            // CustomExecutorService.capacity);
            // System.out.println("CustomExecutorService.currentCapacity : " +
            // CustomExecutorService.currentCapacity);
            CustomExecutorService.currentCapacity++;
            Thread t = new Thread(new Execution());
            t.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (CustomExecutorService.linkedBlockingQueue.size() != 0) {
                // System.out.println("LinkedBlockingQueue Size : " +
                // CustomExecutorService.linkedBlockingQueue.size());
                CustomExecutorService.linkedBlockingQueue.poll().run();
            }
        }
    }
}

class CustomExecutors {
    int capacity;

    static CustomExecutorService CustomThreadPool(int capacity) {
        return new CustomExecutorService(capacity);
    }
}