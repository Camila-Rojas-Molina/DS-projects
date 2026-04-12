// FIFO = First-In and First-Out, frist come first serve
package DataStruturesTest;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<String>();
        queue.offer("karen");
        queue.offer("miguel");
        queue.offer("chad");
        queue.offer("Harold");

        System.out.println(queue);
        //ofer= enqueue/insert at the rear,
        //poll= dequeue/remove at the front, 
        //peek=select/retrieve
        //contains()= search for a element adn returns tru or false, not index

        /*
        USES:
        1. keyboard buffer
        2. printer queue
        3.Bread first search algorithm
        
        */
    }
    
}
