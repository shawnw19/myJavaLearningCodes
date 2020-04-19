package CommonUtils;

import org.apache.commons.math3.complex.Complex;

public class MyUtils {
    public static void printMatrix(double[][] m, int ncol,int nrow){
        for (int i = 0; i <nrow ; i++) {
            for (int j = 0; j <ncol ; j++) {
                System.out.printf("%.2f ",m[i][j]);
            }
            System.out.println();
        }
    }

    public static void printMatrix(double[][] m){
        for (int i = 0; i <m.length ; i++) {
            for (int j = 0; j <m[0].length ; j++) {
                System.out.printf("%.2f ",m[i][j]);
            }
            System.out.println();
        }
    }

    public static void printArray(Complex[] m){
        for (int i = 0; i <m.length ; i++) {
            System.out.printf("No.%2d:  %6.2f, %6.2f",i, m[i].getReal(), m[i].getImaginary());
            System.out.println();
        }
    }

    public static void printArray(double[] m){
        for (int i = 0; i <m.length ; i++) {
            System.out.printf("%.2f ",m[i]);
        }
    }

    public static void printArray(int[] m){
        for (int i = 0; i <m.length ; i++) {
            System.out.printf(" "+m[i]);
        }
    }
}
