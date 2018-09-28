package io.sandy.multithreading;

public class E2Syncronize {

//    This is a classic example where two threads are trying to read and write same variable. In such cases the output
//    is highly unreliable. Hence the synchronize word should be used to such scenarios which doesn't allow other
//    thread to read when its in used by using internal locking mechanism.

    private int count;

    public synchronized void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {

        E2Syncronize e2Syncronize = new E2Syncronize();
        e2Syncronize.doWork();
    }

    public void doWork() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count is " + count);

    }
}
