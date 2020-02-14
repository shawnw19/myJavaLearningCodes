package Chp00;

public class DecimalDivision {
    public static int [] dig;
    public static void div(double num1, double num2){
        for (int i = 0; i <11 ; i++) {
            int temp = (int) (num1/num2);
            dig[i] = temp;
            num1 = 10*(num1-temp*num2);
        }
    }

    public static void main(String[] args) {
        dig = new int[11];
        double num1 = 11.2, num2= 5.5;
        div(num1,num2);
        System.out.println("The result is: ");
        System.out.print(dig[0]+"."+dig[1]);
        if (dig[1]+dig[2]+dig[3]!=0){
            //none of the first three decimals are zero
            for (int i = 2; i <11 ; i++) {
                System.out.print(dig[i]);
            }
        }


    }
}
