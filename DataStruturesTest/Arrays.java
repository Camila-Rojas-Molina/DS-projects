package DataStruturesTest;

public class Arrays {

    public static void main(String[] args) {

       int[] myArray = {3, 4, 5, 6};

       for (int num : myArray){
        System.out.print(num);
        System.out.print(", ");
       }
       System.out.println();
       myArray[2] = 10;

        for (int num : myArray){
        System.out.print(num);
        System.out.print(", ");
       }

    }
}
