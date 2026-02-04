//// *******************************************************************
// Author: Camila Rojas Molina ID 40321494, Section S
// & Daniel Tehrani ID:40100248, Section X
// COMP 352 WINTER 26 – Programming Assignment 1
// *******************************************************************
package FinalTetranacci;

public class TetranacciMultiple {
// n being the position in the tetranacci sequence:
    public static long tetranacci(int n){

        //base case
        if (n==0 || n==1 || n==2) return 0;
        //different second base case
        if (n==3) return 1;

        return tetranacci(n-1) + tetranacci(n-2) + tetranacci(n-3) + tetranacci(n-4);
    }

    public static void main(String[] args) {
        System.out.println("---- Multiple Recursive (Exponential) ----");
        for (int n = 5; n <= 100; n += 5) {
            long start = System.currentTimeMillis();
            long result = tetranacci(n);
            long end = System.currentTimeMillis();
            System.out.printf("Tetranacci(%d) = %d | Time = %d ms%n", n, result, (end - start));
        }
    }
}

