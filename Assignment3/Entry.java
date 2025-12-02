package Assignment3;
/**
 * Simple top-level Entry class for APQ.
 * The 'index' field is package-private so APQ (same package) can update it.
 */
public class Entry<K, V> {
    private K key;
    private V value;
    int index = -1; // index in the backing array; -1 when not in any heap

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }

    public void setKey(K newKey) { this.key = newKey; }
    public void setValue(V newValue) { this.value = newValue; }

    public int getIndex() { return index; }
    // package-private setter used by APQ:
    void setIndex(int idx) { this.index = idx; }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}
