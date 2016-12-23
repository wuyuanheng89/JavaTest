package com.example;

import java.util.Objects;

/**
 * Created by wu on 16-12-24.
 */

public class ThreadUtil {

    public static class ThreadTester{
        int count;
        int maxConCurrent;
        Runnable worker;
        volatile int currentCount;

        public ThreadTester(int count, int maxConCurrent, Runnable worker){
            if (count <= 0 ){
                throw new IllegalArgumentException("count must large than 0");
            }
            if (maxConCurrent <= 0){
                throw new IllegalArgumentException("concurrent threads count must large than 0");
            }
            this.count = count;
            this.maxConCurrent = maxConCurrent;
            this.worker = worker;
        }

        public void test(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Object countLock = new Object();
                    for(int i=0; i<count; i++){
                        if(currentCount >= maxConCurrent){
                            try{
                                synchronized (countLock) {
                                    countLock.wait();
                                    System.out.println("==>wait()");
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                worker.run();
                                currentCount--;
                                try {
                                    synchronized (countLock) {
                                        countLock.notify();
                                        System.out.println("==>resume()");
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        currentCount++;
                    }
                }
            }, "launcher").start();

        }

    }

    public static void main(String[] args){
        new ThreadTester(1000, 10, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "==" + this);
            }
        }).test();
    }
}
