package com.github.druyaned.learn_java.vol1.chapter06;

import static com.github.druyaned.ConsoleColors.bold;
import java.util.*;
import java.util.function.*;

public class TestLambda {
    public static void stringComparison() {
        System.out.println(bold("\nTesting lambda"));

        /*
         * There are 3 types of method links:
         * - "object::method". [System.out::println] : (x) -> System.out.println(x)
         * - "class::static_mothod". [Math::pow] : (x, y) -> Math.pow(x, y)
         * - "class::method". [String::compareTo] : (x, y) -> x.compareTo(y)
         */
        
        String[] names = new String[] {"carlos", "Dan", "Benjamin"};

        System.out.println("\n Sorting #0 (ignore case with method link):");
        Arrays.sort(names, String::compareToIgnoreCase); // method link
        for (String name : names) {System.out.println(name);}

        System.out.println("\n Sorting #1 (by length with length comparator):");
        Arrays.sort(names, new LengthComparator());
        for (String name : names) {System.out.println(name);}

        System.out.println("\n Sorting #2 (by length with lambda):");
        Arrays.sort(names, (name1, name2) -> Integer.compare(name1.length(), name2.length()));
        for (String name : names) {System.out.println(name);}

        System.out.println("\n Sorting #3 (basic):");
        Arrays.sort(names);
        for (String name : names) {System.out.println(name);}
    }

    public static void methodLinks() {
        System.out.println(bold("\nTesting method links and some Comparator's methods"));

        String[] names = new String[] {"Carlos", "Dan", "Benjamin", "Frederic"};
        Arrays.sort(names, Comparator.comparingInt(String::length));

        // To test constroctor links
        class InstanceMaker<T> {
            private T arg;

            InstanceMaker(T arg) {this.arg = arg;}

            <R> R make(Function<T, R> function) {return function.apply(arg);}
        }

        class ArrayMaker {
            private final int size;

            ArrayMaker(int size) {this.size = size;}
            
            <R> R make(IntFunction<R> intFunction) {return intFunction.apply(size);}
        }

        // Constructor links
        AgedPerson[] persons;
        ArrayMaker arrayMaker = new ArrayMaker(names.length);
        // Way 1
        persons = arrayMaker.make((int size) -> new AgedPerson[size]);
        // Way 2: constructor link
        persons = arrayMaker.make(AgedPerson[]::new);
        persons = new AgedPerson[names.length];

        for (int i = 0; i < persons.length; ++i) {persons[i] = new AgedPerson(names[i]);}
        
        InstanceMaker<String> instanceMaker = new InstanceMaker<>("###");
        // Way 1
        persons[persons.length - 1] = instanceMaker.make(name -> new AgedPerson(name));
        // Way 2: constructor link
        persons[persons.length - 1] = instanceMaker.make(AgedPerson::new);

        // Way 1
        Arrays.sort(persons, Comparator
            .comparingInt((AgedPerson p) -> p.getName().length())
            .thenComparingInt(AgedPerson::getAge));
        System.out.println("\n AgedPersons:");
        for (AgedPerson person : persons) {person.show();}
        // Way 2
        persons[persons.length - 1] = new AgedPerson(null);
        Arrays.sort(persons, Comparator
            .comparing(AgedPerson::getName, Comparator
                .nullsFirst((s1, s2) -> s1.length() - s2.length()))
            .thenComparingInt((AgedPerson p) -> -p.getAge())); // reversed int
        System.out.println("\n AgedPersons:");
        for (AgedPerson person : persons) {person.show();}
    }
}

class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {return s1.length() - s2.length();}
}
