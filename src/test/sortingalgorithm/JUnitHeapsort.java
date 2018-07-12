package test.sortingalgorithm;

import org.junit.Test;
import sortingalgorithm.Heapsort;

import static org.junit.Assert.assertEquals;

public class JUnitHeapsort {



    @Test
    public void test(){

        Integer[] myIntegerArray = {5, 3, 27, 8, 49, 13, 0, 333, 21, 1993, 12};
        Heapsort.sort(myIntegerArray);

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

        String[] myStringArray = {"Kumiko", "Akko", "Nano", "Haruna", "Diana"};
        Heapsort.sort(myStringArray);

        assertEquals(myStringArray[0], "Akko");
        assertEquals(myStringArray[1], "Diana");
        assertEquals(myStringArray[2], "Haruna");
        assertEquals(myStringArray[3], "Kumiko");
        assertEquals(myStringArray[4], "Nano");
    }
}
