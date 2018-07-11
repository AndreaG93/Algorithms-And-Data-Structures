package test.dictionary.tree;

import dictionary.tree.RBTree;
import dictionary.tree.node.BinarySearchTreeNode;
import dictionary.tree.node.RBTreeNode;
import dictionary.tree.node.utility.NodeColor;
import dictionary.tree.utility.BinaryTreeVisitJob;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JUnitRBTree extends JUnitBinarySearchTree {

    /**
     * INSERT TEST
     */
    @Test
    public void insertTest() {

        // Creating tree...
        // =================================================================== //
        RBTree<Integer, Integer> myTree = new RBTree<>();

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

                RBTreeNode<Integer, Integer> myNode = (RBTreeNode<Integer, Integer>) aNode;

                switch (nodeIndex) {
                    case 0:
                        nodeTester(aNode, 10,null, 6, 15);
                        assertEquals(myNode.getNodeColor(), NodeColor.Black);
                        break;
                    case 1:
                        nodeTester(myNode, 6,10, 5, 9);
                        assertEquals(myNode.getNodeColor(), NodeColor.Red);
                        break;
                    case 2:
                        nodeTester(myNode, 15,10, 11, 30);
                        assertEquals(myNode.getNodeColor(),  NodeColor.Red);
                        break;
                    case 3:
                        nodeTester(myNode, 5,6, 3, null);
                        assertEquals(myNode.getNodeColor(),  NodeColor.Black);
                        break;
                    case 4:
                        nodeTester(myNode, 9,6, 7, null);
                        assertEquals(myNode.getNodeColor(), NodeColor.Black);
                        break;
                    case 5:
                        nodeTester(myNode, 11,15, null, null);
                        assertEquals(myNode.getNodeColor(), NodeColor.Black);
                        break;
                    case 6:
                        nodeTester(myNode, 30,15, null, 58);
                        assertEquals(myNode.getNodeColor(), NodeColor.Black);
                        break;
                    case 7:
                        nodeTester(myNode, 3,5, null, null);
                        assertEquals(myNode.getNodeColor(), NodeColor.Red);
                        break;
                    case 8:
                        nodeTester(myNode, 7,9, null, null);
                        assertEquals(myNode.getNodeColor(), NodeColor.Red);
                        break;
                    case 9:
                        nodeTester(myNode, 58,30, null, null);
                        assertEquals(myNode.getNodeColor(), NodeColor.Red);
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
