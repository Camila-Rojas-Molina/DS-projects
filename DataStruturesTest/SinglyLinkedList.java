package DataStruturesTest;
import java.util.*;

/* 
Advantages:
1. Dynamic Data Structure (allocates needed memory while running)
2. Insertion and Deletion of Nodes is easy. O(1)
3. No/Low memory waste

Disadvantages:
1. Greater memory usage (additional pointer)
2. No random access of elements (no index [i])
3. Accessing/searching elements is more time consuming. O(n)

Uses:
1. Implement Stacks/Queues
2. GPS navigation
3. Music playlist
*/

public class SinglyLinkedList {
    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<String>();

        /*
        linkedList.push("A");
        linkedList.push("B");
        linkedList.push("C");
        linkedList.push("D");
        linkedList.push("F");
        linkedList.pop();
        When stack, it wont matter to print from bottom to top, 
        cause at the end it's a linked list, which is from head to tail
        */

        linkedList.offer("A");
        linkedList.offer("B");
        linkedList.offer("C");
        linkedList.offer("D");
        linkedList.offer("F");
        //linkedList.poll();
        
        linkedList.add(4, "E");
        linkedList.remove("E");
        System.out.println(linkedList.indexOf("F"));

//you can add first, add last, remove as well

        System.out.println(linkedList);
    }
    
}
