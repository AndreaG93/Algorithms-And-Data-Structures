package test.datastructures.tree;

import datastructures.tree.BTree;
import datastructures.tree.node.BTreeNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitBTree {





    /**
     * INSERT TEST
     */
    @Test
    public void insertTest() {

        // Creating tree...
        // =================================================================== //
        BTree<Integer, Integer> myTree = new BTree<>(2);

        // Inserting key-value pairs...
        // =================================================================== //
        myTree.insert(1, 45);
        myTree.insert(2, 78);
        myTree.insert(3, 33);
        myTree.insert(4, 11);
        myTree.insert(5, 28);
        myTree.insert(6, 99);

        // Testing...
        // =================================================================== //
        BTreeNode<Integer, Integer> node1 = myTree.searchNode(1);
        BTreeNode<Integer, Integer> node2 = myTree.searchNode(2);
        BTreeNode<Integer, Integer> node3 = myTree.searchNode(3);
        BTreeNode<Integer, Integer> node4 = myTree.searchNode(4);
        BTreeNode<Integer, Integer> node5 = myTree.searchNode(5);
        BTreeNode<Integer, Integer> node6 = myTree.searchNode(6);

        // Node 1...
        assertEquals(node1.getKeys().size(), 1);
        assertEquals(node1.getKeys().get(0), (Integer) 1);

        // Node 2...
        assertEquals(node2.getKeys().size(), 2);
        assertEquals(node2.getKeys().get(0), (Integer) 2);
        assertEquals(node2.getKeys().get(1), (Integer) 4);

        // Node 3...
        assertEquals(node3.getKeys().size(), 1);
        assertEquals(node3.getKeys().get(0), (Integer) 3);

        // Node 5...
        assertEquals(node5.getKeys().size(), 2);
        assertEquals(node5.getKeys().get(0), (Integer) 5);
        assertEquals(node5.getKeys().get(1), (Integer) 6);

        System.out.println(node1.getValues().get(0));
        System.out.println(node2.getValues().get(0));
        System.out.println(node2.getValues().get(1));
        System.out.println(node3.getValues().get(0));
        System.out.println(node5.getValues().get(0));
        System.out.println(node5.getValues().get(1));


        assertEquals(myTree.search(1), (Integer) 45);
        assertEquals(myTree.search(2), (Integer) 78);
        assertEquals(myTree.search(3), (Integer) 33);
        assertEquals(myTree.search(4), (Integer) 11);
        assertEquals(myTree.search(5), (Integer) 28);
        assertEquals(myTree.search(6), (Integer) 99);


    }


}
