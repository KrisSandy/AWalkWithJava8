package io.sandy.functional;

import java.util.stream.IntStream;

public class E1Prime {

    public static void main(String[] args) {
        System.out.println(isPrime(6));
    }

    private static boolean isPrime(int number) {

//		Instead of writing the loop and checking for each occurrence where programming takes more
//		emphasis than functionality, below programming style can be used which clearly tells the
//		actual function.

//      The function inside noneMatch is called lambda function
//
//      A lambda function is an implementation of function which is passed directly instead of placing the function
//      in a class and passing the object and calling the method of object.
//      Lambad functions are processed immediately and no need of classes

        return number > 1 &&
                IntStream.range(2, number)
                        .noneMatch(i -> number % i == 0);
    }

}