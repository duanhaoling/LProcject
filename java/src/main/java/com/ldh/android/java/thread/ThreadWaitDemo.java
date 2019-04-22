package com.ldh.android.java.thread;

/**
 * desc:
 * Created by ldh on 2019/1/25.
 */
public class ThreadWaitDemo {
    private static Object sLockkObject = new Object();

    static void waitAndNotifyAll() {
        System.out.println("主线程运行");
        //创建并启动子线程
        Thread thread = new WaitThread();
        thread.start();
        long startTime = System.currentTimeMillis();
        try {
            synchronized (sLockkObject) {
                System.out.println("主线程等待");
                sLockkObject.wait();
            }
        } catch (Exception e) {

        }
        long timsMs = (System.currentTimeMillis() - startTime);
        System.out.println("主线程继续-》等待耗时：" + timsMs + " ms");
    }


    static class WaitThread extends Thread {
        @Override
        public void run() {
            try {
                synchronized (sLockkObject) {
                    Thread.sleep(3000);
                    sLockkObject.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        waitAndNotifyAll();
    }
}
