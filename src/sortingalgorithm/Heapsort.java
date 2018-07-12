package sortingalgorithm;

/**
 * A Java-Implementation of heapsort algorithm (recursive implementation).
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class Heapsort {

    // =================================================================== //
    // 'Public' methods...
    // =================================================================== //

    /**
     * This function is used to sort a specified unsorted array of {@code Key} object.
     *
     * @param anArray - Represents a {@code Comparable} object array.
     */
    public static <Key extends Comparable<Key>> void sort(Key[] anArray) {

        int myEndIndex = anArray.length - 1;

        // Creating Heap...
        // =================================================================== //
        buildHeap(anArray, 0, myEndIndex);

        // Heap has been created: starting sorting...
        // =================================================================== //
        while(myEndIndex >= 0)
        {
            swap(anArray, 0, myEndIndex);
            myEndIndex--;
            fixHeap(anArray,0, myEndIndex);
        }
    }

    // =================================================================== //
    // 'Private' methods...
    // =================================================================== //

    /**
     * This function is used to build an heap from a specified {@code Key} object array.
     *
     * @param anArray - Represents a {@code Key} object array.
     * @param aStartNodeIndex - Represents an {@code int} value.
     * @param aEndNodeIndex - Represents an {@code int} value.
     */
    private static <Key extends Comparable<Key>> void buildHeap(Key[] anArray, int aStartNodeIndex, int aEndNodeIndex) {

        // Case 1: If current
        if (aStartNodeIndex < anArray.length - 1) {
            buildHeap(anArray, 2 * aStartNodeIndex + 1, aEndNodeIndex);
            buildHeap(anArray, 2 * aStartNodeIndex + 2, aEndNodeIndex);
            fixHeap(anArray, aStartNodeIndex, aEndNodeIndex);
        }
    }

    /**
     * This function is used to fix an heap-node according to heap property.
     *
     * @param anArray - Represents a {@code Key} object array.
     * @param aNodeIndex - Represents an {@code int} value.
     * @param aEndNodeIndex - Represents an {@code int} value.
     */
    private static <Key extends Comparable<Key>> void fixHeap(Key[] anArray, int aNodeIndex, int aEndNodeIndex) {

        // Case 1: If current heap-node isn't a leaf...
        // =================================================================== //
        if (2 * aNodeIndex + 1 <= aEndNodeIndex){

            // Current heap-node has certainly a LEFT son...
            // =================================================================== //
            int maxKeyIndex = 2 * aNodeIndex + 1;

            // Current heap-node has a RIGHT son too...
            // =================================================================== //
            if (2 * aNodeIndex + 2 <= aEndNodeIndex && anArray[2 * aNodeIndex + 2].compareTo(anArray[maxKeyIndex]) > 0) {
                maxKeyIndex = 2 * aNodeIndex + 2;
            }

            // Case 1.1: Children keys are bigger than parent key...
            // =================================================================== //
            if (anArray[aNodeIndex].compareTo(anArray[maxKeyIndex]) < 0) {
                swap(anArray, aNodeIndex, maxKeyIndex);
                fixHeap(anArray, maxKeyIndex, aEndNodeIndex);
            }
        }
    }

    /**
     * This function is used to swap specified heap-nodes.
     *
     * @param anArray - Represents a {@code Key} object array.
     * @param aNodeIndex1 - Represents an {@code int} value.
     * @param aNodeIndex2 - Represents an {@code int} value.
     */
    private static <Key extends Comparable<Key>> void swap(Key[] anArray, int aNodeIndex1, int aNodeIndex2) {

        Key parent = anArray[aNodeIndex1];
        anArray[aNodeIndex1] = anArray[aNodeIndex2];
        anArray[aNodeIndex2] = parent;
    }
}