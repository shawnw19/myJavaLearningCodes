package Signals;

import CommonUtils.MyUtils;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class $3_DDFT_cleaned {
    static int n = 32;//num of time intervals or samples -NS
    static int periods =3;/*over the samples
        actually f0; and the relative frequency to sampling frequency is 'periods/n'.
        */
    static int delay = 0;//number of 'samples delay'
    public static void main(String[] args) {
        double[] x = new double[n];//'X', inputs
        double [][] y = new double[n][2];//outputs

        System.out.println("Number of time or samples: " + n);
        System.out.println("Periods over the samples: "+ periods);
        System.out.println("Number of samples delay: "+ delay);
        System.out.println();

        double w = 2*Math.PI*periods/n;//small omega, unit change within one time interval.//
        for (int i = 0; i <n ; i++) {
            x[i] = Math.sin(w*(i-delay));
        }

        /*perform DFT*/
        double e= 2*Math.PI/n;//interpreted to be the delta of differentiation/unit change

        for (int k = 0; k <n ; k++) {
            y[k][0] = 0;
            y[k][1] = 0;
            double wk = k*e;
            for (int j = 0; j <n ; j++) {
                double c = Math.cos(j*wk);
                double s = Math.sin(j*wk);
                y[j][0] += x[j]*(c+s);
                y[j][1] += x[j]*(c-s);
            }
        }

        //output
        System.out.println("Transformed sequence is: ");
        System.out.println("Index  Real  Imaginary");
        for (int i = 0; i <n ; i++) {
            System.out.printf(" %2d   %5.2f    %5.2f\n",i, y[i][0],y[i][1]);
        }

        //using 3rd party library
        System.out.println("\nUsing Apache.Commons.Math: \n");
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] result = fft.transform(x, TransformType.FORWARD);
        MyUtils.printArray(result);
    }
}
