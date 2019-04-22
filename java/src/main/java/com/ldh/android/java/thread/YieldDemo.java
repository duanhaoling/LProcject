package com.ldh.android.java.thread;

/**
 * desc:
 * Created by ldh on 2019/1/25.
 */
public class YieldDemo {


    public static void main(String[] args) {
        YieldThread t1 = new YieldThread("thread-1");
        YieldThread t2 = new YieldThread("thread-2");
        t1.start();
        t2.start();
    }


    static class YieldThread extends Thread {
        private static final int MAX = 5;

        public YieldThread(String name) {
            super(name);
        }

        @Override
        public synchronized void run() {
            for (int i = 0; i < MAX; i++) {
                System.out.printf("%s [%d] ---->  %d\n", this.getName(), this.getPriority(), i);
                //当i为2时，调用当前线程的yield函数
                if (i == 2) {
                    Thread.yield();
                }
            }
        }
    }
}
