package test.dictionary.tree;

import dictionary.tree.BinarySearchTree;
import dictionary.tree.node.BinarySearchTreeNode;
import dictionary.tree.utility.BinaryTreeVisitJob;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JUnitBinarySearchTree {

    /**
     * NODE TESTER
     */
    void nodeTester(BinarySearchTreeNode<Integer, Integer> aNode, Comparable akey, Comparable aParentKey, Comparable aLeftSonKey, Comparable aRightSonKey) {

        assertEquals(aNode.getKey(), akey);
        if (aParentKey != null)
            assertEquals(aNode.getParent().getKey(), aParentKey);
        if (aRightSonKey != null)
            assertEquals(aNode.getRightSon().getKey(), aRightSonKey);
        if (aLeftSonKey != null)
            assertEquals(aNode.getLeftSon().getKey(), aLeftSonKey);
    }

    /**
     * INSERT TEST
     */
    @Test
    public void insertTest() {

        // Creating tree...
        // =================================================================== //
        BinarySearchTree<Integer, Integer> myTree = new BinarySearchTree<>();

        // Inserting some nodes...
        // =================================================================== //
        myTree.insert(15, 12);
        myTree.insert(6, 33);
        myTree.insert(30, 45);
        myTree.insert(58, 66);
        myTree.insert(5, 90);
        myTree.insert(10, 33);
        myTree.insert(3, 45);
        myTree.insert(9, 66);
        myTree.insert(11, 90);
        myTree.insert(7, 33);

        // Configuration visit for testing...
        // =================================================================== //
        BinaryTreeVisitJob<Integer, Integer> myVisitTest = new BinaryTreeVisitJob<>() {

            private int nodeIndex = 0;

            @Override
            public void visit(BinarySearchTreeNode<Integer, Integer> aNode) {

                switch (nodeIndex) {
                    case 0:
                        nodeTester(aNode, 15,null, 6, 30);
                        break;
                    case 1:
                        nodeTester(aNode, 6,15, 5, 10);
                        break;
                    case 2:
                        nodeTester(aNode, 30,15, null, 58);
                        break;
                    case 3:
                        nodeTester(aNode, 5,6, 3, null);
                        break;
                    case 4:
                        nodeTester(aNode, 10,6, 9, 11);
                        break;
                    case 5:
                        nodeTester(aNode, 58,30, null, null);
                        break;
                    case 6:
                        nodeTester(aNode, 3,5, null, null);
                        break;
                    case 7:
                        nodeTester(aNode, 9,10, 7, null);
                        break;
                    case 8:
                        nodeTester(aNode, 11,10, null, null);
                        break;
                    case 9:
                        nodeTester(aNode, 7,9, null, null);
                        break;
                }
                this.nodeIndex++;
            }
        };

        // Visiting...
        // =================================================================== //
        myTree.BFSVisit(myVisitTest);
    }
}