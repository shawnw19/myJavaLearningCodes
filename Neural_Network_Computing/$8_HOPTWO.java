package Neural_Network_Computing;
//- for tracing intermediate processes

import java.util.Random;
import java.util.Scanner;

public class $8_HOPTWO {
    public static double sigm(double x){
        return 1.0/(1.0+Math.exp(-x));//as y
    }

    public static void main(String[] args) {
        double [][] m = new double[5][5];
        double act, bigm=-100.0, sum=0.0;//bigm - biggest cost
        int [][] x= new int[5][5];
        int i1, i2, j1, j2, i,j,k;
        System.out.println("Type in the rows of costs like this: ");
        System.out.println("12 13.6 16 23.1");
        Scanner scanner = new Scanner(System.in);
        //get costs and initialise nodes
        for(i=1; i<5; i++){
            System.out.printf("What are the costs for row %d?\n",i);
            for (j=1;j<5;j++){
                m[i][j] = scanner.nextDouble();
                if(m[i][j]>bigm){
                    bigm = m[i][j];//find biggest cost
                }
                x[i][j] = 0;
                //x[i][j] = new Random().nextInt(2);//initialise node randomly//-original
            }
        }

        System.out.println("biggest cost: "+bigm);

        //randomly update nodes
        for(k=1; k<50; k++){//originally k<=250
            i1 = new Random().nextInt(4)+1;
            j1 = new Random().nextInt(4)+1;
            System.out.printf("old x%d%d=%d ",i1,j1,x[i1][j1]);

            act = 2*bigm -m[i1][j1];//threshold bias
            System.out.print("act= "+act+"| ");//-

            for (i2=1; i2<5 ;i2++){
                if (i2!=i1){
                    act -= 2*bigm * x[i2][j1];//inhibition from the row
                    System.out.print(act+" ");//-
                }

            }

            for (j2=1; j2<5 ;j2++){
                if (j2!=j1){
                    act -= 2*bigm * x[i1][j2];//inhibition from the column
                    System.out.print(act+" ");//-
                }

            }

            x[i1][j1] = (act>=0)?1:0;

            System.out.printf("Activisation=%7.2f  new x%d%d = %d \n",act, i1,j1, x[i1][j1]);
        }

        System.out.println("\nFinal Assignments are: ");
        for(i=1; i<5; i++){
            for (j=1;j<5;j++){
                System.out.printf(" %d ",x[i][j]);
                sum+= x[i][j]*m[i][j];
            }
            System.out.println();
        }

        System.out.printf("\nThe total cost is %7.4f ",sum);
    }
}
