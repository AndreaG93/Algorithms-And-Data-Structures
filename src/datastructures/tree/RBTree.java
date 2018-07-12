package datastructures.tree;

import datastructures.tree.node.utility.NodeColor;
import datastructures.tree.node.utility.NodeRelationship;
import datastructures.tree.node.BinarySearchTreeNode;
import datastructures.tree.node.RBTreeNode;

/**
 * A Java-Implementation of a RB-tree.
 *
 * @param <Key> - Represents a {@code Comparable<Key>} object.
 * @param <Value> - Represents a {@code Value} object.
 * @author Andrea Graziani
 * @version 1.0
 */
public class RBTree<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    // =================================================================== //
    // 'Override'/'Public' methods...
    // =================================================================== //

    @Override
    public Value insert(Key aKey, Value aValue) {
        return this.insertNode(new RBTreeNode<>(aKey, aValue));
    }

    @Override
    public Value remove(Key aKey) {
        RBTreeNode<Key, Value> myNode = (RBTreeNode<Key, Value>) this.searchNode(aKey);
        return (myNode == null) ? null : removeNode(myNode);
    }

    // =================================================================== //
    // 'Override' methods...
    // =================================================================== //

    @Override
    Value insertNode(BinarySearchTreeNode<Key, Value> aNode) {

        Value myOutputValue = super.insertNode(aNode);

        if (myOutputValue != null)
            return myOutputValue;
        else
            nodeCheck((RBTreeNode<Key, Value>) aNode);

        return null;
    }

    @Override
    Value removeNode(BinarySearchTreeNode<Key, Value> aNode) {

        // TODO
        return getSuccessorNode(aNode).getValue();
    }

    // =================================================================== //
    // 'Private' methods...
    // =================================================================== //

    /**
     * This method is used to check of specified {@code BinarySearchTreeNode} object
     * doesn't alter RB-tree properties.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    private void nodeCheck(RBTreeNode<Key, Value> aNode) {

        while (true) {

            RBTreeNode<Key, Value> myNodeParent = (RBTreeNode<Key, Value>) aNode.getParent();
            RBTreeNode<Key, Value> myNodeGrandParent = (RBTreeNode<Key, Value>) aNode.getGrandparent();
            RBTreeNode<Key, Value> myNodeUncle = (RBTreeNode<Key, Value>) aNode.getUncle();

            // Case 1: Specified node is tree root...
            // =================================================================== //
            if (myNodeParent == null) {
                aNode.setNodeColor(NodeColor.Black);
                return;
            }
            // Case 2: Current parent node is black: current tree is valid...
            // =================================================================== //
            else if (myNodeParent.getNodeColor() == NodeColor.Black) {
                return;
            }
            // Case 3: Parent and uncle of current node are red...
            // =================================================================== //
            else if (myNodeUncle != null && myNodeUncle.getNodeColor() == NodeColor.Red) {

                myNodeParent.setNodeColor(NodeColor.Black);
                myNodeUncle.setNodeColor(NodeColor.Black);
                myNodeGrandParent.setNodeColor(NodeColor.Red);

                aNode = myNodeGrandParent;
            }
            // Case 4.1: Parent is red and uncle is black
            //           -> current parent node is a left son of his parent
            //           -> current node is a right son of his parent
            // =================================================================== //
            else if (aNode.getParentRelationship() == NodeRelationship.isRightSon && myNodeParent.getParentRelationship() == NodeRelationship.isLeftSon) {

                this.leftRotation(myNodeParent);
                aNode = (RBTreeNode<Key, Value>) aNode.getLeftSon();
            }
            // Case 4.2: Parent is red and uncle is black...
            //           -> current parent node is a right son of his parent
            //           -> current node is a left son of his parent
            // =================================================================== //
            else if (aNode.getParentRelationship() == NodeRelationship.isLeftSon && myNodeParent.getParentRelationship() == NodeRelationship.isRightSon) {

                this.rightRotation(myNodeParent);
                aNode = (RBTreeNode<Key, Value>) aNode.getRightSon();
            }
            // Case 5: parent is red but grandparent and uncle are red...
            // =================================================================== //
            else {

                myNodeParent.setNodeColor(NodeColor.Black);
                myNodeGrandParent.setNodeColor(NodeColor.Red);

                if (aNode.getParentRelationship() == NodeRelationship.isLeftSon && myNodeParent.getParentRelationship() == NodeRelationship.isLeftSon)
                    this.rightRotation(myNodeGrandParent);
                else
                    this.leftRotation(myNodeGrandParent);
                return;
            }
        }
    }
}