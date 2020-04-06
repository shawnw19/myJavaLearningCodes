package Neural_Network_Computing;

import IntroCS_Princeton.StdDraw;
import RGBHalfRainbow.RGBHalfRainbow;

import java.util.Random;

/*
a demonstration of naive orthogonal tuning-like
linear search for extreme value(s) of a function
(here a real-valued math expression)
 */
public class LinearSearchGradientDescentLite {
    static double min, max, diff,//range and delta
            tmin,//(temporary) minimum value of f
            x,y,mindel;//point coordinate and (if any)minimum delta of function
    static int direction,//0 for left/down, 1 for right/up
            count=0;
    static boolean xtrap = false, ytrap = false;//stop if new x/y doesn't make enough delta
    static boolean inRange(double var){
        return (var<=(max-diff)&var>=(min+diff));
    }
    static void printInterm(){//selective output of intermediate result
        if (count<5|count%5==0){
            System.out.printf("tmin: %7.5f\n",tmin);//-
        }
    }
    static RGBHalfRainbow point = new RGBHalfRainbow(-0.5,2.5);
    static void paintDot(double value){
        int [] values = point.getValue(value);//of RGB
        StdDraw.setPenColor(values[0],values[1],values[2]);
        StdDraw.point(x,y);
    }
    public static void main(String[] args) {
        min = -5.0;
        max =3.0;
        diff = 0.01;
        x = 1.1;//1.09 to left
        y = 0;
        tmin= func(x,y);
        System.out.printf("tmin: %7.5f\n",tmin);//-

        StdDraw.setCanvasSize(800,800);
        StdDraw.setPenRadius(0.012);
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min,max);

        for (int i = 0; i <1500 ; i++) {
            count++;
            direction = (new Random().nextInt(2)>0)?1:-1;//right or left/up or down
            if (inRange(x)&func(x +direction*diff,y)<=tmin){
                x += direction*diff;//suppose direction for x decreases f
                tmin = func(x,y);
                printInterm();
                alterY();
                paintDot(tmin);
            }else if (inRange(x) & func(x -direction*diff,y)<=tmin){
                x -= direction*diff;//another direction for x
                tmin = func(x,y);
                System.out.printf("tmin: %7.5f\n",tmin);//-
                alterY();
                paintDot(tmin);
            }else {
                xtrap = true;
            }

            if (xtrap&ytrap){
                System.out.println("Trapped in a local minima after "+count+" searches.");
                break;
            }
        }

        System.out.printf("\nFinal minimum is: %7.5f\n",tmin);
        System.out.printf("Stopped at: (%.2f, %.2f)\n",x, y);

    }

    static void alterY(){
        if (inRange(y) & func(x,y+direction*diff)<=tmin){
            y += direction*diff;
            tmin = func(x,y);
            System.out.printf("tmin: %7.5f\n",tmin);//-
            paintDot(tmin);
        }else if (inRange(y) & func(x,y-direction*diff)<=tmin){
            y -= direction*diff;//another direction for y
            tmin = func(x,y);
            System.out.printf("tmin: %7.5f\n",tmin);//-
            paintDot(tmin);
        }else {
            ytrap = true;
        }
    }

    static double func(double x, double y){
        return 0.1 + (1.0+Math.sin(2*x+3*y)/3.5+Math.sin(x+y));
    }
}
