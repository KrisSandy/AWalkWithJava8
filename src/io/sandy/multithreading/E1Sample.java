package io.sandy.multithreading;

class Runner implements Runnable {

    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println("i = " + i);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


}

public class E1Sample {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());

        t1.start();
        t2.start();
    }


}
