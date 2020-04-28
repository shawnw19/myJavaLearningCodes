package CommonUtils;

import org.apache.commons.math3.complex.Complex;

import java.io.FileWriter;
import java.io.IOException;
/*
all I/O and format related utilities
 */
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

    public static void printArrayWithNo(double[] m){
        for (int i = 0; i <m.length ; i++) {
            System.out.printf("[%2d]: %5.2f ",i,m[i]);
            System.out.println();
        }
    }

    public static void printArray(int[] m){
        for (int i = 0; i <m.length ; i++) {
            System.out.printf(" "+m[i]);
        }
    }

    public static void writeArraytoFile(Complex[] data, FileWriter writer) throws IOException {
        for (int i = 0; i <data.length ; i++) {
            String str = String.format("%.2f %.2f\n",data[i].getReal(), data[i].getImaginary());//No.%2d:
            writer.write(str);
        }
    }
}
