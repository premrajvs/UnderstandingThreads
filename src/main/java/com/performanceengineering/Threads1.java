package com.performanceengineering;
// Line 2 developer 1 Only change
public class Threads1 {
// Line 4 developer 2 change : Merge conflict resolved by keeping developer 2 change in same line
// Line 4 developer 1 change : and adding developer 1 change in next line

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new Task());
            t1.start();
        }
    }
// Line 11 developer 1 Only change
}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread id: " + Thread.currentThread().getId());

    }

}
//Developer 1 making a change in line 23 : To resolve merge conflict, I am keeping my change in line 23
//developer 2 making a change in line 23 : To resolve merge conflict, I am keeping developer 2 change to line 24
