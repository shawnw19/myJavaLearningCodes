package Signals;

import CommonUtils.SignalUtils;
import IntroCS_Princeton.StdAudio;
/*
demonstration of basic handling of stereo sound
using home-made utilities modified from StdAudio
 */
public class $7_StereoSound {
    public static void main(String[] args) {
        double[][] audio = StdAudio.readStereo("/home/shawn/IdeaProjects/Streib/src/Signals/SignalFiles/$7_001.wav");

        double[] sample = SignalUtils.smallSample(audio[0],0.05);
        SignalUtils.visualizeSignal(sample,1600,600,2,true);
        sample = SignalUtils.smallSample(audio[1],0.05);
        SignalUtils.visualizeSignal(sample,1600,600,2,true);

        double duration =3.0;
        double[][] audio2 = new double[2][(int) (SignalUtils.Fs*duration)];
        audio2[0] = SignalUtils.generateSimpleSinusoid(duration,300,0);
        audio2[1] = SignalUtils.generateSimpleSinusoid(duration,1200,0);

        StdAudio.playStereo(audio2);

        String file = "/home/shawn/IdeaProjects/Streib/src/Signals/SignalFiles/$7_heteroSinus.wav";
        StdAudio.saveStereo(file,audio2);


    }
}
