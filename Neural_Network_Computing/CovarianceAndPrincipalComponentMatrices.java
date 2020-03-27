package Neural_Network_Computing;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;

import java.util.Random;

/*a light demo of processing
small-scaled variance matrix into covariance matrix
and principal component matrix.
implementation of Bharath 1994 Appendix B pp153-165
 */
public class CovarianceAndPrincipalComponentMatrices {
    static int nvar=3, dim=10;//no. of variables, dimensions
    static void printMatrix(double[][] m, int ncol,int nrow){
        for (int i = 0; i <nrow ; i++) {
            for (int j = 0; j <ncol ; j++) {
                System.out.printf("%.2f ",m[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        /*initialise original matrix
        -suppose variable 1 is the main, v2 secondary, v3 least
         */
        double[][] V = new double[dim][nvar];//ten observations each
        for (int i = 0; i <dim ; i++) {
            V[i][0] = i+1;
            V[i][1] = i+10.0*(new Random().nextDouble());//or Math.random(),here v2 is partly dependent on vr
            V[i][2] = i+30.0*(new Random().nextDouble());
        }

        System.out.println("Original matrix: ");
        printMatrix(V, nvar, dim);
        System.out.println();

        double[][] stdM = MatrixUtils.createRealMatrix(V).getData();//copy V to make the original one preserved
        for (int i = 0; i <nvar ; i++) {
            standardizeAndCopyBack(stdM,i);
        }//it becomes a matrix of deviations

        System.out.println("Standardised matrix: ");
        printMatrix(stdM, nvar, dim);
        System.out.println();

        //make covariance matrix
        RealMatrix tempStdM = MatrixUtils.createRealMatrix(stdM);
        RealMatrix tempCM = tempStdM.transpose().multiply(tempStdM).scalarMultiply(1.0/(dim-1));//get the encapsulated form of Covariance Matrix
        double[][] CM = tempCM.getData();
        System.out.println("Covariance matrix: ");
        printMatrix(CM, nvar, nvar);//a square matrix now
        System.out.println();

        //make eigen matrix from the covariance matrix
        EigenDecomposition e = new EigenDecomposition(tempCM);
        double[][] E = new double[nvar][nvar];//eigen matrix of CM
        double[] es = new double[nvar];//list of eigenvalues
        for (int i = 0; i <nvar ; i++) {
            es = e.getRealEigenvalues();
            E[i] =e.getEigenvector(i).toArray();//sum of eigen vectors but transposed
        }
        RealMatrix tempE = MatrixUtils.createRealMatrix(E).transpose();
        E = tempE.getData();

        System.out.println("Eigenvector matrix: ");
        printMatrix(E, nvar, nvar);//a square matrix now
        System.out.println("eigenvalues:");
        for (int i = 0; i <nvar ; i++) {
            System.out.printf("%.3f ",es[i]);
        }
        System.out.println("\n");

        //make principal component matrix and the covariance matrix of itself
        //from
        RealMatrix tempPC = tempCM.multiply(tempE);
        double[][]  PC = tempPC.getData();
        System.out.println("Principal component matrix: ");
        printMatrix(PC, nvar, nvar);
        System.out.println();

        PC= tempPC.transpose().multiply(tempPC).getData();
        printMatrix(PC, nvar, nvar);

    }

    static void standardizeAndCopyBack(double[][] matrix, int v){
        //take the matrix and (v+1)th variable/v-th column
        double [] temp = new double[dim];
        for (int i = 0; i <dim ; i++) {
            temp[i] = matrix[i][v];
        }
        double mean = StatUtils.mean(temp);
        double std = FastMath.sqrt(StatUtils.variance(temp));
        for (int i = 0; i <dim ; i++) {
            temp[i] = (temp[i]-mean)/std;
        }
        for (int i = 0; i <dim ; i++) {//copy back
            matrix[i][v]=temp[i];
        }
    }
}

/*
note:
adjusted mean/divided by n-1

//linearly dependent on v1
Math.log(i+2)+3*Math.random();
 */
