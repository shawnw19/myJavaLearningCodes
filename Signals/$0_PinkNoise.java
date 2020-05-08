package Signals;

import CommonUtils.SignalUtils;
import IntroCS_Princeton.StdAudio;
import org.apache.commons.math3.linear.MatrixUtils;

import java.util.Random;

/*
As length (n of samples) are same, to make power (sum of squared amplitudes/2n-1) equal is
to make energy equal.
Two methods are used: 1 based on n/nyquist [Hack Audio chp 12.9] + periodic randomised values, and concatenated.
2 defined frequency from sinusoids and noised by randomisation, and superimposed.
The second method proved to be incorrect.
 */
public class $0_PinkNoise {
    static double duration =5.0;
    static Random rdm = new Random();
    public static void main(String[] args) {
        double[] au ;//= pinkByCat();
        //SignalUtils.visualizeSample(au,0.01,true);
        //StdAudio.play(au);
        //double[] temp = Arrays.copyOfRange(au,1000,1500);
        //MyUtils.printArray(temp,"0.000",10,true);

        au = pinkBySuperimpose();
        SignalUtils.visualizeSample(au,0.01,true);
        StdAudio.play(au);
    }

    static double[] pinkByCat(){
        int len = (int) (SignalUtils.Fs*duration);
        double[] output = new double[len];

        for (int i = 1; i <len/2 ; i+=20) {//f ; zero Hz omitted and not allowed
            for (int j = 1; j <len ; j++) {//x[n], default is 1
                if(j%i==0){
                    double amp =Math.sqrt(i);//without the reciprocal however sounds more like pink noise!
                    output[j] += amp*Math.pow(-1,rdm.nextInt(2));//alternating sign to make it full ranged
                }
            }
        }

        SignalUtils.normalize(output);

        return output;
    }

    static double[] pinkBySuperimpose(){
        int len = (int) (SignalUtils.Fs*duration);
        double[] output = new double[len];

        for (int i = 1; i <len/2 ; i+=20) {
            double[] au = SignalUtils.simpleSinusoids(duration,i, 0);
            for (int j = 0; j <len ; j++) {
                au[j] += Math.random()*2*Math.pow(-1,j);
            }
            au = MatrixUtils.createRealVector(au).mapMultiply(1/Math.sqrt(i)).toArray();

            output = SignalUtils.addByElements(output,au);
        }

        SignalUtils.normalize(output);

        return output;
    }
}
