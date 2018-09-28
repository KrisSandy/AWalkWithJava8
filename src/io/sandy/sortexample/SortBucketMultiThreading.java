package io.sandy.sortexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

// Below SortBucketMultiThreading class accepts an ArrayList and CountDownLatch which maintains the count of threads yet to complete
// After completion of SortBucketMultiThreading for ech thread, count of countDownLatch is decremented, indicating
// the thread has completed successfully.

public class SortBucketMultiThreading implements Runnable {

    private ArrayList<Integer> numbers;
    private CountDownLatch countDownLatch;

    public SortBucketMultiThreading(ArrayList<Integer> numbers, CountDownLatch countDownLatch) {
        this.numbers = numbers;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        Collections.sort(numbers);
        countDownLatch.countDown();
    }
}