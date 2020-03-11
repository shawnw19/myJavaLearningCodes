package Chp00;

/*
based on The Nature and Power of Mathematics 1993
chp5.2 Bifurcation Diagrams for various values of k
an example of chaotic behaviours of the systems
after two R implementations
*/

import IntroCS_Princeton.StdDraw;

public class Bifurcation {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000,400);
        StdDraw.setXscale(0,640);
        StdDraw.setYscale(0,200);
        StdDraw.setPenColor(StdDraw.BLACK);

        double k,p;
        for (int i = 1; i <640 ; i++) {
            k = 2.72 + i*0.02;//gradual ascend
            p=0.5;
            for (int j = 0; j <250 ; j++) {
                if (j>=100){
                    /*if (Double.isInfinite(p)){
                        p=Math.random();
                    }*/
                    StdDraw.point(i,200*p);
                }
                p = k*p*(1-p);
            }
        }
    }
}
