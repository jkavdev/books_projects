package br.com.jkavdev.java8.cap2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AppleRepositorySortNiceWay {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 75),
                new Apple("red", 85),
                new Apple("yellow", 80));

        //=====================Usando comparators

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        System.out.println("Sorteadas: " + apples);
        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println("Sorteadas: " + apples);

    }

}
