import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciRecursive {

    private static BigInteger a = new BigInteger("1");

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.print("Enter Fibonacci number to calculate: ");
        BigInteger fib = new BigInteger(kb.next());
        long start = System.currentTimeMillis();
        fib = fib(fib);
        double time = System.currentTimeMillis() - start;
        time = time / 1000;
        System.out.printf("\nFibonacci number: %s\nTime: %.5f", fib, time);

    }

    private static BigInteger fib(BigInteger fib) {

        if (fib.intValue() == 1) {
            return new BigInteger("0");
        }
        if (fib.intValue() == 2) {
            return new BigInteger("1");
        }
        BigInteger num1 = fib.subtract(a);
        BigInteger num2 = num1.subtract(a);
        return fib(num1).add(fib(num2));

    }


}
