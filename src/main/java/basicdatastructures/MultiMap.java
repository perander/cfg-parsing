package basicdatastructures;

/**
 * A basic implementation of a multimap. Multimap allows multiple values per key.
 * The key and value sets are represented as lists with each element only appearing once.
 *
 * @param <K> type of key
 * @param <V> type of value
 */
public class MultiMap<K, V> {
    int n;
    int size;
    private List<K>[] keys;
    private List<V>[] values;

    private List<K> keySet;
    private List<V> valueSet;

    /**
     * Creates an empty multimap.
     */
    public MultiMap() {
        n = 100;
        keys = new List[n];
        for (int i = 0; i < n; i++) {
            keys[i] = new List();
        }
        values = new List[n];
        for (int i = 0; i < n; i++) {
            values[i] = new List<>();
        }

        keySet = new List();
        valueSet = new List();
    }

    /**
     * Determines the index of the object to be added
     *
     * @param key
     * @return int determining the objects index in the table
     */
    private int hash(Object key) {
        int s = key.hashCode();
        s = s % n;
        return s;
    }

    /**
     * Add a new key value pair. If the key already exists,
     * the corresponding value is not replaced, but the pair is added as a new pair.
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = hash(key);
        keys[index].add(key);
        values[index].add(value);

        size++;

        keySet.addIfAbsent(key);
        valueSet.addIfAbsent(value);
    }

    /**
     * Fetch all values for a given key.
     *
     * @param key
     * @return
     */
    public List<V> get(K key) {
        int index = hash(key);
        List ks = keys[index];
        List vs = new List();

        for (int i = 0; i < ks.size(); i++) {
            if (ks.get(i).equals(key)) { //the array might contain other keys with the same hashcode
                vs.add(values[index].get(i));
            }
        }
        return vs;
    }

    /**
     * Checks whether the map is empty.
     * @return true if the size is 0, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The size of the map: the number of all distinct keys.
     *
     * @return the size of the map
     */
    public int size() {
        return size;
    }

    /**
     * Return a list of keys, each only occurring once
     *
     * @return keys as set
     */
    public List<K> keySet() {
        return keySet;
    }

    /**
     * Return a list of values, each only occurring once
     *
     * @return values as set
     */
    public List<V> valueSet() {
        return valueSet;
    }
}
