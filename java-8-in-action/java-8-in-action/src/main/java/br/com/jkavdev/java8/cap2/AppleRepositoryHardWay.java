package br.com.jkavdev.java8.cap2;

import java.util.Arrays;
import java.util.List;

public class AppleRepositoryHardWay {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 75),
                new Apple("red", 85),
                new Apple("yellow", 80));

        System.out.println("Green: " + AppleRepository.filterGreenApples(apples));
        System.out.println("Red: " + AppleRepository.filterRedApples(apples));
        System.out.println("Heavy: " + AppleRepository.filterHeavyApples(apples));

        System.out.println("Red: " + AppleRepository.filterApples(apples, "red"));
        System.out.println("Green: " + AppleRepository.filterApples(apples, "green"));
        System.out.println("Heavy: " + AppleRepository.filterApples(apples, 150));

        System.out.println("Red: " + AppleRepository.filterApplesHorribleWay(apples, "red", 95, false));
        System.out.println("Yellow: " + AppleRepository.filterApplesHorribleWay(apples, "yellow", 95, true));

    }

}
