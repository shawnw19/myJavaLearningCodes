package Chp00;

/*
the famous Coffee Can Problem, [Programming Pearls ed1] column4
 */
import org.apache.commons.math3.complex.Complex;

import java.util.LinkedList;
import java.util.Random;

public class CoffeeCanProblem {
    static int cap=200;//_acity of the bucket/can
    static int blNum=0 ;// num of blacks
    static LinkedList bucket = new LinkedList<Complex>();//LinkedList is for convenience of manipulating data
    static int count=1;//of operations(also num. of balls added)
    static Random rdm = new Random();

    public static void main(String[] args) {

        //initialise the bucket/can
        for (int i = 0; i <cap ; i++) {
            if (rdm.nextInt(2)>0){//white ball chosen
                bucket.add(new Complex(i,1));
            }else {
                bucket.add(new Complex(i,0));
                blNum++;
            }
        }

        System.out.println(blNum+" are black, "+(cap-blNum)+" are white.\n");

        //process
        while (bucket.size()>1){//more than one ball remain
            int temp1 = rdm.nextInt(bucket.size());
            int temp2 = rdm.nextInt(bucket.size());
            System.out.println("indexes: "+temp1+" "+temp2);//-
            if (temp1!=temp2){
                process(temp1,temp2);
            }else {
                while (temp1==temp2){
                    System.out.println("Same indexes chosen.");///-
                    temp2 = rdm.nextInt(bucket.size());
                }
                process(temp1,temp2);
            }
            count++;
        }

        String colOfLast = (((Complex)bucket.pop()).getImaginary()==0)?"black":"white";

        System.out.println("\nAfter "+count+" operations, the final one is a: "+colOfLast);
    }

    static void process(int index1, int index2){
        Complex ball1 = (Complex) bucket.get(index1);
        Complex ball2 = (Complex) bucket.get(index2);
        //System.out.println(" "+ball1.getReal()+"-"+t1+" "+ball2.getReal()+"-"+t1);//-
        bucket.remove(ball1);
        bucket.remove(ball2);
        int t1 = (int) ball1.getImaginary();
        int t2 = (int) ball2.getImaginary();

        //System.out.println(t1+" "+t2);//-

        if (t1==t2){//same colour
            bucket.add(new Complex(cap+count, 0));//add a black one
            String col = (t1==0)?"black":"white";
            if(t1==0){//two blacks
                blNum--;
            }else {//two whites
                blNum++;
            }
            System.out.println("Two "+col+" balls. "+blNum+" blacks+ "+(bucket.size()-blNum)+" whites= "+bucket.size());

        }else {//different colour

            bucket.add(new Complex(cap+count, 1));//add a white as substitution
            blNum--;
            System.out.println("Different balls. "+blNum+" blacks+ "+(bucket.size()-blNum)+" whites= "+bucket.size());
        }
    }
}
