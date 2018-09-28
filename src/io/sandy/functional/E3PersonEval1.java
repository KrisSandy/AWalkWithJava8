package io.sandy.functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class E3PersonEval1 {

    public static void main(String[] args) {

        List<E3Person> people = Arrays.asList(
                new E3Person("python", "programming", 20),
                new E3Person("Java", "Beans", 60),
                new E3Person("C", "Language", 200),
                new E3Person("Ruby", "Rails", 10)
        );

//        Sort list by last name

        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));

//        method to print all elements in list

        System.out.println("* Print all elements *");
        printAllElements(people, p ->true, p -> System.out.println(p.toString()));

        System.out.println("* Print elements whose firstname starts with R *");
        printAllElements(people, p -> p.getFirstName().startsWith("R"), p -> System.out.println(p.getLastName()));

    }

//    Predicate and Consumer are few of out of the box interfaces which come with Java 8 and can be used as default interfaces.

    private static void printAllElements(List<E3Person> people, Predicate<E3Person> personPredicate, Consumer<E3Person> personConsumer) {

        for (E3Person person : people) {
            if (personPredicate.test(person)) {
                personConsumer.accept(person);
            }
        }
    }
}
