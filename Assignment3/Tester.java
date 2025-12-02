package Assignment3;

/**
 * Tester with 20+ representative cases demonstrating APQ functionality.
 */

public class Tester {

    public static void main(String[] args) {
        System.out.println("=== APQ Tester (20+ cases) ===\n");

        APQ<Integer,String> apq = new APQ<>(true); // start MIN mode
        System.out.println("Initial state: " + apq.state());

        // 1..5 inserts (should trigger up-heap operations)
        System.out.println("\n-- Insert 1..5 --");
        Entry<Integer,String> e1 = apq.insert(20, "A");
        Entry<Integer,String> e2 = apq.insert(5, "B");
        Entry<Integer,String> e3 = apq.insert(15, "C");
        Entry<Integer,String> e4 = apq.insert(10, "D");
        Entry<Integer,String> e5 = apq.insert(2, "E");
        System.out.println("After inserts: " + apq);

        // 6 top()
        System.out.println("\n-- top() --");
        System.out.println("top = " + apq.top());

        // 7 removeTop()
        System.out.println("\n-- removeTop() --");
        System.out.println("removed = " + apq.removeTop());
        System.out.println("now = " + apq);

        // 8 replaceKey: increase and decrease
        System.out.println("\n-- replaceKey (increase) e4 -> 100 --");
        apq.replaceKey(e4, 100);
        System.out.println("now = " + apq);
        System.out.println("\n-- replaceKey (decrease) e4 -> 1 --");
        apq.replaceKey(e4, 1);
        System.out.println("now = " + apq);

        // 9 replaceValue
        System.out.println("\n-- replaceValue e3 -> C-up --");
        System.out.println("old = " + apq.replaceValue(e3, "C-up"));
        System.out.println("now = " + apq);

        // 10 remove arbitrary entry e2
        System.out.println("\n-- remove(e2) --");
        System.out.println("removing: " + e2);
        apq.remove(e2);
        System.out.println("now = " + apq);

        // 11 more inserts to trigger array growth
        System.out.println("\n-- more inserts to trigger resize --");
        for (int i = 1; i <= 12; ++i) apq.insert(i * 3, "V" + i);
        System.out.println("size after many inserts = " + apq.size());
        System.out.println("apq = " + apq);

        // 12 peekAt
        System.out.println("\n-- peekAt(1), peekAt(3), peekAt(5) --");
        System.out.println("peekAt(1) = " + apq.peekAt(1));
        System.out.println("peekAt(3) = " + apq.peekAt(3));
        System.out.println("peekAt(5) = " + apq.peekAt(5));

        // 13 toggle to MAX
        System.out.println("\n-- toggle() to MAX --");
        apq.toggle();
        System.out.println("mode = " + apq.state());
        System.out.println("apq = " + apq);
        System.out.println("top (max) = " + apq.top());

        // 14 removeTop in MAX mode
        System.out.println("\n-- removeTop (MAX) --");
        System.out.println("removed = " + apq.removeTop());
        System.out.println("now = " + apq);

        // 15 toggle back to MIN
        System.out.println("\n-- toggle() back to MIN --");
        apq.toggle();
        System.out.println("mode = " + apq.state());
        System.out.println("apq = " + apq);

        // 16 merge with another APQ
        System.out.println("\n-- merge() test --");
        APQ<Integer,String> other = new APQ<>(true);
        other.insert(100, "X");
        other.insert(4, "Y");
        other.insert(55, "Z");
        System.out.println("other = " + other);
        apq.merge(other);
        System.out.println("after merge apq = " + apq);

        // 17 replaceKey on an element from merged part
        System.out.println("\n-- replaceKey on merged element --");
        // find a reference (top is safe)
        Entry<Integer,String> topRef = apq.top();
        System.out.println("topRef = " + topRef);
        apq.replaceKey(topRef, -999);
        System.out.println("after replaceKey(topRef,-999) apq = " + apq);

        // 18 remove last inserted element
        System.out.println("\n-- insert and then remove that entry --");
        Entry<Integer,String> last = apq.insert(9999, "LAST");
        System.out.println("inserted last = " + last);
        apq.remove(last);
        System.out.println("after remove(last) apq = " + apq);

        // 19 replaceValue returns old value
        System.out.println("\n-- replaceValue example --");
        Entry<Integer,String> some = apq.insert(42, "orig");
        System.out.println("old value = " + apq.replaceValue(some, "updated"));
        System.out.println("apq = " + apq);

        // 20 drain a few elements to show repeated down/up heap ops
        System.out.println("\n-- drain 5 elements --");
        for (int i = 0; i < 5 && !apq.isEmpty(); ++i) {
            System.out.println("popped: " + apq.removeTop());
        }
        System.out.println("apq (after partial drain) = " + apq);

        // Extra: confirm peekAt out of bounds throws
        System.out.println("\n-- peekAt out-of-bounds test (should throw) --");
        try {
            apq.peekAt(apq.size() + 1);
            System.out.println("ERROR: peekAt did not throw");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("caught expected exception: " + ex.getMessage());
        }

        System.out.println("\n=== Tester completed ===");
    }
}
