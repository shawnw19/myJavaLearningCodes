package Chp00;
/*
adapted from `A First Course in Scientific Computing` by Landau
pp23, exc.10
 */
public class PiApproximation {
    static int appro(int decimal){
        double x = 1.0;//all between 0 and pi work
        double pix = 0;//to be approximated
        double thrd = 1/(Math.pow(10,decimal));//threshold
        int n=1;//to construct 2n-1
        while (Math.abs(pix-Math.PI)>=thrd){
            pix += 4*Math.sin((2*n-1)*x)/(2*n-1);
            n++;
            //System.out.printf("Trial %d %.8f\n",n, pix);
        }

        //System.out.println("\nPi with "+thrd+ " precision: "+ pix);

        return n;//number of calculations to obtain desired precision
    }
    public static void main(String[] args) {
        System.out.println("Decimal(s)  Count");
        for (int i = 1; i <=13 ; i++) {
            System.out.println("   "+i+"   "+appro(i));
        }
        //maximum power is 13 according to trials
    }
}
