package RGBHalfRainbow;

import IntroCS_Princeton.StdDraw;

public class RGBHalfRainbowTester {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(900,600);
        StdDraw.setPenRadius(0.04);
        StdDraw.setXscale(-0.05,1.05);

        RGBHalfRainbow rainbow = new RGBHalfRainbow(0,1);

        for (double i = 0; i <=1 ; i+=0.005) {
            int [] value = rainbow.getValue(i);
            StdDraw.setPenColor(value[0],value[1],value[2]);
            StdDraw.point(i,0.5);
        }
    }
}
