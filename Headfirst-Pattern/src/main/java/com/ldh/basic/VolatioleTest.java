package com.ldh.basic;

/**
 * volatile 不保证原子性
 * Created by ldh on 2018/4/17.
 */
public class VolatioleTest {
    private volatile int anInt = 0;

    public void increase() {
        anInt++;
    }

    public static void main(String[] args) {

        VolatioleTest test = new VolatioleTest();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.increase();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(test.anInt);

    }

}
