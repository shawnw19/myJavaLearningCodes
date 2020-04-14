package Signals;

import IntroCS_Princeton.StdAudio;

public class $1_SqaureWaveSound {
    static int numSeries;
    static double[][] sinusoids;
    static double init=-20;//initial time
    static double finl=20;//final time
    static double step=0.001;//time interval

    public static void main(String[] args) {

        numSeries=1000;
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

        StdAudio.play(signal);
        String file  = "/home/shawn/IdeaProjects/Streib/src/Signals/SignalFiles/sqWvSound_with"+numSeries+".wav";
        StdAudio.save(file,signal);
        }
}
