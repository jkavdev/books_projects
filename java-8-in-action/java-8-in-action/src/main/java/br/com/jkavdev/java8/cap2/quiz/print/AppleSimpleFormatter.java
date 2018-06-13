package br.com.jkavdev.java8.cap2.quiz.print;

import br.com.jkavdev.java8.cap2.Apple;

public class AppleSimpleFormatter implements AppleFormatter {

    @Override
    public String accept(Apple a) {
        return "A apple of " + a.getWeight() + "g";
    }
}
