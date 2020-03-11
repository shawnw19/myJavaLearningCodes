package Chp00;

//visualising Lorenz equation
//The Nature and Power of Mathematics 1993 chp5.3
//pp337 Basic -> Java

import IntroCS_Princeton.StdDraw;

public class Lorenz {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(1100,880);
        StdDraw.setXscale(-25,27);
        StdDraw.setYscale(-12,62);
        StdDraw.setPenRadius(0.003);//changeable
        double x=0, y=1, z=1,//changeable
                interval =0.008,//also changeable
                dx, dy, dz;
        for (int i = 0; i <5001 ; i++) {
            StdDraw.point(x,z);
//            System.out.println(x+", "+z);
            dx = interval*10*(y -x);
            dy = interval* (x* (28 - z) -y);
            dz = interval* (x*y -8*z/3);
            //line
            StdDraw.line(15*(x+20),3.7*z,15*(x+dx+20),3.7*(z+dz));
            x += dx;
            y += dy;
            z += dz;

        }

    }
}
