package br.com.jkavdev.java8.cap2;

import br.com.jkavdev.java8.cap2.predicate.ApplePredicate;
import br.com.jkavdev.java8.cap2.predicate.GenericPredicate;
import br.com.jkavdev.java8.cap2.quiz.print.AppleFormatter;

import java.util.ArrayList;
import java.util.List;

public class AppleRepository {

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> resultado = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equalsIgnoreCase(apple.getColor()))
                resultado.add(apple);
        }
        return resultado;
    }

    public static List<Apple> filterRedApples(List<Apple> inventory) {
        List<Apple> resultado = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("red".equalsIgnoreCase(apple.getColor()))
                resultado.add(apple);
        }
        return resultado;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> resultado = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() == 150)
                resultado.add(apple);
        }
        return resultado;
    }

    public static List<Apple> filterApples(List<Apple> inventory, String color) {
        List<Apple> resultado = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equalsIgnoreCase(apple.getColor()))
                resultado.add(apple);
        }
        return resultado;
    }

    public static List<Apple> filterApples(List<Apple> inventory, int weight) {
        List<Apple> resultado = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() == weight)
                resultado.add(apple);
        }
        return resultado;
    }

    public static List<Apple> filterApplesHorribleWay(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> resultado = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((apple.getWeight() == weight && flag)
                    || (color.equalsIgnoreCase(apple.getColor()) && flag))
                resultado.add(apple);
        }
        return resultado;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> resultado = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple))
                resultado.add(apple);
        }
        return resultado;
    }
    public static <T> List<T> filterGeneric(List<T> inventory, GenericPredicate<T> predicate) {
        List<T> resultado = new ArrayList<>();
        for (T t : inventory) {
            if (predicate.test(t))
                resultado.add(t);
        }
        return resultado;
    }

    public static void preetyPrintApples(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            System.out.println(formatter.accept(apple));
        }
    }

}
