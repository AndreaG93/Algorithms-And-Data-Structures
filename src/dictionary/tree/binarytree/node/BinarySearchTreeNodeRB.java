package dictionary.tree.binarytree.node;

public class BinarySearchTreeNodeRB<Key extends Comparable<Key>, Value> extends BinarySearchTreeNode<Key, Value> {

    private NodeColor nodeColor;

    public BinarySearchTreeNodeRB(Key aKey, Value aElement) {
        super(aKey, aElement);
        this.nodeColor = NodeColor.Red;
    }

    public NodeColor getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(NodeColor nodeColor) {
        this.nodeColor = nodeColor;
    }
}
