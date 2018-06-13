package br.com.jkavdev.java8.cap2.quiz.print;

import br.com.jkavdev.java8.cap2.Apple;

public class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple a) {
        String caracteristica = a.getWeight() > 150 ? "heavy" : "light";
        return "A " + caracteristica + " " + a.getWeight() + "g apple";
    }
}
