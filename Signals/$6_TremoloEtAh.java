package Signals;

import CommonUtils.SignalUtils;
import IntroCS_Princeton.StdAudio;

/*
the essence of this ubiquitous sound effect is
the array multiplication of a low frequency oscillator(LFO) with another sound
 */
public class $6_TremoloEtAh {
    static double duration = 5.0;
    static int Fs = StdAudio.SAMPLE_RATE;
    static int length = (int) (duration * Fs);
    static int period;

    static class SoundGen {//extracted and modified from $5
        static double f1 = 4;//sawtooth
        static int f2 = 880;//triangle
        static int f3 = 440;//impulse

        static double[] generateSound(int theCase) {
            double[] sound = new double[length];
            if (theCase == 1) {
                period = (int) (Fs / f1);
                for (int i = 0; i < length; i++) {
                    sound[i] = (2.0 / period) * (i % period) - 1;//a line
                }
            } else if (theCase == 2) {
                period = Fs / f2;
                for (int i = 0; i < length; i++) {
                    sound[i] = 1 - (4.0 / period) * Math.abs(i % period - 0.5 * period);//opposite lines against the peak
                }
            } else if (theCase == 3) {
                period = Fs / f3;
                for (int i = 0; i < length; i++) {
                    sound[i] = (i % period == 0) ? 1 : 0;//only pulses
                }
            }

            return sound;
        }
    }

    public static void main(String[] args) {
        double[] sound1 = SoundGen.generateSound(1);//sawtooth
        double[] sound2 = SignalUtils.generateSimpleSinusoid(duration, 800, 0);

        double[] tremolo= SignalUtils.ringModulation(sound1, sound2, 0.5);//plus offset which changes modulation depth/intensify by alternating the occurrence of peaks

        //visualise a segment of new synthesised tremolo sound
        double[] sample = SignalUtils.smallSample(tremolo, 0.05);
        SignalUtils.visualizeSignal(sample,1700,550,5,true);

        StdAudio.play(tremolo);

        //try synthesising a vowel-like double-frequency-d sound
        double[] sound3 = SignalUtils.generateSimpleSinusoid(3.0, 200, 0);
        double[] sound4 = SignalUtils.generateSimpleSinusoid(3.0, 1000, 0);

        double[] ahLike = SignalUtils.ringModulation(sound3,sound4,0);

        ahLike = SignalUtils.linearFade(ahLike,0,0.05,true);
        ahLike = SignalUtils.linearFade(ahLike,0.95,1,false);

        sample = SignalUtils.smallSample(ahLike, 0.05);
        SignalUtils.visualizeSignal(sample,1700,550,1,true);

        StdAudio.play(ahLike);

        String file  = "/home/shawn/IdeaProjects/Streib/src/Signals/SignalFiles/$6_ahLike.wav";
        StdAudio.save(file,ahLike);
    }
}
