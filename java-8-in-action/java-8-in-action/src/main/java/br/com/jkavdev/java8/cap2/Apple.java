package br.com.jkavdev.java8.cap2;

public class Apple {

    private String color;
    private Integer weight;

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
