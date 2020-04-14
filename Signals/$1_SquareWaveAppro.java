package Signals;

import IntroCS_Princeton.StdDraw;
import java.awt.*;

/*
based on BASIC Digital Signal Processing pp 17-18
& pp32. Fourier series summation
 */
public class $1_SquareWaveAppro {//-ximation
    static int numSeries;
    static double[][] sinusoids;
    static double init=-5;//initial time
    static double finl=5;//final time
    static double step=0.15;//time interval

    public static void main(String[] args) {

        numSeries=10;
        sinusoids = new double[numSeries][3];//frequency, amplitude and phase for each series

        for (int i = 0; i <numSeries ; i++) {
            sinusoids[i] = new double[]{(2/Math.PI)*1/(2*i+1), 2*i+1, (i+1%2) * Math.PI};//amplitude is set 1
        }

        int n = (int) ((finl-init)/step);
        double signal[]=new double[n];

        for (int i = 0; i <n ; i++) {
            signal[i] = 1/2;//as in the formula
            for (int j = 0; j <numSeries ; j++) {
                signal[i] += sinusoids[j][0]*Math.cos(sinusoids[j][1]*i + sinusoids[j][2]);
            }

        }

        StdDraw.setCanvasSize(1500,600);
        StdDraw.setXscale(0,n);
        StdDraw.setYscale(-1,1);
        StdDraw.setPenRadius(0.008);
        for (int i = 0; i <n-1 ; i++) {
            StdDraw.setPenColor(Color.CYAN);
            StdDraw.line(i,signal[i],i+1, signal[i+1]);
            StdDraw.setPenColor(Color.red);
            StdDraw.point(i,signal[i]);

        }
    }
}

/*
num of sinusoids for approximation:
5,
10,
15,
20
...1000
 */