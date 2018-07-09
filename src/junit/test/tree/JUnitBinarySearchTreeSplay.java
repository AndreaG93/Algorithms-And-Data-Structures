package junit.test.tree;

import dictionary.tree.binarytree.BinarySearchTreeSplay;
import dictionary.tree.binarytree.node.BinarySearchTreeNode;
import org.junit.Test;

/**
 * TEST CLASS
 *
 * @author Andrea Graziani
 * @version 0.5
 */
public class JUnitBinarySearchTreeSplay extends JUnitBinarySearchTree {

    /**
     * GENERIC TEST
     */
    @Test
    public void genericTest(){
        genericTest1(new BinarySearchTreeSplay<>());
    }

    /**
     * INSERT TEST
     */
    @Test
    public void insertTest() {

        // Creating tree...
        // =================================================================== //
        BinarySearchTreeSplay<Integer, Integer> myTree = new BinarySearchTreeSplay<>();

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
        nodeTester(node10, null, 11, 6, 0, null);
        nodeTester(node2, 7, null, 5, 0, null);
        nodeTester(node1, 11, 30, null, 0, null);
        nodeTester(node5, 6, null, 3, 0, null);
        nodeTester(node8, 11, 10, null, 0, null);
        nodeTester(node9, 7, 15, 9, 0, null);
        nodeTester(node3, 15, 58, null, 0, null);
        nodeTester(node7, 5, null, null, 0, null);
        nodeTester(node6, 9, null, null, 0, null);
        nodeTester(node4, 30, null, null, 0, null);
    }
}
