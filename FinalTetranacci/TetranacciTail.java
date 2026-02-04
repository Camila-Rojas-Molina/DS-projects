// COMP 352 – Programming Assignment 1
// TetranacciTail.java – Tail recursive version
package finaltetranacci;

public class TetranacciTail {
    public static long tetranacci(int n) {
        return tetranacciHelper(n, 0, 0, 0, 1);
    }
    
    private static long tetranacciHelper(int n, long a, long b, long c, long d) {
        if (n == 0) return a;
        if (n == 1) return b;
        if (n == 2) return c;
        if (n == 3) return d;
        return tetranacciHelper(n - 1, b, c, d, a + b + c + d);
    }

    public static void main(String[] args) {
        System.out.println("---- Tail Recursive Version ----");
        for (int n = 5; n <= 200; n += 5) {
            long start = System.currentTimeMillis();
            long result = tetranacci(n);
            long end = System.currentTimeMillis();
            System.out.printf("Tetranacci(%d) = %d | Time = %d ms%n", n, result, (end - start));
        }
    }
}
