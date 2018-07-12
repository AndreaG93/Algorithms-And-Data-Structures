package datastructures.tree.utility;

import datastructures.tree.node.BinarySearchTreeNode;

/**
 * An interface for {@code BinarySearchTreeNode} object visiting.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public interface BinaryTreeVisitJob<Key extends Comparable<Key>, Value> {

    /**
     * This function is used to visit specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    void visit(BinarySearchTreeNode<Key, Value> aNode);
}
