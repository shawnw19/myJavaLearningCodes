package AlgebraicGeometry;

import IntroCS_Princeton.StdDraw;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
/*
The key points: 1.sum of angles formed by line segments of the point connecting vertices equals 2*pi
is the method for checking whether the point is inside the circle
2. check on collinearity is based on float accuracy after normalisation of vectors. The precision is
referred to https://github.com/dlegland/javaGeom/blob/master/src/main/java/math/geom2d/Vector2D.java#L100
with adjustment for personal visual experience.
 */
public class PointInTriangle {
    static double pi = Math.PI;
/*    static int[] vertex1;//x1,y1
    static int[] vertex2;
    static int[] vertex3;*/
    static int[][] vertices = new int[3][2];//pass by reference
    static int[] recRange;//xmin, xmax, ymin, ymax
    static Random rdm = new Random();
    static final double  ACCURACY  = 3e-4;
    public static void main(String[] args) {
        StdDraw.setCanvasSize(900,750);
        StdDraw.setXscale(-400,400);
        StdDraw.setYscale(-400,400);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.point(0,0);

        generateTriangle(new int[]{0,0},250);

        System.out.println("A: "+vertices[0][0]+", "+vertices[0][1]);
        System.out.println("B: "+vertices[1][0]+", "+vertices[1][1]);
        System.out.println("C: "+vertices[2][0]+", "+vertices[2][1]);

        StdDraw.setPenColor(Color.lightGray);//for outer boundary
        int[] xs = new int[]{vertices[0][0],vertices[1][0],vertices[2][0]};
        int[] ys = new int[]{vertices[0][1],vertices[1][1],vertices[2][1]};
        Arrays.sort(xs);
        Arrays.sort(ys);
        recRange= new int[]{xs[0],xs[2],ys[0],ys[2]};
        //System.out.println(recRange[0] +" "+recRange[1]+" " +recRange[2]+" " +recRange[3]);
        for (int i = 0; i <4 ; i++) {
            StdDraw.line(recRange[i/2],recRange[(i+1/2)%2+2],recRange[(i+1/2)%2],recRange[3-i/2]);
        }

        StdDraw.setPenRadius(0.009);//normal for edges
        StdDraw.setPenColor(Color.GRAY);
        for (int i = 0; i <3 ; i++) {//paint edges
            StdDraw.line(vertices[i][0],vertices[i][1],vertices[(i+1)%3][0],vertices[(i+1)%3][1]);
        }

        StdDraw.setPenRadius(0.012);//bigger for vertices
        StdDraw.setPenColor(Color.BLACK);
        for (int i = 0; i <3 ; i++) {//paint vertices
            StdDraw.point(vertices[i][0],vertices[i][1]);
        }

        StdDraw.setPenRadius(0.008);//for random points
        for (int i = 0; i <200 ; i++) {
            int[] p = generatePoint(recRange);
            int loc = inside(vertices[0],vertices[1],vertices[2],p);
            if(loc==-1){//outside
                StdDraw.setPenColor(Color.GREEN);
            }else if(loc==0){
                StdDraw.setPenColor(Color.CYAN);
            }else {
                StdDraw.setPenColor(Color.magenta);
            }

            StdDraw.point(p[0],p[1]);
        }

    }

    static void generateTriangle(int[] origin, int avgSideLength){
        double freeDegrees = 2*pi;
        double angle=0;
        for (int i = 0; i <3 ; i++) {
            //generate random angle with mean as 2*pi/3
            double angleP = rdm.nextGaussian()/5 +pi*2.2/3;
            while (angleP<0.03 || angleP>=(pi/2) || angleP>=freeDegrees){
                angleP = rdm.nextGaussian()*pi/6+2*pi/3;
            }
            angle += angleP;//consecutive
            double length = (rdm.nextGaussian()+1)*avgSideLength;
            while (length<=150 || length>390){
                length = (rdm.nextGaussian()+1)*avgSideLength;
            }
            int x = (int) (Math.cos(angle)*length);
            int y = (int) (Math.sin(angle)*length);
            vertices[i] = new int[]{x,y};
            freeDegrees -=angleP;
            //System.out.println("vertex"+i+": "+x+","+y+" l:"+length);//-
        }

    }
    static int inside(int[] vertex1,int[] vertex2,int[] vertex3,int[] thePoint){
        int location = -1;//for outside, 0 for on the edge , 1 for inside
        double[] PA = new double[]{vertex1[0]-thePoint[0],vertex1[1]-thePoint[1]};
        PA = new Vector2D(PA).normalize().toArray();
        double[] PB = new double[]{vertex2[0]-thePoint[0],vertex2[1]-thePoint[1]};
        PB = new Vector2D(PB).normalize().toArray();
        double[] PC = new double[]{vertex3[0]-thePoint[0],vertex3[1]-thePoint[1]};
        PC = new Vector2D(PC).normalize().toArray();
        double[] AB = new double[]{vertex2[0]-vertex1[0],vertex2[1]-vertex1[1]};
        AB = new Vector2D(AB).normalize().toArray();
        double[] BC = new double[]{vertex3[0]-vertex2[0],vertex3[1]-vertex2[1]};
        BC = new Vector2D(BC).normalize().toArray();
        double[] AC = new double[]{vertex3[0]-vertex1[0],vertex3[1]-vertex1[1]};
        AC = new Vector2D(AC).normalize().toArray();

        boolean onEdge = Math.abs(PA[0]*AB[1]-PA[1]*AB[0])<ACCURACY || Math.abs(PA[0]*AC[1]-PA[1]*AC[0])<ACCURACY || Math.abs(PB[0]*AB[1]-PB[1]*AB[0])<ACCURACY || Math.abs(PB[0]*BC[1]-PB[1]*BC[0])<ACCURACY || Math.abs(PC[0]*AC[1]-PC[1]*AC[0])<ACCURACY || Math.abs(PC[0]*BC[1]-PC[1]*BC[0])<ACCURACY;
        boolean in = false;
        if(onEdge){
            location =0;
            System.out.println("P: "+thePoint[0]+","+thePoint[1]+" on edge!");//-
        }else{
            Vector2D pa = new Vector2D(PA[0],PA[1]);
            Vector2D pb = new Vector2D(PB[0],PB[1]);
            Vector2D pc = new Vector2D(PC[0],PC[1]);
            /*Vector2D ab = new Vector2D(AB[0],AB[1]);
            Vector2D bc = new Vector2D(BC[0],BC[1]);
            Vector2D ac = new Vector2D(AC[0],AC[1]);*/
            in = Math.abs(Vector2D.angle(pa,pb)+ Vector2D.angle(pa,pc)+ Vector2D.angle(pb,pc) - 2*pi)<ACCURACY;
        }
        if(in){
            location = 1;
        }
        return location;
    }
    static int[] generatePoint(int[] range){
        int xrange = range[1]-range[0];
        int x = rdm.nextInt(xrange) + range[0];
        int yrange = range[3]-range[2];
        int y = rdm.nextInt(yrange) + range[2];

        return new int[]{x,y};
    }
}
