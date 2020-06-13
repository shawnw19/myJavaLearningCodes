package DataStructures_n_Algo;
/*
a simplification of a Scheme program with same purpose
""
(define sum-of-digits
    (lambda (n)
        (define sum-plus ;(sum of n’s digits) + addend
            (lambda (n addend)
                (if (= n 0)
                    addend
                    (sum-plus (quotient n 10) (+ addend (remainder n 10))))))
        (sum-plus n 0)))
""
It which shows that Lisp-like languages can make things more complex
in certain cases.
 */
public class SumOfDigits {
    static int sumOfDigits(int n){
        if(n<10){
            return n;
        }else {
            return n%10 + sumOfDigits(n/10);
        }
    }

    public static void main(String[] args) {
        System.out.println(sumOfDigits(127));//10
        System.out.println(sumOfDigits(80086));//22
    }
}
