package junit.test.tree;

import dictionary.tree.binarytree.node.BinarySearchTreeNodeAVL;
import dictionary.tree.binarytree.node.BinarySearchTreeNodeRB;
import dictionary.tree.binarytree.node.NodeColor;
import dictionary.tree.binarytree.BinarySearchTree;
import dictionary.tree.binarytree.node.BinarySearchTreeNode;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * TEST CLASS
 *
 * @author Andrea Graziani
 * @version 0.5
 */
public class JUnitBinarySearchTree {

    /**
     * This function represent a generic junit.
     *
     * @param aTree - Represents a {@code BinarySearchTree} object.
     */
    void genericTest1(BinarySearchTree<Integer, Integer> aTree) {

        // "insert()" junit...
        // =================================================================== //


        assertNull(aTree.insert(15, 12));
        assertNull(aTree.insert(6, 33));
        assertNull(aTree.insert(30, 45));
        assertNull(aTree.insert(58, 66));
        assertNull(aTree.insert(5, 90));
        assertNull(aTree.insert(10, 33));
        assertNull(aTree.insert(3, 45));
        assertNull(aTree.insert(9, 66));
        assertNull(aTree.insert(11, 90));
        assertNull(aTree.insert(7, 33));
        assertEquals(aTree.insert(7, 88), (Integer) 33);

        // "isEmpty()" junit...
        // =================================================================== //
        assertFalse(aTree.isEmpty());

        // "size()" junit...
        // =================================================================== //
        assertEquals(aTree.size(), 10);

        // "getMin()" and "getMax()" junit...
        // =================================================================== //
        assertEquals(aTree.getMax(), (Integer) 66);
        assertEquals(aTree.getMin(), (Integer) 45);

        // "search()" junit...
        // =================================================================== //
        assertEquals(aTree.search(11), (Integer) 90);
        assertEquals(aTree.search(58), (Integer) 66);
        assertNull(aTree.search(1000));
    }

    /**
     * This function is used to verify correctness of a node.
     *
     * @param aNode          - Represents a {@code BinarySearchTreeNode} object.
     * @param aParentKey     - Represents a {@code Comparable} object.
     * @param aRightSonKey   - Represents a {@code Comparable} object.
     * @param aLeftSonKey    - Represents a {@code Comparable} object.
     * @param aBalanceFactor - Represents a {@code Comparable} object.
     */
    void nodeTester(BinarySearchTreeNode<Integer, Integer> aNode, Comparable aParentKey, Comparable aRightSonKey, Comparable aLeftSonKey, long aBalanceFactor, NodeColor aColor) {

        if (aParentKey != null)
            assertEquals(aNode.getParent().getKey(), aParentKey);
        if (aRightSonKey != null)
            assertEquals(aNode.getRightSon().getKey(), aRightSonKey);
        if (aLeftSonKey != null)
            assertEquals(aNode.getLeftSon().getKey(), aLeftSonKey);
        if (aNode instanceof BinarySearchTreeNodeAVL)
            assertEquals(((BinarySearchTreeNodeAVL<Integer, Integer>) aNode).getBalanceFactor(), aBalanceFactor);
        if (aNode instanceof BinarySearchTreeNodeRB)
            assertEquals(((BinarySearchTreeNodeRB<Integer, Integer>) aNode).getNodeColor(), aColor);
    }

    /**
     * GENERIC TEST
     */
    @Test
    public void genericTest(){
        genericTest1(new BinarySearchTree<>());
    }

    /**
     * INSERT TEST
     */
    @Test
    public void insertTest() {

        // Creating tree...
        // =================================================================== //
        BinarySearchTree<Integer, Integer> myTree = new BinarySearchTree<>();

        // Creating some nodes...
        // =================================================================== //
        BinarySearchTreeNode<Integer, Integer> node1 = new BinarySearchTreeNode<>(15, 12);
        BinarySearchTreeNode<Integer, Integer> node2 = new BinarySearchTreeNode<>(6, 33);
        BinarySearchTreeNode<Integer, Integer> node3 = new BinarySearchTreeNode<>(30, 45);
        BinarySearchTreeNode<Integer, Integer> node4 = new BinarySearchTreeNode<>(58, 66);
        BinarySearchTreeNode<Integer, Integer> node5 = new BinarySearchTreeNode<>(5, 90);
        BinarySearchTreeNode<Integer, Integer> node6 = new BinarySearchTreeNode<>(10, 33);
        BinarySearchTreeNode<Integer, Integer> node7 = new BinarySearchTreeNode<>(3, 45);
        BinarySearchTreeNode<Integer, Integer> node8 = new BinarySearchTreeNode<>(9, 66);
        BinarySearchTreeNode<Integer, Integer> node9 = new BinarySearchTreeNode<>(11, 90);
        BinarySearchTreeNode<Integer, Integer> node10 = new BinarySearchTreeNode<>(7, 33);

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
        nodeTester(myTree.searchNode(15), null, 30, 6, 0, null);
        nodeTester(myTree.searchNode(6), 15, 10, 5, 0, null);
        nodeTester(myTree.searchNode(30), 15, 58, null, 0, null);
        nodeTester(myTree.searchNode(5), 6, null, 3, 0, null);
        nodeTester(myTree.searchNode(10), 6, 11, 9, 0, null);
        nodeTester(myTree.searchNode(58), 30, null, null, 0, null);
        nodeTester(myTree.searchNode(3), 5, null, null, 0, null);
        nodeTester(myTree.searchNode(9), 10, null, 7, 0, null);
        nodeTester(myTree.searchNode(11), 10, null, null, 0, null);
        nodeTester(myTree.searchNode(7), 9, null, null, 0, null);
    }
}
