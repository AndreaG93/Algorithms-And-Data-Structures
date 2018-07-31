package sorting;

/**
 * This class represents a sorting algorithm.
 *
 * @author Andrea Graziani
 * @version 1.2
 */
public interface SortingAlgorithm<Key extends Comparable<Key>> {

    /**
     * This method is used to sort a specified {@code Comparable} objects array.
     *
     * @param pArray - Represents a {@code Comparable} objects array.
     */
    void sort(Key[] pArray);
}