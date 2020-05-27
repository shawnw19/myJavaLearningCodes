package LanguageFeaturesRelated;

import java.util.*;
import java.util.function.BiFunction;

/*
* An exercise on techniques of basic parsing,
* String manipulations, list operations
* and operator precedence.

Test examples:
7.5*1.2/3 + 10*0.8 -1.5*7%7 (=7.5)
8+8*4.3 - 1.7^2 + 6*2.4/4 (=43.11)
 */
public class SimpleCalculator {
    static StringBuffer input;
    static TreeSet<Character> operators = new TreeSet<>(Arrays.asList('+', '-', '*', '/', '%', '^'));

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the expression: ");
        input = new StringBuffer(scanner.nextLine());
        inputCheck(input);
        LinkedList list = tokenize(input);

        evaluate(2, list);
        evaluate(1, list);

        System.out.println("Final result: " + list.peek());

    }

    static void evaluate(int precedence, LinkedList list) {
        //indexes of operators are in odd numbers (1,3,5 etc)
        int index = 1;//first operator encountered
        while (index < list.size() - 1) {//an operator cannot be the end
            //find an operator with approximate precedence
            while (index < list.size() - 1 && ((Operator) list.get(index)).pre == precedence) {
                Operator opr = (Operator) list.get(index);
                Double first = (Double) list.get(index - 1);
                Double second = (Double) list.get(index + 1);
                list.remove(index - 1);//the element on the right
                list.remove(index - 1);//that remained in the list will take the index
                list.remove(index - 1);
                list.add(index - 1, opr.apply(first, second));
            }

            index += 2;
        }
    }

    static void inputCheck(StringBuffer input) {
        for (int i = 0; i < input.length(); i++) {
            char ref = input.charAt(i);
            if (!(operators.contains(ref) || Character.isDigit(ref) || ref == '.' || Character.isWhitespace(ref)))
                throw new IllegalArgumentException("Contains unrecognised char at " + i);
        }
    }

    static LinkedList tokenize(StringBuffer input) {
        LinkedList list = new LinkedList();
        //the input StringBuffer is to be shortened during parsing
        while (input.length() > 0) {
            Object get = parseNext(input);
            list.add(get);
        }
        return list;
    }

    //return a Double or Operator object
    static Object parseNext(StringBuffer input) {
        String numStr = "";
        boolean stop = false;
        Object returnee = null;
        while (!stop) {
            char ref = input.charAt(0);
            input.deleteCharAt(0);
            if (operators.contains(ref)) {
                returnee = new Operator(ref);
                stop = true;
            } else if (Character.isDigit(ref) || ref == '.') {
                numStr += ref;
                //end of current number
                if (input.length() == 0 || !(Character.isDigit(input.charAt(0)) || (input.charAt(0) == '.'))) {
                    stop = true;
                }
                returnee = Double.parseDouble(numStr);
            }/*whitespaces are removed automatically
            }*/
        }

        return returnee;
    }


    static class Operator implements BiFunction<Double, Double, Double> {
        char opr;//operator
        int pre;//_cedence
        double result = Double.MIN_VALUE;

        public Operator(char opr) {
            this.opr = opr;
            if (opr == '+' || opr == '-') {
                pre = 1;
            } else {
                pre = 2;
            }
        }

        @Override
        public Double apply(Double first, Double second) {
            switch (opr) {
                case '+':
                    result = first + second;
                    break;
                case '-':
                    result = first - second;
                    break;
                case '*':
                    result = first * second;
                    break;
                case '/':
                    result = first / second;
                    break;
                case '%':
                    result = first % second;
                    break;
                case '^'://not the same as it in Java
                    result = Math.pow(first, second);
                    break;
            }
            return result;
        }
    }
}

/*
*Some errors:
* Using node to form a binary tree was proved to be
* unsuitable because the nodes contains heterogeneous
* elements which are hard to 'unite' to the same class.
* Further more, the precedence problem of arithmetic makes
* tree formation quite hard which in turn exacerbates the
* complexity.
* A statement "input = new StringBuffer(input.toString().trim());"
* was used to trim whitespaces and this caused the modification of
* input StringBuffer (passed by reference) void for further usage
* because a NEW object was created.



Node first = new Node(list.pollFirst());//can be a number or an operator
        Node root = new Node(list.pollFirst());//must be an operator
        //first.parent = root;
        root.left = first;
        Node next = new Node(list.pollFirst());
        if(!root.content.getClass().equals(Operator.class))
            throw new RuntimeException("Operator not followed by number");*/
//if(get.getClass().equals(Double.class)){

/*static void evaluate (LinkedList list,Node root, Node next){
     if(!list.isEmpty() && root.content.pre)
 }*/
/*
static class Node{
    Node parent=null;
    Node left=null;
    Node right=null;
    Object content;
    int pred = -1;

    public Node(Object content) {
        this.content = content;
            */
/*if(content.getClass().equals((Operator.class))){
                this.pred = (Operator)content.pre;
            }*//*

    }
}*/

        /*Operator div = new Operator('/');
        System.out.println(div.apply(1.8,0.9));*/