package br.com.jkavdev.java8.cap2.predicate;

import br.com.jkavdev.java8.cap2.Apple;

public class AppleRedColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple a) {
        return "red".equalsIgnoreCase(a.getColor());
    }
}
