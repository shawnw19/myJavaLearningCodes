package DataStructures_n_Algo;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/*
The need of "Gaussian Sort" was found during a data mining project.
It is describe in https://stackoverflow.com/questions/54420458/sort-array-of-numbers-by-normal-distribution-gaussian-distribution.
The tentative solution is firstly, generate an array to store the correct indexes,
and swap the elements in the original array accordingly.

The space complexity: this solution is good for large but not huge data that
can be loaded into memory and sorted using in-place algorithms, but cannot be
duplicated in memory due to space limitation.
Note that the int array is a simple model, and will be generified or changed to
an array of larger objects in usage.
The order and current array in rearrange() are the array storing correct indexes
and the array controlling current indexes of the original data.
Since the size of original data is no more than 1/2 memory capacity, the helper
arrays which have same length, should be relatively small.

The time complexity: the original data array is processed by two halves.
The first half (i<(len + 1) / 2) is linear search and swapping. The result is
that first half sorted array contains even indexes of the ascending array: 0,
2, 4, 6...
The second half, should contain odd indexes: ...7, 5, 3, 1.
Because half indexes has been used during fist sorting, say when reached
0, 2, 4, 6, 8, 5, 3, 7, 1 what we are going to swap are 5 and 7, and get
0, 2, 4, 6, 8, 7, 3, 5, 1 but now the indexes in current e.g. "3" (index 6 in current)
and "5" (index 7 in current) has been different than them in order array, so
there is a need to search where they are in current index (the key is correct values in
order, the value is the index in current array).
Therefore, a linear search is added into the second half sorting with spatial complexity
being less than n^2/4.

References:
https://www.cnblogs.com/hellowooorld/p/7401713.html (incorrect implementation)
https://blog.csdn.net/gettogetto/article/details/69389810
 */

public class GaussianSort {
    public static void main(String[] args) {
        /*int[] orderTest = new GaussianOrderGenerator().getOrder(10);
         System.out.println(Arrays.toString(orderTest));
         Arrays.stream(orderTest).forEach(System.out::println);*/

        int range = 15;
        int[] original = new Random().ints(0, (int) (range*1.5)).distinct().limit(range).toArray();
        System.out.println("Original array:\n" + Arrays.toString(original));
        int[] rearranged = rearrange(original, new GaussianOrderGenerator());
        System.out.println("Sorted array:\n" +Arrays.toString(rearranged));

    }

    /*
    rearrange indexes according to the order sent by orderGenerator;
    scan the array from left to right, if the element has wrong value,
    swap current element with the element with correct value
     */
    public static int[] rearrange(int[] input, OrderGenerator orderGenerator) {
        int len = input.length;

        int[] order = orderGenerator.getOrder(len);
        int[] current = IntStream.range(0, len).toArray();
        /*for integrity purpose avoiding change of the original data,
        input should be used directly*/
        int[] rearranged = Arrays.copyOf(input, len);
        Arrays.sort(rearranged);

        //rearrange first half
        for (int i = 0; i < (len + 1) / 2; i++) {
            if (current[i] != order[i]) {
                swap(current, i, order[i]);
                swap(rearranged, i, order[i]);
            }
        }

        //rearrange second half
        for (int i = (len + 1) / 2; i < len; i++) {
            if (current[i] != order[i]) {
                //the actual location/index of the element supposed to on location i
                int index = getIndex(current, order[i], i);
                swap(current, i, index);
                swap(rearranged, i, index);
            }
        }

        return rearranged;
    }


    /*
    Search from start index to the end, ad hoc design
    for searching second half of the array
     */
    public static int getIndex(int[] arr, int element, int start) {
        for (int i = start; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        throw new RuntimeException("Index not found");
    }

    public static void swap(int[] original, int a, int b) {
        int c = original[b];
        original[b] = original[a];
        original[a] = c;
    }

    interface OrderGenerator {
        int[] getOrder(int arrayLength);
    }

    static private class GaussianOrderGenerator implements OrderGenerator {

        public GaussianOrderGenerator() {
        }

        public int[] getOrder(int arrayLength) {
            int[] order = new int[arrayLength];
            //insert even indexes
            for (int i = 0; i < (arrayLength + 1) / 2; i++) {
                order[i] = i * 2;
            }
            //insert odd indexes
            for (int i = 0; i < arrayLength / 2; i++) {
                order[arrayLength - 1 - i] = 2 * i + 1;
            }
            return order;
        }
    }
}

