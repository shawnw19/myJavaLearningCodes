package Signals;

import CommonUtils.SignalUtils;
import IntroCS_Princeton.StdAudio;
import IntroCS_Princeton.StdDraw;

/*
sinc acts as low-pass filter
and performs its function through convolution
 */
public class $8_SincAsFilter {
    public static void main(String[] args) {
        double[] shortSinc = SignalUtils.simpleSincSignal(50,0.025,true);

        double[] sound0 = SignalUtils.simpleSinusoids(3.0, 600, 0);
        double[] sound1 = SignalUtils.simpleSinusoids(3.0, 800, 0);
        //double[] sound3 = SignalUtils.simpleSinusoids(3.0, 6000, 0);

        double[] soundX = SignalUtils.ringModulation(sound0,sound1,0);
        //soundX = SignalUtils.ringModulation(soundX,sound3,0);

        System.out.println("Before filtering\n");
        double[] sourceSample = SignalUtils.smallSample(soundX, 0.05);
        SignalUtils.visualizeSignal(sourceSample,1700,600,1,true);
        StdAudio.play(soundX);

        StdAudio.play(SignalUtils.shortBlank);

        System.out.println("After 1st type filtering\n");
        convoluteAndVisualise(soundX,shortSinc);

        StdAudio.play(SignalUtils.shortBlank);

        System.out.println("After 2nd type filtering\n");
        shortSinc = SignalUtils.simpleSincSignal(100,0.025,true);//length increased
        convoluteAndVisualise(soundX,shortSinc);

        StdAudio.play(SignalUtils.shortBlank);

        System.out.println("After 3rd type filtering\n");
        shortSinc = SignalUtils.simpleSincSignal(300,0.025,true);//length increased again
        convoluteAndVisualise(soundX,shortSinc);
    }

    static void convoluteAndVisualise(double[] input, double[] response){
        SignalUtils.visualizeSignal(response,true);
        StdDraw.pause(3000);//to view the filter

        double[] output = SignalUtils.convolute(input,response);
        double[] outputSample = SignalUtils.smallSample(output, 0.05);
        SignalUtils.visualizeSignal(outputSample,1700,600,1,true);

        StdAudio.play(output);
    }
}
