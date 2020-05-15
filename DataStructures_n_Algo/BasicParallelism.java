package DataStructures_n_Algo;

import CommonUtils.SignalUtils;
import IntroCS_Princeton.StdAudio;
import edu.rice.pcdp.PCDP;//or add 'static' to enable omission of class title


public class BasicParallelism {
    static double sum1, sum2;//static required by lambda expression
    public static void main(String[] args) {
        parTest();
    }

    /*based on
    https://www.coursera.org/learn/parallel-programming-in-java/lecture/yGttW/reciprocalarraysum-using-async-finish-demo
   and https://blog.csdn.net/weixin_39505272/article/details/94397503
   Conclusion: In task parallelism, Java lambda based parallelism is much slower than conventional
   method for simple data sequential processing but it is faster with regards to more complex tasks.
   */
    static void parArraySum(double[] input, boolean par){//false for sequential
        long startTime = System.nanoTime();
        sum1 = 0; sum2 = 0;
        int len = input.length;

        if(par){
            PCDP.finish(()->{
                PCDP.async(()->{
                    for (int i = 0; i <len/2 ; i++) {
                        sum1 += input[i];
                    }
                });

                for (int i = len/2; i <len ; i++) {
                    sum2 += input[i];
                }
            });
        }else {
            for (int i = 0; i <len/2 ; i++) {
                sum1 += input[i];
            }
            for (int i = len/2; i <len ; i++) {
                sum2 += input[i];
            }
        }

        double sum = sum1 +sum2;
        long finishTime = System.nanoTime() - startTime;

        System.out.printf("Time in milliseconds: %.3f, with sum=%.4f\n", finishTime/1e6, sum);
    }

    static void parTest(){
        double[] au = StdAudio.read("Files/Mondegreen.wav");
        System.out.println("Without parallelism: ");
        parArraySum(au,false);
        System.out.println("With parallelism: ");
        parArraySum(au,true);

        System.out.println("\n");

        System.out.println("Without parallelism: ");
        parComplex(false);
        System.out.println("With parallelism: ");
        parComplex(true);
    }

    static void parComplex(boolean par){//false for sequential
        double[] shortSinc = SignalUtils.simpleSincSignal(50,0.025,true);

        long startTime = System.nanoTime();

        if(par){
            PCDP.finish(()->{
                PCDP.async(()->{
                    double[] sound0 = SignalUtils.simpleSinusoids(3.0, 600, 0);
                    SignalUtils.convolute(sound0,shortSinc);
                });

                double[] sound1 = SignalUtils.simpleSinusoids(3.0, 800, 0);
                SignalUtils.convolute(sound1,shortSinc);
            });
        }else {
            double[] sound0 = SignalUtils.simpleSinusoids(3.0, 600, 0);
            SignalUtils.convolute(sound0,shortSinc);
            double[] sound1 = SignalUtils.simpleSinusoids(3.0, 800, 0);
            SignalUtils.convolute(sound1,shortSinc);
        }

        long finishTime = System.nanoTime() - startTime;

        System.out.printf("Time in milliseconds: %.3f\n", finishTime/1e6);
    }
}
