package io.sandy.multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting thread " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class E3ThreadPools {

    public static void main(String[] args) {

//        Initiate the threads with fixed number of pools.

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 1; i < 6; i++) {
            executorService.submit(new Processor(i));
        }

        executorService.shutdown();

        System.out.println("All Threads submitted");

    }
}
