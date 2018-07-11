package dictionary.tree;

import dictionary.tree.node.BinarySearchTreeNode;

/**
 * A Java-Implementation of a splay tree.
 *
 * @param <Key> - Represents a {@code Comparable<Key>} object.
 * @param <Value> - Represents a {@code Value} object.
 * @author Andrea Graziani
 * @version 1.0
 */
public class SplayTree<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    // =================================================================== //
    // 'Override' methods...
    // =================================================================== //

    @Override
    Value insertNode(BinarySearchTreeNode<Key, Value> aNode) {
        Value myOutput;

        if ((myOutput = super.insertNode(aNode)) == null)
            this.splay(aNode);
        return myOutput;
    }

    @Override
    Value removeNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myParentNode = aNode.getParent();
        Value myOutput;

        if ((myOutput = super.removeNode(aNode)) != null && myParentNode != null)
            this.splay(myParentNode);
        return myOutput;
    }

    @Override
    BinarySearchTreeNode<Key, Value> searchNode(Key aKey) {
        BinarySearchTreeNode<Key, Value> myOutput;

        if ((myOutput = super.searchNode(aKey)) != null)
            this.splay(myOutput);
        return super.searchNode(aKey);
    }

    // =================================================================== //
    // 'Private' methods...
    // =================================================================== //

    /**
     * This function is used to select correct rotation for a specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    private void rotation(BinarySearchTreeNode<Key, Value> aNode) {

        switch (aNode.getParentRelationship()) {
            case isLeftSon:
                this.rightRotation(aNode.getParent());
                break;
            case isRightSon:
                this.leftRotation(aNode.getParent());
                break;
        }
    }

    /**
     * This method is used to perform a 'splay' operation on a specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    private void splay(BinarySearchTreeNode<Key, Value> aNode) {

        while (aNode.getParent() != null) {

            BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();

            // Case 1: current parent node is root: perform 'Zig' step...
            // =================================================================== //
            if (aNode.getParent().getParent() == null) {
                this.rotation(aNode);
            }
            // Case 2: current parent node isn't root...
            // =================================================================== //
            else {

                // Case 2.1: 'Zig-zig' step...
                // =================================================================== //
                if (aNode.getParentRelationship() == myNodeParent.getParentRelationship()) {
                    this.rotation(aNode.getParent());
                    this.rotation(aNode);
                }
                // Case 2.2: 'Zig-zag' step...
                // =================================================================== //
                else {
                    this.rotation(aNode);
                    this.rotation(aNode);
                }
            }
        }
    }
}