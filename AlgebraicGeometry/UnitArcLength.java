package AlgebraicGeometry;

import CommonUtils.SignalUtils;

/*
minimum-interval based naive numerical integration
to explore unit arc length of sinusoids
 */
public class UnitArcLength {
    public static void main(String[] args) {
        double duration = 5.0;

        for (int i = 0; i <30 ; i++) {//batch experiment
            int f = (i*2+1)*20;
            double[] au = SignalUtils.generateSimpleSinusoid(duration,f,0);
            System.out.println("len "+i+ " f="+f+" :" + unitSinusoidLength(au,f,duration));
        }
    }

    static double unitSinusoidLength(double[] sample, int f, double duration){
        double totalLen =0;
        for (int i = 1; i <sample.length ; i++) {
            totalLen += Math.abs(sample[i]-sample[i-1]);//delta is 1, omitted
        }

        return totalLen/(f*duration) ;
    }
}
