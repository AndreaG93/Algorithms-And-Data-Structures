package sorting.algorithms;

import sorting.SortingAlgorithm;

/**
 * This class contains a A Java-Implementation of "MergeSort" algorithm.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
@SuppressWarnings("unused")
        // Safe, it is call by "Reflection"...
class MergeSort<Key extends Comparable<Key>> implements SortingAlgorithm<Key> {

    /**
     * Constructs a newly allocated {@code MergeSort} object.
     */
    @SuppressWarnings("unused") // Safe, it is call by "Reflection"...
    public MergeSort() {
    }

    @Override
    public void sort(Key[] pArray) {
        mergeSort(pArray, 0, pArray.length - 1);
    }

    /**
     * This method is used to perform "MergeSort" algorithm.
     *
     * @param pArray      - Represents a {@code Comparable} objects array.
     * @param pStartIndex - It represents an {@code int} value.
     * @param pEndIndex   - It represents an {@code int} value.
     */
    private void mergeSort(Key[] pArray, int pStartIndex, int pEndIndex) {

        if (pStartIndex < pEndIndex) {

            int myAux = (pStartIndex + pEndIndex) / 2;

            mergeSort(pArray, pStartIndex, myAux);
            mergeSort(pArray, myAux + 1, pEndIndex);
            merge(pArray, pStartIndex, myAux, pEndIndex);
        }
    }

    /**
     * This method is used to perform a "Merge" operation.
     *
     * @param pArray  - Represents a {@code Comparable} objects array.
     * @param pAStart - It represents an {@code int} value.
     * @param pAEnd   - It represents an {@code int} value.
     * @param pBEnd   - It represents an {@code int} value.
     */
    private void merge(Key[] pArray, int pAStart, int pAEnd, int pBEnd) {

        // Backup first sequence start index order to copy data from auxiliary array correctly...
        // =================================================================== //
        int startPoint = pAStart;

        // Merge
        // =================================================================== //
        @SuppressWarnings("unchecked") // Safe...
                Key[] myAuxiliaryArray = (Key[]) new Comparable[pBEnd - pAStart + 1];

        int index = 0;
        int myBStart = pAEnd + 1;

        while (pAStart <= pAEnd && myBStart <= pBEnd) {

            if (pArray[pAStart].compareTo(pArray[myBStart]) <= 0) {
                myAuxiliaryArray[index] = pArray[pAStart];
                pAStart++;
            } else {
                myAuxiliaryArray[index] = pArray[myBStart];
                myBStart++;
            }
            index++;
        }

        // Copying remaining data...
        // =================================================================== //
        if (pAStart <= pAEnd)
            System.arraycopy(pArray, pAStart, myAuxiliaryArray, index, pAEnd - pAStart + 1);
        else
            System.arraycopy(pArray, myBStart, myAuxiliaryArray, index, pBEnd - myBStart + 1);

        // Copying data from the auxiliary array...
        // =================================================================== //
        System.arraycopy(myAuxiliaryArray, 0, pArray, startPoint, myAuxiliaryArray.length);
    }
}