package Signals;

import CommonUtils.SignalUtils;

/*
Based on [Hack Audio chp 12[.
In the first half, for the convenience of demonstration using small-valued shifts,
a high frequency was used for low-pass and a lower frequency for high-pass
 */
public class $8_BasicFeedforwardFilters {
    public static void main(String[] args) {
        /* 1. low-pass (attenuating the amplitude) by delayed addition
         (positive delay so comes the name feedforward <of delay direction>) */
        double[] au1 = SignalUtils.simpleSinusoids(1,10000,0);
        double[] au2 = SignalUtils.delay(au1,2);//44100/10000/2 = 2.205, adjacent to reversed half-period
        double[] au = SignalUtils.addByElements(au1,au2);
        trim(au,true,2);

        SignalUtils.printSummary(au1);
        SignalUtils.printSummary(au);//Min -0.29, Max 0.29 - decreased absolute values
        System.out.println("------------------------");
        /* 2. high-pass (increasing the amplitude) by delayed subtraction
         (suitable delayed shifts to make it near reversed half-period is important) */
        double[] au3 = SignalUtils.simpleSinusoids(1,1000,0);
        double[] au4 = SignalUtils.delay(au3,18);//44100/1000/2 = 22.05, adjacent to reversed half-period
        au = SignalUtils.submtractByElements(au3,au4);
        trim(au,false,18);

        SignalUtils.printSummary(au);//Min -1.92, Max 1.92 - increased absolute values
        System.out.println("------------------------\n-----------------------------\n");

        /* 3. low-pass for a sample of compound signals with different delays
         */
        double[] au5 = SignalUtils.addByElements(SignalUtils.simpleSinusoids(1,200,0),au3);
        //SignalUtils.visualizeSample(au5,0.05,true);
        au = lowPass(au5,15);
        //SignalUtils.visualizeSample(au,0.05,true);//partial removal of high frequencies
        au = lowPass(au5,22);
        //SignalUtils.visualizeSample(au,0.05,true);//complete removal of high frequencies

        double[] au6 = SignalUtils.addByElements(SignalUtils.simpleSinusoids(1,100,0),au3);
        //SignalUtils.visualizeSample(au6,0.05,true);
        au = highPass(au5,1);
        SignalUtils.printSummary(au);
        //SignalUtils.visualizeSample(au,0.03,true);//partial removal of low frequencies
        au = highPass(au5,441);
        SignalUtils.printSummary(au);//standard error decreased a lot from the previous one
        //SignalUtils.visualizeSample(au,0.03,true);//although looks unchanged, stronger removal of low frequencies

        /* 4. serial combination of low pass and high pass to be a bandpass
         */
        double[] au7 = SignalUtils.addByElements(SignalUtils.simpleSinusoids(1,5000,0),au6);
        //SignalUtils.visualizeSample(au7,0.02,true);
        au = lowPass(au7,4);// 44100/5000/2
        au = highPass(au,441);// according to last example
        //SignalUtils.visualizeSample(au,0.02,true);

        /* 5.parallel combination of LPF and HPF to be a bandstop (of middle freq)
         */
        double[] au8 = lowPass(au7,4);
        double[] au9 = highPass(au,441);
        au = SignalUtils.addByElements(au8,au9);
        SignalUtils.visualizeSample(au,0.03,true);//became a combinatio of low and high frequencies
    }

    static void trim(double[] source, boolean tail, int length){//tail or head; length to be removed
        int start;
        if(tail){
            start = source.length - length;
        }else {
            start = 0;
        }
        for (int i = start; i <start+length ; i++) {
            source[i] = 0;
        }
    }

    static double[] lowPass(double[]source, int delays){
        double[] delayed = SignalUtils.delay(source,delays);
        double[] output = SignalUtils.addByElements(source,delayed);
        SignalUtils.normalize(output);

        return output;
    }

    static double[] highPass(double[]source, int delays){
        double[] delayed = SignalUtils.delay(source,delays);
        double[] output = SignalUtils.submtractByElements(source,delayed);
        SignalUtils.normalize(output);

        return output;
    }
}
