package DataStructures_n_Algo;

import org.apache.commons.math3.stat.StatUtils;

/*
a basic method for computing running average and deviation of
stream data without storing large amounts of values.
Based on https://nestedsoftware.com/2018/03/20/calculating-a-moving-average-on-streaming-data-5a7k.22879.html
and https://nestedsoftware.com/2018/03/27/calculating-standard-deviation-on-streaming-data-253l.23919.html
originally in Javascript
 */
public class RunningMeanAndVariance {
    public static void main(String[] args) {
        double[] data = new double[]{1.0,2,3,4,5,6,7,8,9};
        double mean0 = StatUtils.mean(data);
        double v0 = StatUtils.variance(data);
        System.out.printf("Using standard methods: mean=%.2f, d=%.2f\n",mean0,v0);

        MovingCalculator calc = new MovingCalculator();
        for (double num:data) {
            calc.update(num);
        }
        System.out.printf("Using running methods: mean=%.2f, d=%.2f\n",calc.getAvg(),calc.getVar());


    }

    private static class MovingCalculator{
        double avg;
        double var;
        int count;

        public MovingCalculator() {
            avg = 0;
            var = 0;//yet to be divided by count-1 if requested
            count = 0;
        }

        public void update(double entrant){
            count++;
            double oldAvg = avg;//for running deviation
            avg += (entrant - avg)/count;

            var += (entrant - avg)*(entrant-oldAvg);
        }

        public double getAvg() {
            return avg;
        }

        public double getVar() {
            return var/(count-1);
        }
    }
}
