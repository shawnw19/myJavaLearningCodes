package Chp00;

import java.util.Scanner;

public class HanoiTower {
    public static int steps =0;

    public static void solveTowers(int count, char source, char destination, char spare){
        if (count==1){
            System.out.println("Move top disk from pole "+source +" to pole "+ destination);
            steps=steps+1;
        }
        else {
            solveTowers(count-1,source,spare,destination);
            solveTowers(1,source,destination,spare);
            solveTowers(count-1,spare,destination,source);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of disks: ");
        int count=scanner.nextInt();
        //char is added with single quotation
        solveTowers(count,'A','B','C');

        System.out.println();
        System.out.println("All together "+steps+" steps.");
    }
}
