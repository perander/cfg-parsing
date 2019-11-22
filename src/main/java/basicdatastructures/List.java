package basicdatastructures;

/**
 * Basic implementation of a list of objects.
 */
public class List {
    private Object[] content;

    /**
     * Creates an empty List of length 5.
     */
    public List() {
        this.content = new Object[5];
    }

    /**
     * Returns the size of the list: the part of the array with actual content
     *
     * @return the index of the first null item of the array. Because of the indexing starting from 0, the size will be correct this way.
     */
    public int size() {
        for (int i = 0; i < content.length; i++) {
            if (content[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Add a new object to the list. If the array is already full, it is copied to a new array with 5 empty items in the end.
     * This array or the original with empty items left is then filled with the new object.
     *
     * @param o object to be added
     */
    public void add(Object o) {
        if (content[content.length - 1] != null) {
            Object[] copyContent = content;
            content = new Object[copyContent.length + 5];

            for (int i = 0; i < copyContent.length - 1; i++) {
                content[i] = copyContent[i];
            }
        }

        for (int i = 0; i < content.length; i++) {
            if (content[i] == null) {
                content[i] = o;
            }
        }

    }

    /**
     * Checks whether the list contains an object o.
     *
     * @param o the given object
     * @return true if the array contains the object, false otherwise.
     */
    public boolean contains(Object o) {
        for (int i = 0; i < content.length; i++) {
            if (content[i] == o) {
                return true;
            }
        }
        return false;
    }


}
