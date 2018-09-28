package io.sandy.functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class E3PersonEval2 {

    public static void printPerson(E3Person p) {
        System.out.println(p.toString());
    }

    public static void main(String[] args) {

        List<E3Person> people = Arrays.asList(
                new E3Person("python", "programming", 20),
                new E3Person("Java", "Beans", 60),
                new E3Person("C", "Language", 200),
                new E3Person("Ruby", "Rails", 10)
        );

//        Sorting the collection based on age

        Collections.sort(people, Comparator.comparing(E3Person::getAge));

        System.out.println("* printing all values *");

        people
                .stream()
                .forEach(E3PersonEval2::printPerson);

        System.out.println("* printing people whose last name starts with L *");

        people
                .stream()
                .filter(p -> p.getLastName().startsWith("L"))
                .forEach(System.out::println);

    }
}
