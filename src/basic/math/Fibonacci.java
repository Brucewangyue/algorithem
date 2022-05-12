package basic.math;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(f(9));
    }

    static int f(int n){
        if(n==0 || n==1)
            return n;

        return f(n-1) + f(n-2);
    }
}
