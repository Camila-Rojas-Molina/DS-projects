// LIFO: LAST-In, First-Out
package DataStruturesTest;
import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("hola");
        stack.push("hi");
        stack.push("bonjour");
        stack.push("salam");

        String top = stack.peek();

        System.out.println(top);
        System.out.println(stack);
        stack.search("hola");
        System.out.println(stack.empty());
        
    }
}
/*USES
    1. Undo/redo text editors
    2. Bakctracking (maze, file directories)
    3. Browser navigations, back and forth
    4. calling functions (call stack)


*/
