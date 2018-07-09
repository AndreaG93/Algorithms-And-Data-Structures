package junit.test.tree;

import dictionary.tree.binarytree.BinarySearchTreeRB;
import dictionary.tree.binarytree.node.BinarySearchTreeNodeRB;
import dictionary.tree.binarytree.node.NodeColor;
import org.junit.Test;

/**
 * TEST CLASS
 *
 * @author Andrea Graziani
 * @version 0.5
 */
public class JUnitBinarySearchTreeRB extends JUnitBinarySearchTree {

    /**
     * GENERIC TEST
     */
    @Test
    public void genericTest(){
        genericTest1(new BinarySearchTreeRB<>());
    }

    /**
     * INSERT TEST
     */
    @Test
    public void insertTest() {

        // Creating tree...
        // =================================================================== //
        BinarySearchTreeRB<Integer, Integer> myTree = new BinarySearchTreeRB<>();

        // Creating some nodes...
        // =================================================================== //
        BinarySearchTreeNodeRB<Integer, Integer> node1 = new BinarySearchTreeNodeRB<>(15, 12);
        BinarySearchTreeNodeRB<Integer, Integer> node2 = new BinarySearchTreeNodeRB<>(6, 33);
        BinarySearchTreeNodeRB<Integer, Integer> node3 = new BinarySearchTreeNodeRB<>(30, 45);
        BinarySearchTreeNodeRB<Integer, Integer> node4 = new BinarySearchTreeNodeRB<>(58, 66);
        BinarySearchTreeNodeRB<Integer, Integer> node5 = new BinarySearchTreeNodeRB<>(5, 90);
        BinarySearchTreeNodeRB<Integer, Integer> node6 = new BinarySearchTreeNodeRB<>(10, 33);
        BinarySearchTreeNodeRB<Integer, Integer> node7 = new BinarySearchTreeNodeRB<>(3, 45);
        BinarySearchTreeNodeRB<Integer, Integer> node8 = new BinarySearchTreeNodeRB<>(9, 66);
        BinarySearchTreeNodeRB<Integer, Integer> node9 = new BinarySearchTreeNodeRB<>(11, 90);
        BinarySearchTreeNodeRB<Integer, Integer> node10 = new BinarySearchTreeNodeRB<>(7, 33);

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
        nodeTester(myTree.searchNode(10), null, 15, 6, 0, NodeColor.Black);
        nodeTester(myTree.searchNode(6), 10, 9, 5, 0, NodeColor.Red);
        nodeTester(myTree.searchNode(15), 10, 30, 11, 0, NodeColor.Red);
        nodeTester(myTree.searchNode(5), 6, null, 3, 0, NodeColor.Black);
        nodeTester(myTree.searchNode(9), 6, null, 7, 0, NodeColor.Black);
        nodeTester(myTree.searchNode(11), 15, null, null, 0, NodeColor.Black);
        nodeTester(myTree.searchNode(30), 15, 58, null, 0, NodeColor.Black);
        nodeTester(myTree.searchNode(3), 5, null, null, 0, NodeColor.Red);
        nodeTester(myTree.searchNode(7), 9, null, null, 0, NodeColor.Red);
        nodeTester(myTree.searchNode(58), 30, null, null, 0, NodeColor.Red);
    }
}
