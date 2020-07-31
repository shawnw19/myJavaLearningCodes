package LanguageFeaturesRelated;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;

/*
mostly based on [Modern Java in Action] chp3
 */
public class LambdaExercises {
    public static void main(String[] args) {
        String date = new SimpleDateFormat().format(System.currentTimeMillis());

        Runnable r1 = ()-> System.out.println("time now: "+ date);

        r1.run();

        Callable<String> c2 = ()->{return "Hello, awersome!";};

        try {
            System.out.println(c2.call());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(fetch().call());
        } catch (Exception e) {
            e.printStackTrace();
        }

        goThrough(Arrays.asList(1,3,4,65,100), i-> System.out.println("In list: "+ i));//types inferenced

        Arrays.asList("lambdas", "in", "action").stream().map((String s)->s.length()).forEach(e -> System.out.println("Length: "+e));

        IntPredicate even = t-> t%2==0;
        System.out.println(even.test(1000));
        System.out.println(even.test(1321));

        List<String> stringList = Arrays.asList("a","E","d","C");
        stringList.sort(String::compareToIgnoreCase);
        stringList.stream().forEach(s -> System.out.println(s));

        BiFunction<Color, Integer, Apple> c3 =
                (color, weight) -> new Apple(weight,color);
        Apple a3 = c3.apply(Color.GREEN, 110);
        System.out.println(a3);

        TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
        Color myColor = colorFactory.apply(10,52,77);
        System.out.println(myColor.toString());
    }

    static public Callable<String> fetch() {
        return () -> "Tricky example ;-)";
    }

    static public <T> void goThrough(List<T> list, Consumer c){
        for (T t:
             list) {
            c.accept(t);
        }
    }

    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    //from accompanying material
    static class Apple {

        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }

    }
}
