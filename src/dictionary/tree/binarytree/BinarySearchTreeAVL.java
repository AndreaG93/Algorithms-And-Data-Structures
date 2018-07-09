package dictionary.tree.binarytree;

import dictionary.tree.binarytree.node.BinarySearchTreeNode;
import dictionary.tree.binarytree.node.BinarySearchTreeNodeAVL;

/**
 * A Java-Implementation of an AVL binary search tree data structure.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class BinarySearchTreeAVL<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    // =================================================================== //
    // Override methods...
    // =================================================================== //

    @Override
    public Value insert(Key aKey, Value aValue) {
        return this.insertNode(new BinarySearchTreeNodeAVL<>(aKey, aValue));
    }

    @Override
    public Value insertNode(BinarySearchTreeNode<Key, Value> aNode) {

        Value myOutputValue = super.insertNode(aNode);

        if (myOutputValue != null)
            return myOutputValue;
        else {
            this.updateNodeSubtreesHeightAlongTree((BinarySearchTreeNodeAVL<Key, Value>) aNode);
            return null;
        }
    }

    @Override
    public Value remove(Key aKey) {
        BinarySearchTreeNodeAVL<Key, Value> myNode = (BinarySearchTreeNodeAVL<Key, Value>) this.searchNode(aKey);
        return (myNode == null) ? null : removeNode(myNode);
    }

    @Override
    public Value removeNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();

        // Case 0: Specified node has NO sons...
        // ------------------------------------------------------------------------------- //
        if (aNode.hasNoSon()) {

            // Case 0.0: Specified node is NOT tree root...
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
            // Case 0.1: Specified node IS tree root...
            else
                this.root = null;

            this.size--;
            this.updateNodeSubtreesHeightAlongTree((BinarySearchTreeNodeAVL<Key, Value>) myNodeParent);
        }
        // Case 1: Specified node has ONE sons...
        // ------------------------------------------------------------------------------- //
        else if (aNode.hasOneSon()) {

            // Getting child...
            BinarySearchTreeNode<Key, Value> myNodeSon = (aNode.hasLeftSon()) ? aNode.getLeftSon() : aNode.getRightSon();

            // Case 1.0: Specified node is NOT tree root...
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
            // Case 1.1: Specified node IS tree root...
            else {
                this.root = myNodeSon;
                myNodeSon.setParent(null);
            }

            this.size--;
            this.updateNodeSubtreesHeightAlongTree((BinarySearchTreeNodeAVL<Key, Value>) myNodeParent);
        }
        // Case 2: Specified node has TWO sons...
        // ------------------------------------------------------------------------------- //
        else {
            BinarySearchTreeNode<Key, Value> predecessor = this.getPredecessorNode(aNode);

            aNode.switchWith(predecessor);
            removeNode(predecessor);
            this.updateNodeSubtreesHeightAlongTree((BinarySearchTreeNodeAVL<Key, Value>) aNode);
            return predecessor.getElement();
        }

        // Update count...
        return aNode.getElement();
    }

    // =================================================================== //
    // Private methods...
    // =================================================================== //

    /**
     * This function is used to do a node rotation.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNodeAVL} object.
     */
    private void rotation(BinarySearchTreeNodeAVL<Key, Value> aNode) {

        long myBalanceFactor = aNode.getBalanceFactor();

        // Case 0: LEFT subtree is higher by 2 than RIGHT subtree
        // ------------------------------------------------------------------------------- //
        if (myBalanceFactor == 2) {

            // LL imbalance...
            // ------------------------------------------------------------------------------- //
            if (((BinarySearchTreeNodeAVL) aNode.getLeftSon()).getBalanceFactor() >= 0) {
                this.rightRotation(aNode);
                this.updateNodeSubtreesHeightAlongTree(aNode);
            }
            // LR imbalance...
            // ------------------------------------------------------------------------------- //
            else {
                BinarySearchTreeNodeAVL<Key, Value> x = (BinarySearchTreeNodeAVL<Key, Value>) aNode.getLeftSon();
                leftRotation(x);
                rightRotation(aNode);

                aNode.updateSubtreesHeight();
                this.updateNodeSubtreesHeightAlongTree(x);
            }
        }
        // Case 0: RIGHT subtree is higher by 2 than LEFT subtree
        // ------------------------------------------------------------------------------- //
        else {

            // RR imbalance...
            // ------------------------------------------------------------------------------- //
            if (((BinarySearchTreeNodeAVL) aNode.getRightSon()).getBalanceFactor() <= 0) {
                this.leftRotation(aNode);
                this.updateNodeSubtreesHeightAlongTree(aNode);
            }
            // RL imbalance...
            // ------------------------------------------------------------------------------- //
            else {
                BinarySearchTreeNodeAVL<Key, Value> x = (BinarySearchTreeNodeAVL<Key, Value>) aNode.getRightSon();
                rightRotation(x);
                leftRotation(aNode);

                aNode.updateSubtreesHeight();
                this.updateNodeSubtreesHeightAlongTree(x);
            }
        }
    }

    /**
     * This function is used to update subtrees height from a specified node to root node.
     *
     * @param aNode - It represents a {@code BinarySearchTreeNodeAVL} object
     */
    private void updateNodeSubtreesHeightAlongTree(BinarySearchTreeNodeAVL<Key, Value> aNode) {

        BinarySearchTreeNodeAVL<Key, Value> currentNode = aNode;

        do {
            currentNode.updateSubtreesHeight();
            if (Math.abs(currentNode.getBalanceFactor()) > 1) {
                this.rotation(currentNode);
                return;
            } else
                currentNode = (BinarySearchTreeNodeAVL<Key, Value>) currentNode.getParent();

        } while (currentNode != null);
    }
}