package datastructures.tree.node;

import java.util.ArrayList;
import java.util.List;

/**
 * A Java-Implementation of a B-tree node.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class BTreeNode<Key extends Comparable<Key>, Value> {

    private BTreeNode<Key, Value> parent;
    private int childIndex = -1;
    private List<BTreeNode<Key, Value>> children = new ArrayList<>();
    private List<Key> keys = new ArrayList<>();
    private List<Value> values = new ArrayList<>();

    // =================================================================== //
    // 'Getter' and 'Setter' methods...
    // =================================================================== //

    public BTreeNode<Key, Value> getParent() {
        return parent;
    }

    public void setParent(BTreeNode<Key, Value> parent) {
        this.parent = parent;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public void setChildIndex(int childIndex) {
        this.childIndex = childIndex;
    }

    public List<BTreeNode<Key, Value>> getChildren() {
        return children;
    }

    public void setChildren(List<BTreeNode<Key, Value>> children) {
        this.children = children;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}