package Neural_Network_Computing;

import java.util.Scanner;

public class $1_SIGMOID {
    //sigma function
    public static double sigm(double x){
        return 1.0/(1.0+Math.exp(-x));//as y
    }

    //print n spaces
    public static void spc(int n){
        for (int i = 0; i <n ; i++) {
            System.out.print(" ");
        }
    }
    public static void main(String[] args) {
        double y, k;//x
        Scanner scanner = new Scanner(System.in);

        System.out.println("The gain of the sigmoid function is k.");
        System.out.println("Please type in the value of k (<=100): ");
        k = scanner.nextDouble();

        for (double x = -5.0; x <=5.0 ; x+=0.5) {
            y= sigm(x*k);
            System.out.printf("IN=%6.2f  OUT=%6.4f |",x,y);
            //print out spaces and plus sign for graph
            spc((int) (40.0*y+0.5));
            System.out.println("+");
        }
    }
}
