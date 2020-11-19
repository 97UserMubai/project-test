package com.boot.thread;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/11/16 17:29
 * <h></h>
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        Thread thread1 = new Thread(new MyThread2());
        thread.start();
        thread1.start();
        System.out.println("我是不用等待的输出");
    }
}

class MyThread extends Thread {
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是三秒后的输出");
    }
}

class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("我是run接口");
    }
}
