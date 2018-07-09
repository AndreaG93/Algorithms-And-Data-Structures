package junit.test.tree;

import dictionary.tree.binarytree.BinarySearchTreeAVL;
import dictionary.tree.binarytree.node.BinarySearchTreeNodeAVL;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * TEST CLASS
 *
 * @author Andrea Graziani
 * @version 0.5
 */
public class JUnitBinarySearchTreeAVL extends JUnitBinarySearchTree {

    /**
     * GENERIC TEST
     */
    @Test
    public void genericTest(){
        genericTest1(new BinarySearchTreeAVL<>());
    }

    /**
     * INSERT TEST
     */
    @Test
    public void insertTest() {

        // Creating tree...
        // =================================================================== //
        BinarySearchTreeAVL<Integer, Integer> myTree = new BinarySearchTreeAVL<>();

        // Creating some nodes...
        // =================================================================== //
        BinarySearchTreeNodeAVL<Integer, Integer> node1 = new BinarySearchTreeNodeAVL<>(15, 12);
        BinarySearchTreeNodeAVL<Integer, Integer> node2 = new BinarySearchTreeNodeAVL<>(6, 33);
        BinarySearchTreeNodeAVL<Integer, Integer> node3 = new BinarySearchTreeNodeAVL<>(30, 45);
        BinarySearchTreeNodeAVL<Integer, Integer> node4 = new BinarySearchTreeNodeAVL<>(58, 66);
        BinarySearchTreeNodeAVL<Integer, Integer> node5 = new BinarySearchTreeNodeAVL<>(5, 90);
        BinarySearchTreeNodeAVL<Integer, Integer> node6 = new BinarySearchTreeNodeAVL<>(10, 33);
        BinarySearchTreeNodeAVL<Integer, Integer> node7 = new BinarySearchTreeNodeAVL<>(3, 45);
        BinarySearchTreeNodeAVL<Integer, Integer> node8 = new BinarySearchTreeNodeAVL<>(9, 66);
        BinarySearchTreeNodeAVL<Integer, Integer> node9 = new BinarySearchTreeNodeAVL<>(11, 90);
        BinarySearchTreeNodeAVL<Integer, Integer> node10 = new BinarySearchTreeNodeAVL<>(7, 33);

        // Inserting...
        // =================================================================== //
        myTree.insertNode(node1);
        myTree.insertNode(node2);
        myTree.insertNode(node3);
        myTree.insertNode(node4);
        myTree.insertNode(node5);
        myTree.insertNode(node6);
        myTree.insertNode(node7);
        myTree.insertNode(node8);
        myTree.insertNode(node9);
        myTree.insertNode(node10);

        // Testing...
        // =================================================================== //
        nodeTester(myTree.searchNode(10), null, 15, 6, 0, null);
        nodeTester(myTree.searchNode(6), 10, 9, 5, 0, null);
        nodeTester(myTree.searchNode(15), 10, 30, 11, -1, null);
        nodeTester(myTree.searchNode(5), 6, null, 3, 1, null);
        nodeTester(myTree.searchNode(9), 6, null, 7, 1, null);
        nodeTester(myTree.searchNode(11), 15, null, null, 0, null);
        nodeTester(myTree.searchNode(30), 15, 58, null, -1, null);
        nodeTester(myTree.searchNode(3), 5, null, null, 0, null);
        nodeTester(myTree.searchNode(7), 9, null, null, 0, null);
        nodeTester(myTree.searchNode(58), 30, null, null, 0, null);
    }

    /**
     * REMOVE TEST
     */
    @Test
    public void removeTest() {

        // Creating tree...
        // =================================================================== //
        BinarySearchTreeAVL<Integer, Integer> myTree = new BinarySearchTreeAVL<>();

        // Creating some nodes...
        // =================================================================== //
        BinarySearchTreeNodeAVL<Integer, Integer> node1 = new BinarySearchTreeNodeAVL<>(5, 12);
        BinarySearchTreeNodeAVL<Integer, Integer> node2 = new BinarySearchTreeNodeAVL<>(3, 33);
        BinarySearchTreeNodeAVL<Integer, Integer> node3 = new BinarySearchTreeNodeAVL<>(9, 45);
        BinarySearchTreeNodeAVL<Integer, Integer> node4 = new BinarySearchTreeNodeAVL<>(11, 66);
        BinarySearchTreeNodeAVL<Integer, Integer> node5 = new BinarySearchTreeNodeAVL<>(14, 90);

        // Inserting...
        // =================================================================== //
        myTree.insertNode(node1);
        myTree.insertNode(node2);
        myTree.insertNode(node3);
        myTree.insertNode(node4);
        myTree.insertNode(node5);

        // Testing...
        // =================================================================== //
        nodeTester(node1, null, 11, 3, -1, null);
        nodeTester(node2, 5, null, null, 0, null);
        nodeTester(node3, 11, null, null, 0, null);
        nodeTester(node4, 5, 14, 9, 0, null);
        nodeTester(node5, 11, null, null, 0, null);

        // Removing...
        // =================================================================== //
        assertEquals(myTree.removeNode(node2), (Integer) 33);

        // Testing...
        // =================================================================== //
        nodeTester(node4, null, 14, 5, 1, null);
        nodeTester(node1, 11, 9, null, -1, null);
        nodeTester(node5, 11, null, null, 0, null);
        nodeTester(node3, 5, null, null, 0, null);
    }
}