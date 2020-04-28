package Signals;

import CommonUtils.SignalUtilis;
import IntroCS_Princeton.StdAudio;

/*
synthesis of sawtooth, triangle and impulse sounds
based on [Hack audio  an introduction to computer programming and digital signal processing in MATLAB]
chp7.3.4-7.3.6
 */
public class $5_OtherTestSounds {
    static double duration =2.0;
    static int Fs = StdAudio.SAMPLE_RATE;
    static int length = (int) (duration*Fs);
    static int f1 = 880;//sawtooth
    static int f2 = 880;//triangle
    static int f3 = 440;//impulse
    //static int[] f =new int[]{f1,f2,f3};
    static int period;// = new int[3] int[]of three frequencies, duration*Fs/f[i]
    //static double[] sound;
    public static void main(String[] args) {
        double min = -1, max = 1;

        double[] sound = generateSound(2);

        //visualise a segment of second type sound which is the most complex
        double[]sample = SignalUtilis.smallSample(sound,0.02);
        SignalUtilis.visualizeSignal(sample,1);

        StdAudio.play(sound);
    }

    static double[] generateSound(int theCase){
        double[] sound = new double[length];
        if (theCase==1){
            period = length/f1;
            for (int i = 0; i <length ; i++) {
                sound[i] = (1.8/period)*(i%period) -1;//a line
            }
        }else if(theCase==2){
            period = length/f2;
            for (int i = 0; i <length ; i++) {
                sound[i] =1- (4.0/period)*Math.abs(i%period -0.5*period);//opposite lines against the peak
            }
        }else if(theCase==3){
            period = length/f3;
            for (int i = 0; i <length ; i++) {
                sound[i] = (i%period == 0)?1:0;//only pulses
            }
        }

        return sound;
    }
}
