package Chp00;

import IntroCS_Princeton.StdAudio;
import IntroCS_Princeton.StdDraw;
import java.awt.*;


/*visualise the waves and play the sounds
using Karplus-Strong algorithm (iterative attenuation): y[n] = x[n] +a*y[n-1]
partly based on https://introcs.cs.princeton.edu/java/15inout/PlayThatTune.java.html
and Digital Signal Processing (Coursera) 1.3.b The Karplus-Strong algorithm
 */
public class KarplusStrongLite {
    static double duration = 1.5;//seconds for each sound;
    static int n = (int) (StdAudio.SAMPLE_RATE * duration);//no. of discrete intervals
    public static void main(String[] args) {
        double pitch;

        StdDraw.setCanvasSize(1000,600);
        StdDraw.setXscale(0,n+1);
        StdDraw.setYscale(-1,1);
        StdDraw.setPenColor(Color.pink);
        StdDraw.setPenRadius(0.008);
        StdDraw.line(0,0,n,0);
        StdDraw.setPenColor(Color.BLACK);

        System.out.println("Normal sound: ");
        RisingPitch(7,false);
        System.out.println("Crispy sound: ");
        RisingPitch(7,true);

    }

    static void RisingPitch(int num,boolean att){
        int pitch;
        for (pitch=0;pitch<=num;pitch++){
            double hz = 440 * Math.pow(2, pitch / 12.0);
            double samples [] = new double[n+1];

            for (int i = 0; i <= n; i++) {
                if (att){
                    samples[i] = Math.pow(0.9999,i)*Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
                    if (i<=32000){
                        if (i%45==0)
                            StdDraw.point(i,samples[i]);
                    }else if (i%220==0)
                        StdDraw.point(i,samples[i]);
                    /*if (i%30==0)
                        StdDraw.point(i,samples[i]);*/

                }else {
                    samples[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
                }

            }

            System.out.print("+ ");

            StdAudio.play(samples);

            for (int j = 0; !att & j <StdAudio.SAMPLE_RATE *0.2 ; j++) {
                StdAudio.play(0);//pause 0.2 second, no need for the attenuated
            }
            StdDraw.clear();
            StdDraw.setPenColor(Color.pink);
            StdDraw.line(0,0,n,0);
            StdDraw.setPenColor(Color.BLACK);
        }
        System.out.println();
    }
}
