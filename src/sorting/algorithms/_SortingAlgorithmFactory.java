package sorting.algorithms;

import sorting.SortingAlgorithm;

/**
 * This class is used as factory of {@code SortingAlgorithm} objects.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public abstract class _SortingAlgorithmFactory {

    public static String HEAP_SORT = "HeapSort";
    public static String MERGE_SORT = "MergeSort";

    /**
     * This method is used to retrieve wanted {@code SortingAlgorithm} object.
     *
     * If specified {@code String} object contains a name matches an existing and implemented algorithm then a {@code SortingAlgorithm}
     * object is returned; otherwise {@code null} is returned.
     *
     * @param pSortAlgorithmName - It represents a {@code String} object.
     * @return A {@code SortingAlgorithm} object or {@code null}.
     */
    public static SortingAlgorithm getSortingAlgorithm(String pSortAlgorithmName){

        SortingAlgorithm mySortingAlgorithm = null;

        // Concrete sorting algorithm's name processing...
        // =================================================================== //
        String className = String.format("%s.%s", _SortingAlgorithmFactory.class.getPackage().getName(), pSortAlgorithmName);

        // Allocation concrete sorting algorithm...
        // =================================================================== //
        try {
            mySortingAlgorithm = (SortingAlgorithm) Class.forName(className).getConstructor().newInstance();
        } catch (Exception e) {

            // Print data about error and exit. //
            e.printStackTrace();
            return null;
        }
        return mySortingAlgorithm;
    }
}