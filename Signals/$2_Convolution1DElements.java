package Signals;

import java.util.Iterator;
import java.util.TreeSet;

/*
demonstration of an efficient way to make enumeration of
all signal samples (input and impulse response) involved in certain output signal sample
 */
public class $2_Convolution1DElements {
    public static void main(String[] args) {
        int length1 = 60;
        int length2 = 9;
        int outputLength = length1+length2-1;//as per definition

        for (int i = 0; i <outputLength ; i++) {
            TreeSet<Pair> involvedSamples = getInvolvedSamples(length1,length2,i);
            System.out.printf("%2dth output has %d products: ", i, involvedSamples.size());
            outputSamplePairs(involvedSamples);
            System.out.println();
        }
    }

    static TreeSet<Pair> getInvolvedSamples(int inputLength, int impulseLength, int index){//3' _OfOutputSample
        int m = Math.max(inputLength,impulseLength);
        int n = Math.min(inputLength,impulseLength);//so n<=m
        m--;n--;//converted to indexes
        TreeSet<Pair> samplePairs = new TreeSet<>();
        int min = Math.min(n,index);//threshold of looping index
        for (int i = 0; i <=min ; i++) {// <= as max num of possibilities is min+1 (p+1 or n+1)
            if(index-i>=0 && index-i<=m){//so there is a pair of n,m that n+m=p(the index)
                Pair pair = new Pair(i,index-i);
                samplePairs.add(pair);
            }
        }

        return samplePairs;
    }

    static void outputSamplePairs(TreeSet<Pair> involvedSamples){

        Iterator iter = involvedSamples.iterator();
        while (iter.hasNext()){
            Pair pair = (Pair) iter.next();
            System.out.printf("%2d*%2d ",pair.former,pair.latter);//
        }

    }

    static class Pair implements Comparable<Pair>{
        int former;
        int latter;

        public Pair(int former, int latter) {
            this.former = former;
            this.latter = latter;
        }

        @Override
        public int compareTo(Pair o) {
            return this.former-o.former;
        }
        
    }
}
