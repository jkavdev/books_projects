package br.com.jkavdev.java8.cap2.predicate;

import br.com.jkavdev.java8.cap2.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple a) {
        return "green".equalsIgnoreCase(a.getColor());
    }
}
