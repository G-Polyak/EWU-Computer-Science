import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciDynamic {

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

        int n = fib.intValue();
        BigInteger f[] = new BigInteger[n+2];
        f[0] = new BigInteger("0");
        f[1] = new BigInteger("1");
        for (int i = 2; i <= n; i++) {
            f[i] = f[i-1].add(f[i-2]);
        }

        return f[n];

    }


}
