package Neural_Network_Computing;

import java.util.Scanner;

//demonstration of "learning" processes of
//a single neuron
public class $3_LEARNIT {
    public static void main(String[] args) {
        int x[][] = {{1, 0, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 1}};//4 posssible inputs with X(0) all 1
        int r[] = new int[4];//correct responses
        double w[] = new double[3];//weights
        double corr;//correlation factor
        int resp, diff;//response","delta/difference"
        boolean wrongResp = true;
        int count=0;//looping count

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i <4 ; i++) {
            System.out.printf("Type in the correct response for these inputs %d %d :",x[i][1],x[i][2]);
            r[i] = scanner.nextInt();
        }//get the desired responses for the possible inputs

        //Routine of manual assignment of weight omitted
        //random assignment
        w[0] = Math.random();
        w[1] = Math.random();
        w[2] = Math.random();
        System.out.printf("The initial weights are %.2f %.2f %.2f .\n", w[1], w[2], w[0]);

        System.out.print("What is the value of your correction factor? :");
        corr = scanner.nextDouble();

        while (wrongResp & count<=1000) {//loop until responses are correct
            wrongResp = false;
            for (int j = 0; j <4 ; j++) {
                resp = ((w[0]*x[j][0] + w[1]*x[j][1] +w[2]*x[j][2])>= 0)?1:0;
                diff = r[j]-resp;//*signed and directs the adjustment

                System.out.printf("test inputs %d %d, response %d, correct response %d.\n",x[0][1],x[j][1],resp,r[j]);

                if (diff!=0){
                    wrongResp = true;
                    w[0] = w[0] + diff*corr*x[j][0];
                    w[1] = w[1] + diff*corr*x[j][1];
                    w[2] = w[2] + diff*corr*x[j][2];
                    System.out.printf("New initial weights are %.2f %.2f %.2f\n", w[1], w[2], w[0]);
                }
                count++;
            }
        }

        if (wrongResp==false){
            System.out.println("\nNo adjustment necessary.\n");
            System.out.printf("The final weights are %.2f %.2f %.2f\n", w[1], w[2], w[0]);
        }else {
            System.out.println("\nUnsolvable by single neuron.");
        }

    }
}
