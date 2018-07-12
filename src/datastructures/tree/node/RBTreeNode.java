package datastructures.tree.node;

import datastructures.tree.node.utility.NodeColor;

/**
 * A Java-Implementation of a RB-tree node.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class RBTreeNode<Key extends Comparable<Key>, Value> extends BinarySearchTreeNode<Key, Value> {

    private NodeColor nodeColor;

    /**
     * Construct a newly allocated {@code BinarySearchTreeNode} object.
     *
     * @param aKey   - It Represents type of keys.
     * @param aValue - It Represents type of values.
     */
    public RBTreeNode(Key aKey, Value aValue) {
        super(aKey, aValue);
        this.nodeColor = NodeColor.Red;
    }

    // =================================================================== //
    // 'Getter' and 'Setter' methods...
    // =================================================================== //

    public NodeColor getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(NodeColor nodeColor) {
        this.nodeColor = nodeColor;
    }
}