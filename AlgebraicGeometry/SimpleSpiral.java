package AlgebraicGeometry;

import IntroCS_Princeton.StdDraw;
import RGBHalfRainbow.RGBHalfRainbow;
import org.apache.commons.math3.util.FastMath;
/*a simple generation of spirals
using iteration method
 */

public class SimpleSpiral {
    static double radius = 10.0;
    static double power =1.002;//of growth of radius
    static double theta = 0;//start from (1,0)
    static double Pi = 3.141592654;
    static double md = 2*Pi;//modular controller for colour-changing period
    static double x(){
        return FastMath.cos(theta)*radius;
    }
    static double y(){
        return FastMath.sin(theta)*radius;
    }
    static RGBHalfRainbow palette = new RGBHalfRainbow(-0,md+0);

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(-400,400);
        StdDraw.setYscale(-400,400);
        StdDraw.setPenRadius(0.007);
        int[] values;

        while (radius<=400){
            values = palette.getValue(theta%(md));
            double tempX = x();
            double tempY = y();
            StdDraw.setPenColor(values[0],values[1],values[2]);
            StdDraw.point(x(),y());
            radius = radius*power;//power growth strategy
            //radius += 0.4;//or- linear increment strategy
            theta += 0.1;
            StdDraw.line(tempX,tempY,x(),y());
        }

        System.out.println("Painting finished.");
    }
}

/*
   //System.out.printf("x,y %.3f, %.3f\n",x,y);
 */
