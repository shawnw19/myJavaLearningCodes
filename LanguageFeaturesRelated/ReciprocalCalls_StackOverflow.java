package LanguageFeaturesRelated;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Adapted from and inspired by case in Modern Java in Action
chapter 17.2.2

Two cases:
1. unstopping reciprocal calls by fun1 and fun2
on each other causing stackoverflow
2 (To uncomment the comments) a separate
thread is used to hold another function and
this solves the problem
 */

public class ReciprocalCalls_StackOverflow {
    static ExecutorService executor =
            Executors.newSingleThreadExecutor();
    public static void main(String... args) {

        fun1("hi");
    }

    public static void fun1(String in1){
        System.out.println(in1);
        fun2(" 1 ");
    }

    public static void fun2(String in2){
//        executor.submit(()->{
        System.out.println(in2);
        fun1(" 2 ");
        //});
    }
}
