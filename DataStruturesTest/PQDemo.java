package DataStruturesTest;
import java.util.*;

public class PQDemo {
    public static void main(String[] args) {

        // FIFO DS - Highest priority first unless reverseOrder() used - even with strings

        Queue<String> queue = new PriorityQueue<>();

        queue.offer("Bob");
        queue.offer("Cami");
        queue.offer("Ana");
        queue.offer("Fico");
        queue.offer("Faro");

        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }



        
    }
}
