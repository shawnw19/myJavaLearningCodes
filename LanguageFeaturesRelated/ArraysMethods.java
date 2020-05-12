package Chp00;

import java.util.Arrays;

public class ArraysMethods {
    public static void main(String[] args)
    {
        int[] array = {5,7,9,2,4,33};
//        Array array1=new Array(5,7,9,2,4,33);
        System.out.println("Numbers= ");
        for (int i: array){
            System.out.println(i);
        }

        Arrays.sort(array);
        System.out.println("Numbers after sorting: ");
        for (int i: array){
            System.out.println(i);
        }

        System.out.println("Binary search result: "+ Arrays.binarySearch(array,6));
        System.out.println("Binary search result: "+ Arrays.binarySearch(array,7));

    }
}
