package Neural_Network_Computing;
/*the rationale of this program is
the first order derivative of sigmoid function
which could be complex, can be approximated by
h*y*(1-y) when h is small
 */
import java.util.Scanner;

public class $4_SIGRESP {
    public static void main(String[] args) {
        double h;//change in the input, like delta
        double x,y,diff;
        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you wish to use for h? ");
        h = scanner.nextDouble();
        for (x = -5.0; x <=5.0 ; x+=0.5) {
            y= sigm(x);//k is set 1 as default
            diff = sigm(x+h) -y;
            System.out.printf("IN=%6.2f  OUT=%6.4f   increase/h=%6.4f  OUT*(1-OUT)=%6.4f\n", x, y,diff/h, y*(1.0-y));
        }
    }

    public static double sigm(double x){
        return 1.0/(1.0+Math.exp(-x));//as y
    }
}
