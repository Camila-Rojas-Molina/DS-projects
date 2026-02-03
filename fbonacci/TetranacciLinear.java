// Camila Rojas Molina - 40321494
// COMP 352 – Programming Assignment 1
// TetranacciLinear.java – Linear recursive with memoization

import java.util.HashMap;

public class TetranacciLinear {
    private static HashMap<Integer, Long> memo = new HashMap<>();

    public static long tetranacci(int n) {
        if (memo.containsKey(n)) return memo.get(n);
        long value;
        if (n == 0 || n == 1 || n == 2) value = 0;
        else if (n == 3) value = 1;
        else value = tetranacci(n-1) + tetranacci(n-2) + tetranacci(n-3) + tetranacci(n-4);
        memo.put(n, value);
        return value;
    }

    public static void main(String[] args) {
        System.out.println("---- Linear Recursive (Memoization) ----");
        for (int n = 5; n <= 200; n += 5) {
            long start = System.currentTimeMillis();
            long result = tetranacci(n);
            long end = System.currentTimeMillis();
            System.out.printf("Tetranacci(%d) = %d | Time = %d ms%n", n, result, (end - start));
        }
    }
}
