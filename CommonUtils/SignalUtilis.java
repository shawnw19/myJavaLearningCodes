package CommonUtils;

import IntroCS_Princeton.StdAudio;
import IntroCS_Princeton.StdDraw;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SignalUtilis {
    public static void printSummary(double[] m) {//of vector-like data input
        Arrays.sort(m);
        double min = m[0];
        int len = m.length;
        double max = m[len - 1];
        double avg = StatUtils.mean(m);
        double sd = FastMath.sqrt(StatUtils.variance(m));
        System.out.printf("Length %d, Min %.2f, Max %.2f, Avg %.2f, SD %.2f \n", len, min, max, avg, sd);
    }

    public static void visualizeSignal(double[] m, int windowWidth, int windowHeight, int interval) {
        int len = m.length;
        double min = StatUtils.min(m);
        double max = StatUtils.max(m);
        double[] n = m.clone();
        Arrays.sort(n);
        double median = n[len / 2];

        StdDraw.setCanvasSize(windowWidth, windowHeight);
        StdDraw.setXscale(0, len);
        StdDraw.setYscale(min, max);
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(0, median, len, median);
        StdDraw.setPenColor(Color.BLUE);
        for (int i = 0; i < len; i++) {
            /*StdDraw.point(i, m[i]);
            StdDraw.line(i, m[i],i+1, m[i+1]);//-*/
            if (i % interval == 0){
                StdDraw.point(i, m[i]);
                //StdDraw.line(i, m[i],i+1, m[i+1]);//-
            }
        }
    }

    public static void visualizeSignal(double[] m, int interval) {
        int len = m.length;
        double min = StatUtils.min(m);
        double max = StatUtils.max(m);
        double[] n = m.clone();
        Arrays.sort(n);
        double median = n[len / 2];

        StdDraw.setCanvasSize(1200, 600);
        StdDraw.setXscale(0, len);
        StdDraw.setYscale(min, max);
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.setPenRadius(0.007);
        StdDraw.line(0, median, len, median);
        StdDraw.setPenColor(Color.BLUE);
        for (int i = 0; i < len; i++) {
            if (i % interval == 0){
                StdDraw.point(i, m[i]);
                //StdDraw.line(i, m[i],i+4, m[i+4]);
            }
        }
    }

    public static final int SAMPLE_RATE = StdAudio.SAMPLE_RATE;

    public static double[] smallSample(double[] m, double percentage) {//percentage of intensive sound for 1 second
        int len = m.length;
        Random rdm = new Random();
        int n = (int) (percentage*SAMPLE_RATE);
        double[] x = new double[n];

        int t = rdm.nextInt(StdAudio.SAMPLE_RATE);
        while (len - t < n && t < n) {//for smaller original sample
            t = rdm.nextInt(StdAudio.SAMPLE_RATE);
        }
        if (t >= (n - 1)) {
            for (int i = 0; i < n; i++) {//copy the portion reversely
                x[n - 1 - i] = m[t - i];//orig: n[i] = m[(t-defaultN+1)+i];
            }
        } else {
            for (int i = 0; i < n; i++) {
                    /*if(i%20==0){//-
                        System.out.println(i+" ");
                    }else {
                        System.out.print(i+" ");
                    }*/
                x[i] = m[t + i];
            }
        }

        return x;
    }

    /*
    if (len<=defaultN*1.1){
            throw new RuntimeException("Length below resampling threshold.");
        }else {
     */
}
