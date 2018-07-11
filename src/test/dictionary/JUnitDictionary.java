package test.dictionary;

import dictionary.Dictionary;
import dictionary.tree.BinarySearchTree;
import dictionary.tree.AVLTree;
import dictionary.tree.SplayTree;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class is used to junit {@code Dictionary} abstract class.
 * @version 1.0
 * @author Andrea Graziani
 */
public class JUnitDictionary {

    /**
     * This method is used to junit all {@code Dictionary} object implemented methods.
     *
     * @param aDictionary - It represents a {@code Dictionary} object.
     */
    private void dictionaryTestMethod(Dictionary<Integer, Integer> aDictionary) {

        // "insert()" junit...
        // =================================================================== //
        assertNull(aDictionary.insert(34, 102));
        assertNull(aDictionary.insert(67, 109));
        assertNull(aDictionary.insert(89, 187));
        assertNull(aDictionary.insert(12, 106));
        assertEquals(aDictionary.insert(12, 109), (Integer) 106);
        assertEquals(aDictionary.size(), 4);

        // "search()" junit...
        // =================================================================== //
        assertEquals(aDictionary.search(34), (Integer) 102);
        assertEquals(aDictionary.search(67), (Integer) 109);
        assertEquals(aDictionary.search(89), (Integer) 187);
        assertEquals(aDictionary.search(12), (Integer) 109);

        // "remove()" junit...
        // =================================================================== //
        assertEquals(aDictionary.remove(34), (Integer) 102);
        assertNull(aDictionary.remove(34));
        assertNull(aDictionary.search(34));

        // "isEmpty()" junit...
        // =================================================================== //
        assertFalse(aDictionary.isEmpty());

        // "size()" junit...
        // =================================================================== //
        assertEquals(aDictionary.size(), 3);

        // "getMin()" and "getMax()"junit...
        // =================================================================== //
        assertEquals(aDictionary.getMax(), (Integer) 187);
        assertEquals(aDictionary.getMin(), (Integer) 109);

        // "clear()" junit...
        // =================================================================== //
        aDictionary.clear();
        assertTrue(aDictionary.isEmpty());
        assertEquals(aDictionary.size(), 0);
    }

    /**
     * This junit method is used to junit all {@code Dictionary} object implementation.
     */
    @Test
    public void testAllImplementedDictionary(){

        // Tree-Based dictionary...
        // =================================================================== //
        this.dictionaryTestMethod(new BinarySearchTree<>());
        this.dictionaryTestMethod(new AVLTree<>());
        this.dictionaryTestMethod(new SplayTree<>());
    }
}