package Signals;

import CommonUtils.MyUtils;
import IntroCS_Princeton.StdAudio;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import java.io.FileWriter;
import java.io.IOException;

/*
adapted from [Speech and Audio Processing_A MATLAB based Approach-Ian McLoughlin]
pp47-49, originally in Matlab.
 */
public class $4_VaryingTonedChirp {
    static double duration =2.0;
    static int Fs = StdAudio.SAMPLE_RATE;
    public static void main(String[] args) throws IOException {
        int n = (int) (duration*Fs);
        //int n = 131072;//2^17 for fourier transform
        int f0 = 600;//basic frequency
        int fv = 1000;//varying part
        int ff =5;//frequency of changing f within one second
        double[] ModF = new double[n];//"modified frequency", the frequency array
        for (int i = 0; i <n ; i++) {
            double Tt = ((i%Fs)*ff/Fs);//percentage of full change
            ModF[i] = f0+fv*Math.sin(Tt*2*Math.PI);
        }

        double theSound[] = freqgen(ModF,n);
        StdAudio.play(theSound);

        String file  = "/home/shawn/IdeaProjects/Streib/src/Signals/SignalFiles/$4_" +f0+"_"+fv+"_"+ff+".wav";
        StdAudio.save(file,theSound);

        //try a fft
        System.out.println("\nUsing Apache.Commons.Math transform: \n");
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] result = fft.transform(theSound, TransformType.FORWARD);
        FileWriter writer = new FileWriter("/home/shawn/IdeaProjects/Streib/src/Signals/SignalFiles/$4_fftResults.txt");
        MyUtils.writeArraytoFile(result,writer);

    }

    static double[] freqgen(double[]frc, int n){//generate the sound based on an array of frequencies (of all points)
        double[] sound= new double[n];
        double th = 0;
        double fr = 0;

        for (int i = 0; i <n ; i++) {
            fr = frc[i]*2*Math.PI/Fs;
            th += fr;
            sound[i] = Math.sin(th);
            //th = unwrap(th);
            }
        return sound;
        }

    //unwrap (counterpart in Matlab) is useless here
    //based on https://stackoverflow.com/questions/15634400/continous-angles-in-c-eq-unwrap-function-in-matlab
    static double unwrap(double th){
        th = (th+Math.PI)%(2*Math.PI);
        if (th < 0)
            th += 2*Math.PI;
        return th-Math.PI;
    }
}
