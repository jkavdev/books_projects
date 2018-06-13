package br.com.jkavdev.java8.cap2;

import br.com.jkavdev.java8.cap2.predicate.AppleGreenColorPredicate;
import br.com.jkavdev.java8.cap2.predicate.AppleHeavyWeightPredicate;
import br.com.jkavdev.java8.cap2.predicate.ApplePredicate;
import br.com.jkavdev.java8.cap2.predicate.AppleRedColorPredicate;

import java.util.Arrays;
import java.util.List;

public class AppleRepositoryNiceWay {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 75),
                new Apple("red", 85),
                new Apple("yellow", 80));

        System.out.println("Green: " + AppleRepository.filterApples(apples, new AppleGreenColorPredicate()));
        System.out.println("Red: " + AppleRepository.filterApples(apples, new AppleRedColorPredicate()));
        System.out.println("Heavy: " + AppleRepository.filterApples(apples, new AppleHeavyWeightPredicate()));

        System.out.println("Green: " + AppleRepository.filterApples(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return "green".equalsIgnoreCase(a.getColor());
            }
        }));
        System.out.println("Red: " + AppleRepository.filterApples(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return "red".equalsIgnoreCase(a.getColor());
            }
        }));
        System.out.println("Heavy: " + AppleRepository.filterApples(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return 150 == a.getWeight();
            }
        }));

    }

}
