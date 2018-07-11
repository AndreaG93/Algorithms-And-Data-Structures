package dictionary.tree;

import dictionary.tree.node.AVLTreeNode;
import dictionary.tree.node.BinarySearchTreeNode;

/**
 * A Java-Implementation of an AVL tree.
 *
 * @param <Key> - Represents a {@code Comparable<Key>} object.
 * @param <Value> - Represents a {@code Value} object.
 * @author Andrea Graziani
 * @version 1.0
 */
public class AVLTree<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    // =================================================================== //
    // 'Override'/'Public' methods...
    // =================================================================== //

    @Override
    public Value insert(Key aKey, Value aValue) {
        return this.insertNode(new AVLTreeNode<>(aKey, aValue));
    }

    @Override
    public Value remove(Key aKey) {
        AVLTreeNode<Key, Value> myNode = (AVLTreeNode<Key, Value>) this.searchNode(aKey);
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
        else {
            this.updateNodeSubtreesHeightAlongTree((AVLTreeNode<Key, Value>) aNode);
            return null;
        }
    }

    @Override
    Value removeNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();

        // Case 1: Current node is a leaf...
        // =================================================================== //
        if (aNode.hasNoSon()) {

            // Case 1.1: Current node isn't tree root...
            // =================================================================== //
            if (myNodeParent != null) {
                switch (aNode.getParentRelationship()) {
                    case isLeftSon:
                        myNodeParent.setLeftSon(null);
                        break;
                    case isRightSon:
                        myNodeParent.setRightSon(null);
                        break;
                }
            }
            // Case 1.2: Current node isn't tree root...
            // =================================================================== //
            else
                this.root = null;

            // Update subtree heights...
            this.updateNodeSubtreesHeightAlongTree((AVLTreeNode<Key, Value>) myNodeParent);
        }
        // Case 2: Current node has one son...
        // =================================================================== //
        else if (aNode.hasOneSon()) {

            // Getting child...
            BinarySearchTreeNode<Key, Value> myNodeSon = (aNode.hasLeftSon()) ? aNode.getLeftSon() : aNode.getRightSon();

            // Case 2.1: Current node isn't tree root...
            // =================================================================== //
            if (myNodeParent != null) {
                switch (aNode.getParentRelationship()) {
                    case isLeftSon:
                        myNodeParent.setLeftSon(myNodeSon);
                        break;
                    case isRightSon:
                        myNodeParent.setRightSon(myNodeSon);
                        break;
                }
            }
            // Case 2.2: Current node isn't tree root...
            // =================================================================== //
            else {
                this.root = myNodeSon;
                myNodeSon.setParent(null);
            }

            // Update subtree heights...
            this.updateNodeSubtreesHeightAlongTree((AVLTreeNode<Key, Value>) myNodeParent);
        }
        // Case 3: Current node has two sons...
        // =================================================================== //
        else {
            BinarySearchTreeNode<Key, Value> predecessor = this.getPredecessorNode(aNode);

            aNode.switchWith(predecessor);
            removeNode(predecessor);
            this.updateNodeSubtreesHeightAlongTree((AVLTreeNode<Key, Value>) aNode);
            return predecessor.getValue();
        }

        this.size--;
        return aNode.getValue();
    }

    // =================================================================== //
    // 'Private' methods...
    // =================================================================== //

    /**
     * This function is used to select correct rotation for a specified unbalanced {@code AVLTreeNode} object.
     *
     * @param aNode - Represents an {@code AVLTreeNode} object.
     */
    private void rotation(AVLTreeNode<Key, Value> aNode) {

        long myBalanceFactor = aNode.getBalanceFactor();

        // Case 1: LEFT subtree is higher by 2 than RIGHT subtree
        // =================================================================== //
        if (myBalanceFactor == 2) {

            // Case 1.1: LL imbalance...
            // =================================================================== //
            if (((AVLTreeNode) aNode.getLeftSon()).getBalanceFactor() >= 0) {
                this.rightRotation(aNode);
                this.updateNodeSubtreesHeightAlongTree(aNode);
            }
            // Case 1.2: LR imbalance...
            // =================================================================== //
            else {
                AVLTreeNode<Key, Value> x = (AVLTreeNode<Key, Value>) aNode.getLeftSon();
                leftRotation(x);
                rightRotation(aNode);

                aNode.updateSubtreesHeight();
                this.updateNodeSubtreesHeightAlongTree(x);
            }
        }
        // Case 2: RIGHT subtree is higher by 2 than LEFT subtree
        // =================================================================== //
        else {

            // Case 2.1: RR imbalance...
            // =================================================================== //
            if (((AVLTreeNode) aNode.getRightSon()).getBalanceFactor() <= 0) {
                this.leftRotation(aNode);
                this.updateNodeSubtreesHeightAlongTree(aNode);
            }
            // Case 2.2: imbalance...
            // =================================================================== //
            else {
                AVLTreeNode<Key, Value> x = (AVLTreeNode<Key, Value>) aNode.getRightSon();
                rightRotation(x);
                leftRotation(aNode);

                aNode.updateSubtreesHeight();
                this.updateNodeSubtreesHeightAlongTree(x);
            }
        }
    }

    /**
     * This function is used to update subtrees height from a specified node up to root tree.
     *
     * @param aNode - It represents an {@code AVLTreeNode} object
     */
    private void updateNodeSubtreesHeightAlongTree(AVLTreeNode<Key, Value> aNode) {

        AVLTreeNode<Key, Value> currentNode = aNode;

        while (currentNode != null) {

            // Update subtree height...
            currentNode.updateSubtreesHeight();

            // Check for unbalanced...
            if (Math.abs(currentNode.getBalanceFactor()) > 1) {
                this.rotation(currentNode);
                return;
            } else
                currentNode = (AVLTreeNode<Key, Value>) currentNode.getParent();

        }
    }
}