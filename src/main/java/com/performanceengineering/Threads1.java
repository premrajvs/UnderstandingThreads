package com.performanceengineering;

public class Threads1 {
// Line 4 developer 2 change
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new Task());
            t1.start();
        }
    }

}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread id: " + Thread.currentThread().getId());

    }

}
//Developer 1 making a change in line 23 : To resolve merge conflict, I am keeping my change in line 23
//developer 2 making a change in line 23 : To resolve merge conflict, I am keeping developer 2 change to line 24
