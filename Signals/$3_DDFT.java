package Signals;
/*
a demonstration of direct Discrete Fourier Transform
of a known wave - type (sine), frequency and phase(delay);
Based on BASIC Digital Signal Processing pp29-30, pp 9
 */
public class $3_DDFT {
    static int n = 32;//num of time intervals or samples -NS
    //! String type = "SN";// type 'A$ is sine
    static int periods =3;/*over the samples -PR
        actually f0; and the relative frequency to sampling frequency is 'periods/n'.
        */
    static int delay = 0;//number of 'samples delay' -DL

    public static void main(String[] args) {
        double[] x0 = new double[n];//'X'
        double [][] x = new double[n][2];//inputs 'XR XY'
        double [][] y = new double[n][2];//outputs

        System.out.println("Number of time or samples: " + n);
        System.out.println("Periods over the samples: "+ periods);
        System.out.println("Number of samples delay: "+ delay);
        System.out.println();

        double w = 2*Math.PI*periods/n;/*small omega, unit change within one time interval.
        8*Math.atan(1) was originally used for 2*pi*/
        for (int i = 0; i <n ; i++) {
            x0[i] = Math.sin(w*(i-delay));
        }//end of sine wave initialisation section in orig. program 1.4

        /*calculation section in orig. 2.2*/
        for (int i = 0; i <n ; i++) {
            x[i][0] = x0[i];//?
            x[i][1] = x0[i];;//XI(n)=X(n)
        }
        char type='d';//d for direct dft, i for indirect dft
        /*perform DFT*/
        double e=0;
        if(type=='d'){
            e = 2*Math.PI/n;//interpreted to be the delta of differentiation/unit change
        }
        /*if(type=='i'){
            e = -e;
        }*/
        for (int k = 0; k <n ; k++) {
            y[k][0] = 0;
            y[k][1] = 0;
            double wk = k*e;
            for (int j = 0; j <n ; j++) {
                double c = Math.cos(j*wk);
                double s = Math.sin(j*wk);
                y[j][0] += x[j][0]*c + x[j][1]*s;//?
                y[j][1] += x[j][1]*c - x[j][0]*s;//?
            }
        }

        //output
        System.out.println("Transformed sequence is: ");
        System.out.println("Index  Real  Imaginary");
        for (int i = 0; i <n ; i++) {
            System.out.printf(" %2d   %5.2f    %5.2f\n",i, y[i][0],y[i][1]);
        }
    }
}
