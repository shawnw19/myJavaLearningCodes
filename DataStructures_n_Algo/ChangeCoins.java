package DataStructures_n_Algo;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Adapted from the famous climbing stairs problem
 * Accept arbitrary combination of smaller denominations
 * as long as they are compatible with the amount to be changed
 * The topic is tree recursion.
 **/
public class ChangeCoins {
    public static void main(String[] args) {
        //change a 2 euro coin to cents
        LinkedList<Double> denoms = new LinkedList<>(Arrays.asList(0.01,0.02,0.05,0.1,0.2,0.5));
        System.out.println(noWaysOfChange(2, denoms));
    }

    public static int noWaysOfChange(double n, LinkedList<Double> denoms) {

        if(n<=0){
            return 0;
        }
        else if(denoms.isEmpty()){
            return 0;
        }
        else{
            double currentDenom = denoms.peek();
            return 1 + noWaysOfChange(n-currentDenom, denoms)
                    + noWaysOfChange(n, new LinkedList<>(denoms.subList(1, denoms.size())));
        }
    }
}
