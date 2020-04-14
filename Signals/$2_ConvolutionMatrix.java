package Signals;
/*
to confirm convolution operation is communicative
 */
public class $2_ConvolutionMatrix {
    static int n=6;//of vector U
    static int m=15;//of vector V
    public static void main(String[] args) {
        System.out.println(n+" elements * "+m+" elements:");
        for (int i = 0; i <n ; i++) {//nXm
            for (int j = 0; j <m ; j++) {
                if (j<10)
                    System.out.printf("%d,%d |", (j+i)%n, j);//format "U,V"
                else
                    System.out.printf("%d,%2d |", (j+i)%n, j);
            }
            System.out.println();
        }

        System.out.println("----------------\n");
        System.out.println(m+" elements * "+n+" elements:");
        for (int i = 0; i <m ; i++) {//mXn
            for (int j = 0; j <n ; j++) {
                System.out.printf("%2d,%d ",i, (j+i)%n);//format "V,U"
            }
            //add horizontal segment lines
            System.out.println();
            for (int j = 0; j <5*n ; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }
}
