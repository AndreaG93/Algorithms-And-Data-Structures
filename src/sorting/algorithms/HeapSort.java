package sorting.algorithms;

import sorting.SortingAlgorithm;

/**
 * This class contains a A Java-Implementation of "HeapSort" algorithm.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
@SuppressWarnings("unused")
class HeapSort<Key extends Comparable<Key>> implements SortingAlgorithm<Key> {

    /**
     * Constructs a newly allocated {@code HeapSort} object.
     */
    @SuppressWarnings("unused") // Safe, it is call by "Reflection"...
    public HeapSort() {
    }

    @Override
    public void sort(Key[] pArray) {
        int myEndIndex = pArray.length - 1;

        // Creating Heap...
        // =================================================================== //
        buildHeap(pArray, 0, myEndIndex);

        // Heap has been created: starting sorting...
        // =================================================================== //
        while (myEndIndex >= 0) {
            swap(pArray, 0, myEndIndex);
            myEndIndex--;
            fixHeap(pArray, 0, myEndIndex);
        }
    }

    /**
     * This function is used to build an heap from a specified {@code Key} object array.
     *
     * @param anArray         - Represents a {@code Key} object array.
     * @param aStartNodeIndex - Represents an {@code int} value.
     * @param aEndNodeIndex   - Represents an {@code int} value.
     */
    private void buildHeap(Key[] anArray, int aStartNodeIndex, int aEndNodeIndex) {

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
     * @param anArray       - Represents a {@code Key} object array.
     * @param aNodeIndex    - Represents an {@code int} value.
     * @param aEndNodeIndex - Represents an {@code int} value.
     */
    private void fixHeap(Key[] anArray, int aNodeIndex, int aEndNodeIndex) {

        // Case 1: If current heap-node isn't a leaf...
        // =================================================================== //
        if (2 * aNodeIndex + 1 <= aEndNodeIndex) {

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
     * @param anArray     - Represents a {@code Key} object array.
     * @param aNodeIndex1 - Represents an {@code int} value.
     * @param aNodeIndex2 - Represents an {@code int} value.
     */
    private void swap(Key[] anArray, int aNodeIndex1, int aNodeIndex2) {

        Key parent = anArray[aNodeIndex1];
        anArray[aNodeIndex1] = anArray[aNodeIndex2];
        anArray[aNodeIndex2] = parent;
    }
}