package io.sandy.sortexample;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class SortBucketMultiThreadingLambda implements Runnable {

    private ArrayList<Integer> numbers;
    private CountDownLatch countDownLatch;
    private Consumer<ArrayList> sortMethod;

//  SortBucket method accepts Sort function as Lambda function and uses it to sort the list of numbers.

    public SortBucketMultiThreadingLambda(ArrayList<Integer> numbers, Consumer<ArrayList> sortMethod, CountDownLatch countDownLatch) {
        this.numbers = numbers;
        this.sortMethod = sortMethod;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        sortMethod.accept(numbers);
        countDownLatch.countDown();
    }
}
