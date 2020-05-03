package CommonUtils;

import IntroCS_Princeton.StdAudio;
import IntroCS_Princeton.StdDraw;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SignalUtils {
    public static int Fs = 44100;
    public static void printSummary(double[] m) {//of vector-like data input
        Arrays.sort(m);
        double min = m[0];
        int len = m.length;
        double max = m[len - 1];
        double avg = StatUtils.mean(m);
        double sd = FastMath.sqrt(StatUtils.variance(m));
        System.out.printf("Length %d, Min %.2f, Max %.2f, Avg %.2f, SD %.2f \n", len, min, max, avg, sd);
    }

    public static void visualizeSignal(double[] m, int windowWidth, int windowHeight, int interval, boolean lined) {
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
            StdDraw.setPenColor(Color.CYAN);
            if (i % interval == 0){
                if(lined & i+interval<len){
                    StdDraw.line(i, m[i],i+interval, m[i+interval]);
                }
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.point(i, m[i]);
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
        if(len<=n){
            throw new IllegalArgumentException("Too short source or too long sample!");
        }
        double[] x = new double[n];

        int t = rdm.nextInt(len);//index of start or end
        while (len - t < n && t < n) {//for smaller original sample
            t = rdm.nextInt(len);
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

    public static double[] generateSimpleSinusoid(double duration, int frequency, double phaseShift) {//amplitude is set 1 as default
        int length = (int) (duration*StdAudio.SAMPLE_RATE);
        double[] sound = new double[length];
        double w = 2*Math.PI*frequency/StdAudio.SAMPLE_RATE;
        for (int i = 0; i <length ; i++) {
            sound[i] = Math.sin(w*(i+phaseShift));
        }

        return sound;
    }

    public static double[] ringModulation(double[] input, double[] response, double offset) {
        int len1 = input.length;
        int len2 = response.length;
        int len = Math.max(len1,len2);
        double[] output = new double[len];
        for (int i = 0; i <len ; i++) {
            output[i] = input[i%len1]*response[i%len2] + offset;
        }

        return output;
    }

    public static double[] linearFade(double[] input, double start, double end, boolean fadeIn) {//proportions of start and end; false for fade-out
        int len = input.length;
        double[] output = input.clone();//for convenience of keeping unchanged part intact
        if (start>=1 || end<=0 || start>=end){
            throw new IllegalArgumentException("Invalid start/end proportion!");
        }else {
            int segLen = (int) ((end-start)*len);
            for (int i = (int) (start*len); i < (end*len); i++) {
                double prop = (1.0 / segLen) * (i-start*len);//proportion to the original value
                if(fadeIn){
                    output[i] *= prop;
                }else {
                    output[i] *= 1-prop;
                }
            }
        }

        return output;
    }

}
