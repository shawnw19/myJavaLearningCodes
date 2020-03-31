package AlgebraicGeometry;

import IntroCS_Princeton.StdDraw;
import RGBHalfRainbow.RGBHalfRainbow;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.BigReal;
import org.apache.commons.math3.util.FastMath;

import java.util.Vector;
/*experiment for finding "hexa"pointed line
 */

public class SimpleSpiralEx {
    static double radius = 10.0;
    static double power =1.002;//of growth of radius
    static double theta = 0;//start from (1,0)
    static double Pi = Math.PI;
    static double md = 2*Pi;//modular controller for colour-changing period
    static double x(){
        return FastMath.cos(theta)*radius;
    }
    static double y(){
        return FastMath.sin(theta)*radius;
    }
    static RGBHalfRainbow palette = new RGBHalfRainbow(-0,md+0);

    public static void main(String[] args) {
        StdDraw.setCanvasSize(1100,500);
        StdDraw.setXscale(0,4000);
        StdDraw.setYscale(-200,10);
        StdDraw.setPenRadius(0.007);

        int cycle =1;
        int[] values;

        Vector vector = new Vector();//of coordinates
        vector.add(new double[]{10,0,palette.getValue(0)[2]});

        BigReal limit = new BigReal(1999999999);
        BigReal r = new BigReal(radius);
        while (r.compareTo(limit)<=0){//use very large number to find patterns
            values = palette.getValue(theta%(md));

            double tempX = x();
            double tempY = y();

            if(values[0]==0 & values[1]==0 & values[2]<17){
                double[] arr = new double[3];
                arr[0]= tempX;
                arr[1]= tempY;
                arr[2]= values[2];//blue section, indicating blackness

                vector.add(arr);
                if (tempX<=4000){//graphic limitations
                    StdDraw.setPenColor(values[0],values[1],values[2]);
                    StdDraw.point(x(),y());
                }
            }
            radius = radius*power;//power growth strategy
            //radius += 0.4;//or- linear increment strategy
            theta += 0.1;
            r = new BigReal(radius);
        }

        System.out.println("Special points:");
        for (int i = 0; i <vector.size() ; i++) {
            double [] a = (double[]) vector.get(i);
            System.out.printf(" %.1f  %.2f  %.0f\n",a[0],a[1],a[2]);
        }
        System.out.println();

        int groups =vector.size()/6; //no. of nearly-collinear point groups

        for (int i = 0; i <groups ; i++) {
            double pg[][]= new double[6][2];
            for (int j = 0; j <6 ; j++) {
                pg[j][0]= ((double[])vector.get(6*i+j))[0];
                pg[j][1]= ((double[])vector.get(6*i+j))[1];
            }
            SimpleRegression regression = new SimpleRegression();
            regression.addData(pg);
            System.out.printf(i+"th group: slope %.3f  R^2 %.4f\n",regression.getSlope(),regression.getRSquare());
        }
    }
}

/*
double diff = theta-theta*((int)(theta/md));
System.out.printf(" %.3f  %.3f",vector.get(i))
 */