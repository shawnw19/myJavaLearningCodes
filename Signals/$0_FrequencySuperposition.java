package Signals;

import IntroCS_Princeton.StdAudio;
/*
sound synthesis by simply superimposing different
frequencies can generate music notes like C and D,
and simple sounds with some effects (e.g. gun shooting, metallic embellishments)
but is far from enough to vowels (e.g. [a]).
 */
public class $0_FrequencySuperposition {
    static int sample_rate = 10000;
    static double[] getSimpleSound(int octave,boolean att){//flag of attenuation
        double duration = 1.0;
        int n = (int) (StdAudio.SAMPLE_RATE * duration);//no. of discrete intervals
        double hz = 440 * Math.pow(2, octave/ 12.0);
        double sound[] = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            if (att) {
                sound[i] = Math.pow(0.9999, i) * Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
            } else {
                sound[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
            }
        }
        return sound;
    }

    static double[] getSimpleVowel(int f1, int f2, double d){//formants and duration
        int n = (int) (StdAudio.SAMPLE_RATE * d);
        int[] f = new int[]{100, f1, f2};//f0 first

        double sound[] = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            sound[i] = 0;//initialisation
            for (int j = 0; j <3 ; j++) {//3 formants
                sound[i] += Math.sin(2 * Math.PI * i *f[j]/StdAudio.SAMPLE_RATE)*Math.pow(0.55,j);
            }
        }
        return sound;
    }
    public static void main(String[] args) {
        double[] doC = getSimpleSound(1,false);
        StdAudio.save("/home/shawn/IdeaProjects/Streib/src/Signals/doC.wav",doC);
        double[] reD = getSimpleSound(2,false);
        StdAudio.save("/home/shawn/IdeaProjects/Streib/src/Signals/reD.wav",reD);
        double[] ah = getSimpleVowel(800,1400,1.0);
        StdAudio.save("/home/shawn/IdeaProjects/Streib/src/Signals/ah.wav",ah);
    }
}

/*

gun-like caused by superposition of adjacent frequencies:
--------
for (int k = 0; k <10 ; k++) {//adjacent frequencies
                    sound[i] += Math.sin(2 * Math.PI * i *(f[j]+(k-5)*10)/StdAudio.SAMPLE_RATE)*Math.pow(0.5,j);
                }
 */