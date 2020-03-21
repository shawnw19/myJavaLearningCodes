package Chp00;

import IntroCS_Princeton.StdDraw;

import java.util.Arrays;
import java.util.Vector;

/*a demonstration of first order differential equation
using Euler's method. adapted from
`The Chaos Cookbook` 2nd Joe Pritchard 1996
originally in Pascal and Visual Basic
 */
public class EulerDiffDemo {
    static double equation(double xi, double ti){
        return 3*Math.sin(xi*xi)-Math.cos(ti*ti);
    }

    public static void main(String[] args) {
        double x, t, d, xinc; //two variables, delta and x-increment
        d = 0.001;
        x = 0.77;
        t = -1;

        StdDraw.setCanvasSize(800,600);
        StdDraw.setXscale(-200,3200);
        StdDraw.setYscale(0.72,2.5);

/*        trial of value range
        double[] jug1 = new double[2000];
        double[] jug2 = new double[2000];*/

        for (int i = 1; i <=3000 ; i++) {
            xinc = equation(x,t)*d;
            x += xinc;
            t += d;
            StdDraw.point(i,x);
/*            jug1[i-1] = x;
            jug2[i-1] = t;*/
        }

        System.out.println("Drawing finished.");
        /*Arrays.sort(jug1);
        Arrays.sort(jug2);
        System.out.println("x range "+jug1[0]+" "+jug1[1999]);
        System.out.println("t range "+jug2[0]+" "+jug2[1999]);*/
        //x range 0.771 1.707
    }
}
