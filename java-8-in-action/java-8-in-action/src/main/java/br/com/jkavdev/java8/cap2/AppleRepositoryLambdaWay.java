package br.com.jkavdev.java8.cap2;

import java.util.Arrays;
import java.util.List;

public class AppleRepositoryLambdaWay {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 75),
                new Apple("red", 85),
                new Apple("yellow", 80));
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println("Green: " + AppleRepository.filterApples(apples, (Apple a) -> "green".equalsIgnoreCase(a.getColor())));
        System.out.println("Red: " + AppleRepository.filterApples(apples, (Apple a) -> "red".equalsIgnoreCase(a.getColor())));
        System.out.println("Heavy: " + AppleRepository.filterApples(apples, (Apple a) -> 150 == a.getWeight()));

        System.out.println("Green Generic: " + AppleRepository.filterGeneric(apples, (Apple a) -> "green".equalsIgnoreCase(a.getColor())));
        System.out.println("Red Generic: " + AppleRepository.filterGeneric(apples, (Apple a) -> "red".equalsIgnoreCase(a.getColor())));
        System.out.println("Heavy Generic: " + AppleRepository.filterGeneric(apples, (Apple a) -> 150 == a.getWeight()));

        System.out.println("Generic Filter: " + AppleRepository.filterGeneric(integers, (Integer i) -> i % 2 == 0));
        System.out.println("Generic Filter: " + AppleRepository.filterGeneric(integers, (Integer i) -> i % 2 != 0));

    }

}
