package dictionary;

/**
 * This class represents a dictionary.
 *
 * @param <Key>   - It Represents type of keys.
 * @param <Value> - It Represents type of values.
 * @author Andrea Graziani
 * @version 1.3
 */
public interface Dictionary<Key extends Comparable<Key>, Value> {

    /**
     * This method is used to insertNode a {@code Key}-{@code Value} pair in this dictionary.
     *
     * If this dictionary already contains an entry for the specified {@code Key}, the {@code Value} already in this
     * dictionary for that {@code Key} is returned, after modifying the entry to contain the new element.
     *
     * If this dictionary does not already have an entry for the specified {@code Key}, an entry is created for the
     * specified {@code Key}-{@code Value} pair, and {@code null} is returned.
     *
     * Specified {@code Key} cannot be {@code null}.
     *
     * @param aKey - It represents a {@code Key} object.
     * @param aValue - It represents a {@code Value} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value insert(Key aKey, Value aValue);

    /**
     * This method is used to remove specified {@code Key} and its corresponding {@code Value} from this dictionary.
     *
     * If this dictionary contains an entry for the specified {@code Key}, its corresponding {@code Value} object is
     * returned; otherwise {@code null} is returned.
     *
     * @param aKey - It represents a {@code Key} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value remove(Key aKey);

    /**
     * This method is used to search a {@code Key} entry in this dictionary.
     *
     * If this dictionary has an entry for the specified {@code Key}, its corresponding {@code Value} is returned;
     * otherwise {@code null} is returned.
     *
     * @param aKey - It Represents a {@code Key} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value search(Key aKey);

    /**
     * This method is used to junit if this dictionary has any {@code Key}-{@code Value} pair.
     *
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