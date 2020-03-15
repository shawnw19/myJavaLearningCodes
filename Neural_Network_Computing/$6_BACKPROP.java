package Neural_Network_Computing;

import java.util.Scanner;

public class $6_BACKPROP {
    static double w03, w04, w05,
            w13, w14, w23, w24, w35, w45,
            act3, act4, act5,
            out3, out4, out5,
            cr3, cr4, cr5,
            beta, r5, error, mse=1.0;
    /*The variables:
        w- the weights
        act - the activation levels of the nodes
        out - the outputs of the nodes
        cr? - the corrections, multiplied by beta
        mse - mean squared error
        r5 - the correct response (output) from node 5
     */
    public static void main(String[] args) {
        int in1, in2, iters,
                i;
        boolean tuned = false;

        Scanner scanner = new Scanner(System.in);
        System.out.print("What is the correction factor? ");
        beta = scanner.nextDouble();
        System.out.print("What is maximum number of iterations? ");
        iters = scanner.nextInt();
        //randomly set the weights
        w03 = Math.random()*10 -5.0;
        w04 = Math.random()*10 -5.0;
        w05 = Math.random()*10 -5.0;
        w13 = Math.random()*10 -5.0;
        w14 = Math.random()*10 -5.0;
        w23 = Math.random()*10 -5.0;
        w24 = Math.random()*10 -5.0;
        w35 = Math.random()*10 -5.0;
        w45 = Math.random()*10 -5.0;
        //stop when the iteration finishes or MSE is small
        for ( i = 1; i <=iters &(!tuned) ; i++) {
            in1 = (Math.random()>=0.5)?1:0;
            in2 = (Math.random()>=0.5)?1:0;
            act3 = w13*in1 +w23*in2 +w03;
            out3 = sigm(act3);
            act4 = w14*in1 +w24*in2 +w04;
            out4 = sigm(act4);
            act5 = w35*out3 +w45*out4 +w05;
            out5 = sigm(act5);
            r5 = in1+ in2 -2*in1*in2;//1 if in1 and in2 different, else 0
            error = r5-out5;
            mse = mse + 0.1*(error*error-mse);
            //==mse+0.1error^2, "exponential smoothing"
            cr5 = error*out5*(1.0-out5);
            w05 += beta*cr5;
            w35 += beta*cr5*out3;//propagated back to node3
            w45 += beta*cr5*out4;//propagated back to node4

            cr3 = w35*cr5*out3*(1-out3);//using sigmoid approximation in $4
            cr4 = w45*cr5*out4*(1-out4);

            w03 += beta*cr3;
            w04 += beta*cr4;

            w13 += beta*cr3*in1;
            w14 += beta*cr4*in1;
            w23 += beta*cr3*in2;
            w24 += beta*cr4*in2;

            if (mse<=0.1){
                tuned = true;
            }

            System.out.printf("i=%5d   input=%d %d  output=%.5f  mse=%.5f\n",i, in1, in2, out5, mse);
        }

        //print out the weights
        System.out.printf("\nAfter running %d times: ",i);
        if (tuned){
            System.out.printf("\nw03=%10.5f   w13=%10.5f   w23=%10.5f",w03,w13,w23);
            System.out.printf("\nw04=%10.5f   w14=%10.5f   w24=%10.5f",w04,w14,w24);
            System.out.printf("\nw05=%10.5f   w35=%10.5f   w45=%10.5f",w05,w35,w45);
        }else {
            System.out.println("  Back propagation failed.");
        }


    }

    public static double sigm(double x){
        return 1.0/(1.0+Math.exp(-x));//as y
    }
}
/* w0x is understood to be
the threshold weight itself
 */