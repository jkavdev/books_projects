package br.com.jkavdev.java8.cap2.quiz.print;

import br.com.jkavdev.java8.cap2.Apple;
import br.com.jkavdev.java8.cap2.AppleRepository;
import br.com.jkavdev.java8.cap2.quiz.print.AppleFancyFormatter;
import br.com.jkavdev.java8.cap2.quiz.print.AppleSimpleFormatter;

import java.util.Arrays;
import java.util.List;

public class ApplePrintQuiz {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 75),
                new Apple("red", 85),
                new Apple("yellow", 80));

        AppleRepository.preetyPrintApples(apples, new AppleFancyFormatter());
        AppleRepository.preetyPrintApples(apples, new AppleSimpleFormatter());

    }

}
