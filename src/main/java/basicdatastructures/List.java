package basicdatastructures;

import java.util.Arrays;
import java.util.Objects;

/**
 * Basic implementation of a list of objects.
 */
public class List<T> {
    private int space; //full size (content + empty cells)
    private int size; //only content
    private Object[] table;

    /**
     * Creates an empty List of length 5.
     */
    public List() {
        this.space = 10;
        this.table = new Object[space];
        this.size = 0;
    }

    /**
     * Return the object in the cell at position i in the able
     *
     * @param i position of the cell
     * @return the object found in the cell
     */
    @SuppressWarnings("unchecked")
    public T get(int i) {
        return (T) table[i];
    }

    /**
     * Returns the size of the list: the number of cells with actual content
     *
     * @return number of cells with content
     */
    public int size() {
        return size;
    }

    /**
     * Add a new object to the list. If the array is already full, it is copied to a new array with 2 times as many cells as the original one.
     * The more additions there are, the less often the table needs to be expanded.
     * The less additions there are, the less the space will increase every time it is expanded.
     * <p>
     * The new object is then added to the first null cell of the (possibly updated) table.
     *
     * @param o object to be added
     */
    public void add(T o) {
        if (space == size) {
            Object[] copyContent = new Object[2 * space];

            for (int i = 0; i < space; i++) {
                copyContent[i] = table[i];
            }
            table = copyContent;
            space = 2 * space;
        }

        table[size] = o;
        size++;
    }

    /**
     * Checks whether the list contains an object o.
     *
     * @param o the given object
     * @return true if the array contains the object, false otherwise.
     */
    public boolean contains(T o) {
        if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (table[i].equals(o) || table[i] == o) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a String representing the content of the table.
     *
     * @return a String with the contents of the table separated by a space
     */
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            s += table[i] + " ";
        }
        s += "]";
        return s;
    }

    /**
     * Add an object to the list only if the list does not yet contain it.
     *
     * @param value
     */
    public void addIfAbsent(T value) {
        if (!this.contains(value)) {
            this.add(value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        List<?> list = (List<?>) o;
        return size == list.size &&
                Arrays.equals(table, list.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }
}
