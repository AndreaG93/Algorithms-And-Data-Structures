package test.sorting;

import org.junit.Test;
import sorting.SortingAlgorithm;
import sorting.algorithms._SortingAlgorithmFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JUnitSortingTest {


    private void sortingTest(SortingAlgorithm pSortingAlgorithm){

        Integer[] myIntegerArray = {5, 3, 27, 8, 49, 13, 0, 333, 21, 1993, 12};
        String[] myStringArray = {"Kumiko", "Akko", "Nano", "Haruna", "Diana"};

        // Sorting...
        pSortingAlgorithm.sort(myIntegerArray);
        pSortingAlgorithm.sort(myStringArray);

        // Checking (myIntegerArray)...
        assertEquals(myIntegerArray[0], (Integer) 0);
        assertEquals(myIntegerArray[1], (Integer) 3);
        assertEquals(myIntegerArray[2], (Integer) 5);
        assertEquals(myIntegerArray[3], (Integer) 8);
        assertEquals(myIntegerArray[4], (Integer) 12);
        assertEquals(myIntegerArray[5], (Integer) 13);
        assertEquals(myIntegerArray[6], (Integer) 21);
        assertEquals(myIntegerArray[7], (Integer) 27);
        assertEquals(myIntegerArray[8], (Integer) 49);
        assertEquals(myIntegerArray[9], (Integer) 333);
        assertEquals(myIntegerArray[10], (Integer) 1993);

        // Checking (myStringArray)...
        assertEquals(myStringArray[0], "Akko");
        assertEquals(myStringArray[1], "Diana");
        assertEquals(myStringArray[2], "Haruna");
        assertEquals(myStringArray[3], "Kumiko");
        assertEquals(myStringArray[4], "Nano");
    }








    @Test
    public void test(){

        SortingAlgorithm myMergeSortAlgorithm = _SortingAlgorithmFactory.getSortingAlgorithm(_SortingAlgorithmFactory.MERGE_SORT);
        SortingAlgorithm myHeapSortAlgorithm = _SortingAlgorithmFactory.getSortingAlgorithm(_SortingAlgorithmFactory.HEAP_SORT);

        assertNotNull(myMergeSortAlgorithm);
        assertNotNull(myHeapSortAlgorithm);


        sortingTest(myMergeSortAlgorithm);
        sortingTest(myHeapSortAlgorithm);
    }
}
