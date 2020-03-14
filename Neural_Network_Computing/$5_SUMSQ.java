package Neural_Network_Computing;

import java.util.Scanner;

public class $5_SUMSQ {
    public static void main(String[] args) {
        double xmin, xmax, x, r, h, diff, newss, oldss;
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is the correct response (r)? " );
        r = scanner.nextDouble();
        System.out.print("What is the minimum value of x? " );
        xmin = scanner.nextDouble();
        System.out.print("What is the maximum value of x? " );
        xmax = scanner.nextDouble();
        h =(xmax-xmin)/16.0;//stipulated as increment -h
        x = xmin;
        oldss= Math.pow((r-x),2);
        System.out.printf("  x    squared error  change  \n");
        System.out.printf("%8.4f  %8.4f \n", x, oldss);
        for (x= x+h; x<=xmax; x+=h){
            newss = Math.pow((r-x),2);
            diff = newss - oldss;
            System.out.printf("%8.4f  %8.4f  %8.4f  \n", x, newss, diff);
            oldss =newss;
        }
    }
}

/*observation:
linear change of diff(changes of squared error)
 */