package LanguageFeaturesRelated;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExercises {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        String[] arrayOfWords = {"Hello","World","New"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        List<String> uniqueChars = words.stream().map(word ->word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        uniqueChars.stream().forEach(s -> System.out.println(s));

        System.out.println();

        List<Integer> ints = Arrays.asList(1,2,3,4,5);
        List<Integer> squareList = ints.stream().map(n -> n*n).collect(Collectors.toList());
        squareList.stream().forEach(s-> System.out.println(s));

        System.out.println();

        //generate pairs
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i,j})).collect(Collectors.toList());
        pairs.stream().forEach(i -> System.out.printf("(%d,%d)",i[0],i[1]));

        System.out.println();
        pairs.stream().forEach(i ->{
            System.out.print("(");
            Arrays.stream(i).forEach(j -> System.out.print(j+" "));
            System.out.print(")");
        });

        //pairs whose sum is divisible by 3
        System.out.println();
        List<int[]> pairs2 = pairs.stream().filter(a -> (a[0]+a[1])%3==0).collect(Collectors.toList());
        pairs2.stream().forEach(i -> System.out.printf("(%d,%d)",i[0],i[1]));

        System.out.println();
        List<int[]> pairs3 =
                numbers1.stream().flatMap(i ->
                                numbers2.stream()
                                        .filter(j -> (i + j) % 3 == 0)
                                        .map(j -> new int[]{i, j})
                        ).collect(Collectors.toList());

        System.out.println();
        int c =Arrays.asList(1).stream().reduce(0, (a, b)-> a+b);
        int d =Arrays.asList(1,2,3,4,5).stream().reduce(1, (a, b)-> a*b);
        int e =Arrays.asList(1,2,3,4,5).stream().reduce(0, Integer::sum);
        System.out.println(c+"\n"+d+"\n"+e);

        double[] arr = new double[]{1.1,2.2,3.3,4,5};
        OptionalDouble sum = Arrays.stream(arr).reduce((a, b)-> a+b);
        if(sum.isPresent()){
            System.out.println(sum.getAsDouble());
        }

        int[] numbers = new int[]{1,3,5,10,2,8};
        OptionalInt opMax= Arrays.stream(numbers).reduce(Integer::max);
        if(sum.isPresent()){
            System.out.println(opMax.getAsInt());
        }

        List numberss = Arrays.asList(numbers);//this one is problematic since int array is an object and int elements cannot be autoboxed, so if it is streamed and counted, the result is 1.
        System.out.println(Arrays.stream(numbers).count());

        //brute force method
        System.out.println();
        IntStream primes = IntStream.rangeClosed(4,100).filter(t -> IntStream.rangeClosed(2, (int) Math.sqrt(t)).noneMatch(dv -> t%dv==0));
        System.out.println(primes.count()+2);

        Stream<String> values =
                Stream.of("config", "home", "user")
                        .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        values.forEach(s -> System.out.println("_"+s+"_"));//null


    }
}
