package datastructures;

/**
 * This class represents a dictionary data structure.
 *
 * @param <Key>   - It represents an object its class extends {@code Comparable} class.
 * @param <Value> - It represents a generic object.
 * @author Andrea Graziani
 * @version 1.3
 */
public interface Dictionary<Key extends Comparable<Key>, Value> {

    /**
     * This method is used to insert a {@code Key}-{@code Value} pair in current dictionary.
     * <p>
     * If this dictionary already contains an entry for the specified {@code Key}, the {@code Value} already present
     * for that {@code Key} is returned, after modifying the entry to contain the new element.
     * <p>
     * If this dictionary doesn't already have an entry for the specified {@code Key}, an entry is created for the
     * specified {@code Key}-{@code Value} pair, and {@code null} is returned.
     * <p>
     * Specified {@code Key} cannot be {@code null}.
     *
     * @param pKey   - It represents a {@code Key} object.
     * @param pValue - It represents a {@code Value} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value insert(Key pKey, Value pValue);

    /**
     * This method is used to remove specified {@code Key} and its corresponding {@code Value} from this dictionary.
     * <p>
     * If this dictionary contains an entry for the specified {@code Key}, its corresponding {@code Value} object is
     * returned; otherwise {@code null} is returned.
     *
     * @param pKey - It represents a {@code Key} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value remove(Key pKey);

    /**
     * This method is used to search a {@code Key} entry in this dictionary.
     * <p>
     * If this dictionary has an entry for the specified {@code Key}, its corresponding {@code Value} is returned;
     * otherwise {@code null} is returned.
     *
     * @param pKey - It Represents a {@code Key} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value search(Key pKey);

    /**
     * This method is used to junit if this dictionary has any {@code Key}-{@code Value} pair.
     * <p>
     * If this dictionary has no entry, {@code true} is returned; otherwise {@code false} is returned.
     *
     * @return A {@code boolean} value.
     */
    boolean isEmpty();

    /**
     * This method is used to removes all {@code Key}-{@code Value} pairs from this dictionary.
     */
    void clear();

    /**
     * This method returns the number of entries (distinct keys) stored in this dictionary.
     *
     * @return A {@code long} value.
     */
    long size();

    /**
     * This method returns {@code Value} object corresponding to highest {@code Key}. If dictionary is empty
     * {@code null} is returned.
     *
     * @return A {@code Value} object or {@code null}.
     */
    Value getMax();

    /**
     * This method returns {@code Value} object corresponding to least {@code Key}. If dictionary is empty
     * {@code null} is returned.
     *
     * @return A {@code Value} object or {@code null}.
     */
    Value getMin();
}