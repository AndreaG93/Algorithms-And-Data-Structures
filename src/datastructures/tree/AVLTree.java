package datastructures.tree;

import datastructures.tree.node.AVLTreeNode;
import datastructures.tree.node.BinarySearchTreeNode;

/**
 * This class represents a Java-Implementation of an AVL tree.
 *
 * @param <Key>   - It represents an object its class extends {@code Comparable} class.
 * @param <Value> - It represents a generic object.
 * @author Andrea Graziani
 * @version 1.3
 */
@SuppressWarnings("unused")
class AVLTree<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    @Override
    public Value insert(Key pKey, Value pValue) {
        return this.insertNode(new AVLTreeNode<>(pKey, pValue));
    }

    @Override
    public Value remove(Key pKey) {
        AVLTreeNode<Key, Value> mNode = (AVLTreeNode<Key, Value>) this.searchNode(pKey);
        return (mNode == null) ? null : removeNode(mNode);
    }

    @Override
    Value insertNode(BinarySearchTreeNode<Key, Value> pNode) {

        Value mOutputValue = super.insertNode(pNode);

        if (mOutputValue != null)
            return mOutputValue;
        else {
            this.updateNodeSubtreesHeightAlongTree((AVLTreeNode<Key, Value>) pNode);
            return null;
        }
    }

    @Override
    Value removeNode(BinarySearchTreeNode<Key, Value> pNode) {

        BinarySearchTreeNode<Key, Value> mNodeParent = pNode.getParent();

        // Case 1: Current node is a leaf...
        // =================================================================== //
        if (pNode.hasNoSon()) {

            // Case 1.1: Current node isn't tree root...
            // =================================================================== //
            if (mNodeParent != null) {
                switch (pNode.getParentRelationship()) {
                    case isLeftSon:
                        mNodeParent.setLeftSon(null);
                        break;
                    case isRightSon:
                        mNodeParent.setRightSon(null);
                        break;
                }
            }
            // Case 1.2: Current node isn't tree root...
            // =================================================================== //
            else
                this.root = null;

            // Update subtree heights...
            this.updateNodeSubtreesHeightAlongTree((AVLTreeNode<Key, Value>) mNodeParent);
        }
        // Case 2: Current node has one son...
        // =================================================================== //
        else if (pNode.hasOneSon()) {

            // Getting child...
            BinarySearchTreeNode<Key, Value> myNodeSon = (pNode.hasLeftSon()) ? pNode.getLeftSon() : pNode.getRightSon();

            // Case 2.1: Current node isn't tree root...
            // =================================================================== //
            if (mNodeParent != null) {
                switch (pNode.getParentRelationship()) {
                    case isLeftSon:
                        mNodeParent.setLeftSon(myNodeSon);
                        break;
                    case isRightSon:
                        mNodeParent.setRightSon(myNodeSon);
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
            this.updateNodeSubtreesHeightAlongTree((AVLTreeNode<Key, Value>) mNodeParent);
        }
        // Case 3: Current node has two sons...
        // =================================================================== //
        else {
            BinarySearchTreeNode<Key, Value> predecessor = this.getPredecessorNode(pNode);

            pNode.switchWith(predecessor);
            removeNode(predecessor);
            this.updateNodeSubtreesHeightAlongTree((AVLTreeNode<Key, Value>) pNode);
            return predecessor.getValue();
        }

        this.size--;
        return pNode.getValue();
    }

    /**
     * This method is used to select correct rotation for a specified unbalanced {@code AVLTreeNode} object.
     *
     * @param pNode - Represents an {@code AVLTreeNode} object.
     */
    private void rotation(AVLTreeNode<Key, Value> pNode) {

        long mBalanceFactor = pNode.getBalanceFactor();

        // Case 1: LEFT subtree is higher by 2 than RIGHT subtree
        // =================================================================== //
        if (mBalanceFactor == 2) {

            // Case 1.1: LL imbalance...
            // =================================================================== //
            if (((AVLTreeNode) pNode.getLeftSon()).getBalanceFactor() >= 0) {
                this.rightRotation(pNode);
                this.updateNodeSubtreesHeightAlongTree(pNode);
            }
            // Case 1.2: LR imbalance...
            // =================================================================== //
            else {
                AVLTreeNode<Key, Value> x = (AVLTreeNode<Key, Value>) pNode.getLeftSon();
                leftRotation(x);
                rightRotation(pNode);

                pNode.updateSubtreesHeight();
                this.updateNodeSubtreesHeightAlongTree(x);
            }
        }
        // Case 2: RIGHT subtree is higher by 2 than LEFT subtree
        // =================================================================== //
        else {

            // Case 2.1: RR imbalance...
            // =================================================================== //
            if (((AVLTreeNode) pNode.getRightSon()).getBalanceFactor() <= 0) {
                this.leftRotation(pNode);
                this.updateNodeSubtreesHeightAlongTree(pNode);
            }
            // Case 2.2: imbalance...
            // =================================================================== //
            else {
                AVLTreeNode<Key, Value> x = (AVLTreeNode<Key, Value>) pNode.getRightSon();
                rightRotation(x);
                leftRotation(pNode);

                pNode.updateSubtreesHeight();
                this.updateNodeSubtreesHeightAlongTree(x);
            }
        }
    }

    /**
     * This method is used to update subtrees height from a specified node up to root tree.
     *
     * @param pNode - It represents an {@code AVLTreeNode} object
     */
    private void updateNodeSubtreesHeightAlongTree(AVLTreeNode<Key, Value> pNode) {

        AVLTreeNode<Key, Value> mCurrentNode = pNode;

        while (mCurrentNode != null) {

            // Update subtree height...
            mCurrentNode.updateSubtreesHeight();

            // Check for unbalanced...
            if (Math.abs(mCurrentNode.getBalanceFactor()) > 1) {
                this.rotation(mCurrentNode);
                return;
            } else
                mCurrentNode = (AVLTreeNode<Key, Value>) mCurrentNode.getParent();

        }
    }
}