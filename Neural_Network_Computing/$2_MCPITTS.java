package Neural_Network_Computing;

import java.util.Scanner;

public class $2_MCPITTS {
    public static void main(String[] args) {
        double w1, w2, T;//weights and threshold
        int x1, x2, out;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter weight 1:  ");
        w1= scanner.nextDouble();
        System.out.print("Please enter weight 2:  ");
        w2= scanner.nextDouble();
        System.out.print("Please enter the threshold: ");
        T= scanner.nextDouble();

        //print weights and threshold and a simple graph

        //print table of inputs and outputs
        System.out.println("       inputs");
        System.out.println("    X1      X2     output");
        for (x1 = 0; x1 <2 ; x1++) {
            for (x2 =0; x2 <2 ; x2++){
                out = (w1*x1 + w2*x2>=T)? 1:0;
                System.out.printf("    %d       %d      %d\n", x1,x2, out);
            }

        }

    }
}
