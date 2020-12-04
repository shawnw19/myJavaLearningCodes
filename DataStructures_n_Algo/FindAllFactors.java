package DataStructures_n_Algo;

import java.util.Vector;

/***
 * An improved solution of finding all prime factors
 * of a large number.
 * 
 * Input: a large number (long but can be other types)
 * Output: a vector of all prime factors which can be repetitive
 *
 * Two points are considered:
 * 1. Retaining all factors rather than only distinct ones preserves
 * all information
 * 2. Early stop: x is prime if all numbers, except 1, up to sqrt(x)
 * cannot divide x.
 */
public class FindAllFactors {
    public static void main(String[] args) {
        //generated from the product of 2 2 2 3 3 5 7 11 13 17 17 43
        long num = 4478193720L;
        System.out.printf("The factors of %d are: \n", num);
        printFactors(findFactors(num));

    }

    static Vector findFactors(long num){
        Vector factors = new Vector();
        boolean stop = false;
        //starts from 2 because 1 is neither prime nor composite
        int fact = 2;
        while (!stop){
            if(num%fact==0){
                factors.add(fact);
                num /= fact;
            }
            else if(fact<Math.sqrt(num)){
                fact ++;
            }
            else {
                //either 1 after divisions or the larger prime left
                stop = true;
                if(num>1){
                    factors.add(num);
                }
            }
        }

        return factors;
    }

    static void printFactors(Vector factors){

        factors.stream().forEach(e-> System.out.print(e+" "));
    }
}
