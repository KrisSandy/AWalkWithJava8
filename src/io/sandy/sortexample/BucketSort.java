package io.sandy.sortexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BucketSort {

    public static void main(String[] args) {
        int[] numbers = new int[]{68, 47, 11, 83, 57, 75, 80, 92, 84, 69, 50, 18, 32, 68, 29, 69, 96, 34, 100, 45};
        System.out.println("Before Sort : " + Arrays.toString(numbers));
        bucketSort(numbers, 7);
        System.out.println("After Sort : " + Arrays.toString(numbers));
    }

    public static int[] bucketSort(int[] numbers, int bucketCount) {

        if (numbers.length <= 1) return numbers;

        int maxVal = numbers[0];
        int minVal = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxVal) maxVal = numbers[i];
            if (numbers[i] < minVal) minVal = numbers[i];
        }

        double interval = ((double)(maxVal - minVal + 1)) / bucketCount; // range of bucket
        ArrayList<Integer> buckets[] = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) // initialize buckets (initially empty)
            buckets[i] = new ArrayList<Integer>();

        for (int i = 0; i < numbers.length; i++) // distribute numbers to buckets
            buckets[(int)((numbers[i] - minVal)/interval)].add(numbers[i]);

        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            Collections.sort(buckets[i]);
// calls Java's built-in merge sort (as a kind of “helper” sort)
            for (int j = 0; j < buckets[i].size(); j++) { // update array with the bucket content
                numbers[k] = buckets[i].get(j);
                k++;
            }
        }
        return numbers;
    }
}
