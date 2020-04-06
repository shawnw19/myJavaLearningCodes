package Neural_Network_Computing;

import IntroCS_Princeton.StdDraw;
import RGBHalfRainbow.RGBHalfRainbow;

import java.util.Random;

public class LinearSearch2 {static double min, max, diff,//range and delta
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
    static RGBHalfRainbow point = new RGBHalfRainbow(-7,4);
    static void paintDot(double value){
        int [] values = point.getValue(value);//of RGB
        StdDraw.setPenColor(values[0],values[1],values[2]);
        StdDraw.point(x,y);
    }
    public static void main(String[] args) {
        min = -2.5;
        max = 3.5;
        diff = 0.01;
        x = 0.9;
        y = -0.5;
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

    public static double func(double x, double y){
        return 3* Math.pow((1-x),2)*Math.pow(Math.E,(-1*Math.pow(x,2)-Math.pow((y+1),2)))-10*(x/5-Math.pow(x,3)-Math.pow(y,5))*Math.pow(Math.E,-1*(Math.pow(x,2)+Math.pow(y,2)))-1/3*Math.pow(Math.E,-1*(Math.pow(x+1,2)+Math.pow(y,2)));
    }
}

/*
3*(1-x)^2*e^(-x^2-(y+1)^2)-10*(x/5-x^3-y^5)*e^(-x^2-y^2)-1/3*e^(-(x+1)^2-y^2)
 */