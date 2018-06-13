package br.com.jkavdev.java8.cap2;

public class RunnableNiceWay {

    public static void main(String[] args) {

        Thread old = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Funcionando...............");
            }
        });

        Thread newest = new Thread(() -> System.out.println("Funcionando..............."));

        old.run();
        newest.run();

    }

}
