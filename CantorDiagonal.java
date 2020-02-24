package Chp00;

import java.util.ArrayList;
/*
a simulation of restricted/limited Cantor diagonalisation
with finite decimals, e.g. 10.
 */

/*
interesting facts:
1 count after first repeat of generated number is 10*numOfDigits - numOfExisting number
2 the first decimal of first existing number e.g. 1 of 0.14... has strongest effect on
the corresponding decimal of first repeating number, usually +1
 */

public class CantorDiagonal {
    public static String pi= "0.1415926536";
    public static String ee= "0.7182818284";//decimal part of e
    public static String st= "0.4142135624";// of sqrt 2
    public static String fi= "0.5000000000";
    public static String gi= "0.6111111111";
    public static String hi= "0.9876543210";
    public static String num;//new generated number
    public static ArrayList list;//to store numbers
    public static int count=0;

    public static int compute(int numOfDigits){
        boolean addable = true;
        int count2=0;
        while (count2<5){
            num = "0.";
            int ptr = (list.size()<=numOfDigits)? 0 :(list.size()-numOfDigits);//list index pointer
            int ptr2 = numOfDigits-(list.size()-ptr)+2;//0to9 string index pointer
            for (int i = ptr; i < list.size(); i++) {
                String temp= (String) list.get(i);
                int temp2 = Character.getNumericValue(temp.charAt(ptr2));
                num += (temp2+1)%9;
            }
            if (list.size()<numOfDigits){
                for (int i = 0; i <numOfDigits-list.size() ; i++) {
                    num += "0";
                }
            }
            /*if ((count>1000) || list.contains(num)){
                addable = false;*/
            if (list.contains(num)){
                count2 += 1;
                System.out.println("Repeat: "+num);
            }else {
                list.add(num);
                count++;
                System.out.println(num);
            }
        }

        return count;

    }
    public static void main(String[] args) {
        list = new ArrayList();
        list.add(pi);
//        list.add(ee);
//        list.add(st);
//        list.add(fi);
//        list.add(gi);
//        list.add(hi);

        int digi = compute(10);

        System.out.println("--------------");
        System.out.println(digi);

    }
}
