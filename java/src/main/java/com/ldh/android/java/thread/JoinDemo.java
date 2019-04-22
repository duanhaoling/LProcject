package com.ldh.android.java.thread;


/**
 * desc:
 * Created by ldh on 2019/1/25.
 */
public class JoinDemo {


    public static void main(String[] args) {
        Worker worker1 = new Worker("work-1");
        Worker worker2 = new Worker("work-2");
        worker1.start();
        try {
            //调用work1的join函数，主线程会阻塞直到worker1执行完成
            System.out.println("启动线程1");
            worker1.join();
            //再启动线程2，并且调用线程2的join函数，主线程会阻塞直到worker2执行完成
            System.out.println("启动线程2");
            worker2.start();
            worker2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程继续执行");
    }


    static class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }


        public void run() {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("work in " + getName());
        }
    }


}