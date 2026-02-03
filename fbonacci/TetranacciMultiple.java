// Camila Rojas Molina - 40321494
// COMP 352 – Programming Assignment 1
// TetranacciMultiple.java – Multiple recursive (exponential)

public class TetranacciMultiple {
    public static long tetranacci(int n) {
        if (n == 0 || n == 1 || n == 2) return 0;
        if (n == 3) return 1;
        return tetranacci(n-1) + tetranacci(n-2) + tetranacci(n-3) + tetranacci(n-4);
    }

    public static void main(String[] args) {
        System.out.println("---- Multiple Recursive (Exponential) ----");
        for (int n = 5; n <= 200; n += 5) {
            long start = System.currentTimeMillis();
            long result = tetranacci(n);
            long end = System.currentTimeMillis();
            System.out.printf("Tetranacci(%d) = %d | Time = %d ms%n", n, result, (end - start));
        }
    }
}
