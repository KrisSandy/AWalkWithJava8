package io.sandy.functional;

import java.util.Arrays;
import java.util.List;

public class E2Eval {

    public static boolean isGreaterThan3(int x) {
        return x > 3;
    }

    public static boolean isEven(int x) {
        return (x % 2 == 0);
    }

    public static int doubleIt(int x) {
        return x * 2;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        List<Integer> values = Arrays.asList(1,2,3,5,4,6,7,8,9);

//		In traditional programming below will be implemented in loops and if conditions. But in function all the
//		programming is minimized to single functional statements and sequence of events.
//
//		Functions inside the filters are called lambda functions. So effectively we are passing
//		method implementation rather than variables or objects.

        System.out.println(values
                .stream()
                .filter(x -> x > 3)
                .filter(x -> x % 2 == 0)
                .map(x -> x * 2)
                .findFirst()
                .get()
        );

//		The lambda functions can be further converted to real functions for readability

        System.out.println(values
                .stream()
                .filter(E2Eval::isGreaterThan3)
                .filter(E2Eval::isEven)
                .map(E2Eval::doubleIt)
                .findFirst()
        );

//		The above returns a result of Optional[8], because it does a lazy evaluation and doesn't
//		really execute the code until its necessary.

    }

}

