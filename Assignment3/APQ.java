package Assignment3;

import java.util.NoSuchElementException;

/**
 * APQ: Advanced Priority Queue implemented on a dynamically resizable raw array.
 * Supports MIN and MAX modes (toggle), arbitrary entry removal, key/value replacement,
 * peekAt(n) (non-destructive), and efficient merge (append + bottom-up heapify).
 *
 * K must be Comparable<K>.
 */
public class APQ<K extends Comparable<K>, V> {

    private Entry<K,V>[] heap; // raw array backing store
    private int size;          // number of elements currently in heap
    private int capacity;      // current capacity of heap array
    private boolean isMin;     // true => MIN mode; false => MAX mode

    @SuppressWarnings("unchecked")
    public APQ(boolean startInMin) {
        this.capacity = 16; // initial capacity (power of two recommended)
        this.heap = (Entry<K,V>[]) new Entry[capacity];
        this.size = 0;
        this.isMin = startInMin;
    }

    // ---------------------- Utilities ------------------------

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= capacity) return;
        int newCap = capacity;
        while (newCap < minCapacity) newCap *= 2;
        @SuppressWarnings("unchecked")
        Entry<K,V>[] newArr = (Entry<K,V>[]) new Entry[newCap];
        for (int i = 0; i < size; ++i) newArr[i] = heap[i];
        heap = newArr;
        capacity = newCap;
    }

    private int compareKeys(K a, K b) {
        // returns negative if a is "better" than b (i.e., should be above in heap)
        // In MIN mode, smaller keys are better => a.compareTo(b)
        // In MAX mode, larger keys are better => reverse order
        return isMin ? a.compareTo(b) : b.compareTo(a);
    }

    private void swap(int i, int j) {
        Entry<K,V> t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
        heap[i].setIndex(i);
        heap[j].setIndex(j);
    }

    private void heapifyUp(int idx) {
        while (idx > 0) {
            int p = (idx - 1) / 2;
            if (compareKeys(heap[idx].getKey(), heap[p].getKey()) < 0) {
                swap(idx, p);
                idx = p;
            } else break;
        }
    }

    private void heapifyDown(int idx) {
        int n = size;
        while (true) {
            int l = 2*idx + 1;
            int r = 2*idx + 2;
            int best = idx;
            if (l < n && compareKeys(heap[l].getKey(), heap[best].getKey()) < 0) best = l;
            if (r < n && compareKeys(heap[r].getKey(), heap[best].getKey()) < 0) best = r;
            if (best == idx) break;
            swap(idx, best);
            idx = best;
        }
    }

    // ---------------------- ADT methods ------------------------

    /** toggle(): switch MIN <-> MAX and rebuild heap bottom-up (O(n)). */
    public void toggle() {
        isMin = !isMin;
        // rebuild using bottom-up heapify
        for (int i = (size/2); i >= 0; --i) {
            heapifyDown(i);
        }
    }

    /** removeTop(): remove and return top entry (min or max depending on mode). */
    public Entry<K,V> removeTop() {
        if (size == 0) throw new NoSuchElementException("APQ is empty");
        Entry<K,V> top = heap[0];
        Entry<K,V> last = heap[size-1];
        heap[size-1] = null;
        size--;
        if (size == 0) {
            top.setIndex(-1);
            return top;
        }
        heap[0] = last;
        heap[0].setIndex(0);
        heapifyDown(0);
        top.setIndex(-1);
        return top;
    }

    /** insert(k,v): add a new entry and return it. */
    public Entry<K,V> insert(K k, V v) {
        ensureCapacity(size + 1);
        Entry<K,V> e = new Entry<>(k, v);
        heap[size] = e;
        e.setIndex(size);
        size++;
        heapifyUp(e.getIndex());
        return e;
    }

    /** top(): peek top without removal. */
    public Entry<K,V> top() {
        if (size == 0) return null;
        return heap[0];
    }

    /** remove(e): remove arbitrary entry e and return it. O(log n). */
    public Entry<K,V> remove(Entry<K,V> e) {
        if (e == null) throw new IllegalArgumentException("null entry");
        int idx = e.getIndex();
        if (idx < 0 || idx >= size || heap[idx] != e)
            throw new IllegalArgumentException("entry not in this APQ");
        Entry<K,V> last = heap[size-1];
        heap[size-1] = null;
        size--;
        if (idx == size) { // removed last element
            e.setIndex(-1);
            return e;
        }
        heap[idx] = last;
        heap[idx].setIndex(idx);
        // decide to up or down
        int p = (idx == 0) ? -1 : (idx-1)/2;
        if (p >= 0 && compareKeys(heap[idx].getKey(), heap[p].getKey()) < 0) {
            heapifyUp(idx);
        } else {
            heapifyDown(idx);
        }
        e.setIndex(-1);
        return e;
    }

    /** replaceKey(e, k): set key and return old key. O(log n). */
    public K replaceKey(Entry<K,V> e, K newKey) {
        if (e == null) throw new IllegalArgumentException("null entry");
        int idx = e.getIndex();
        if (idx < 0 || idx >= size || heap[idx] != e)
            throw new IllegalArgumentException("entry not in this APQ");
        K old = e.getKey();
        e.setKey(newKey);
        // decide direction
        if (compareKeys(newKey, old) < 0) {
            heapifyUp(idx);
        } else if (compareKeys(newKey, old) > 0) {
            heapifyDown(idx);
        }
        return old;
    }

    /** replaceValue(e, v): set value and return old value. O(1). */
    public V replaceValue(Entry<K,V> e, V newValue) {
        if (e == null) throw new IllegalArgumentException("null entry");
        int idx = e.getIndex();
        if (idx < 0 || idx >= size || heap[idx] != e)
            throw new IllegalArgumentException("entry not in this APQ");
        V old = e.getValue();
        e.setValue(newValue);
        return old;
    }

    /** state(): returns "MIN" or "MAX" */
    public String state() { return isMin ? "MIN" : "MAX"; }

    /** isEmpty() */
    public boolean isEmpty() { return size == 0; }

    /** size() */
    public int size() { return size; }

    /**
     * peekAt(n): non-destructive. 1-based (peekAt(1) == top()).
     * Implementation: shallow-clone this APQ into a temporary APQ and removeTop n-1 times.
     * Throws IndexOutOfBoundsException if n out of range.
     */
    public Entry<K,V> peekAt(int n) {
        if (n < 1 || n > size) throw new IndexOutOfBoundsException("n out of bounds");
        APQ<K,V> tmp = this.shallowClone(); // clones entries into a new heap (O(n))
        for (int i = 1; i <= n-1; ++i) tmp.removeTop();
        return tmp.top();
    }

    // shallow clone: copies keys & values into new entries and array (non-destructive)
    private APQ<K,V> shallowClone() {
        APQ<K,V> clone = new APQ<>(this.isMin);
        clone.ensureCapacity(this.size);
        for (int i = 0; i < this.size; ++i) {
            Entry<K,V> e = this.heap[i];
            Entry<K,V> ne = new Entry<>(e.getKey(), e.getValue());
            clone.heap[i] = ne;
            ne.setIndex(i);
            clone.size++;
        }
        // heap ordering preserved because array positions copied; no need to rebuild
        return clone;
    }

    /**
     * merge(other): append clones of other's entries and then bottom-up heapify.
     * Complexity: O(n + m).
     */
    public void merge(APQ<K,V> other) {
        if (other == null || other.size == 0) return;
        int total = this.size + other.size;
        ensureCapacity(total);
        // append clones
        for (int i = 0; i < other.size; ++i) {
            Entry<K,V> e = other.heap[i];
            Entry<K,V> ne = new Entry<>(e.getKey(), e.getValue());
            this.heap[this.size] = ne;
            ne.setIndex(this.size);
            this.size++;
        }
        // bottom-up heapify
        for (int i = (this.size/2); i >= 0; --i) heapifyDown(i);
    }

    // --------------------- Utility: snapshot for debugging ---------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; ++i) {
            sb.append(heap[i].toString());
            if (i+1 < size) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
