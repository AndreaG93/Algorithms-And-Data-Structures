package dictionary.tree.node;

import dictionary.tree.node.utility.NodeRelationship;

/**
 * A Java-Implementation of a binary search tree node.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class BinarySearchTreeNode<Key extends Comparable<Key>, Value> {

    private Key key;
    private Value value;
    private BinarySearchTreeNode<Key, Value> parent;
    private BinarySearchTreeNode<Key, Value> leftSon;
    private BinarySearchTreeNode<Key, Value> rightSon;
    private NodeRelationship parentRelationship;

    /**
     * Construct a newly allocated {@code BinarySearchTreeNode} object.
     *
     * @param aKey   - It Represents type of keys.
     * @param aValue - It Represents type of values.
     */
    public BinarySearchTreeNode(Key aKey, Value aValue) {
        this.key = aKey;
        this.value = aValue;
    }

    // =================================================================== //
    // 'Public' methods...
    // =================================================================== //

    /**
     * This function is used to get grandparent node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object of {@code null}.
     */
    public BinarySearchTreeNode<Key, Value> getGrandparent() {
        return (this.parent == null) ? null : this.parent.getParent();
    }

    /**
     * This function is used to get sibling node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object of {@code null}.
     */
    public BinarySearchTreeNode<Key, Value> getSibling() {

        if (this.parent != null)
            switch (this.getParentRelationship()) {
                case isLeftSon:
                    return this.parent.getRightSon();
                case isRightSon:
                    return this.parent.getLeftSon();
            }
        return null;
    }

    /**
     * This function is used to get uncle node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object of {@code null}.
     */
    public BinarySearchTreeNode<Key, Value> getUncle() {

        BinarySearchTreeNode<Key, Value> myNodeGrandparent = this.getGrandparent();

        if (myNodeGrandparent != null)
            switch (this.parent.getParentRelationship()) {
                case isLeftSon:
                    return myNodeGrandparent.getRightSon();
                case isRightSon:
                    return myNodeGrandparent.getLeftSon();
            }

        return null;
    }

    /**
     * This function is used to get parent node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object of {@code null}.
     */
    public BinarySearchTreeNode<Key, Value> getParent() {
        return this.parent;
    }

    /**
     * This function is used to retrieve left son node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object of {@code null}.
     */
    public BinarySearchTreeNode<Key, Value> getLeftSon() {
        return this.leftSon;
    }

    /**
     * This function is used to retrieve right son node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object of {@code null}.
     */
    public BinarySearchTreeNode<Key, Value> getRightSon() {
        return this.rightSon;
    }

    /**
     * This function is used to set left son node of current {@code BinarySearchTreeNode} object.
     *
     * @param aLeftSon - Represents a {@code BinarySearchTreeNode} object.
     */
    public void setLeftSon(BinarySearchTreeNode<Key, Value> aLeftSon) {
        if ((this.leftSon = aLeftSon) != null) {
            this.leftSon.setParentRelationship(NodeRelationship.isLeftSon);
            this.leftSon.setParent(this);
        }
    }

    /**
     * This function is used to set right son node of current {@code BinarySearchTreeNode} object.
     *
     * @param aRightSon - Represents a {@code BinarySearchTreeNode} object.
     */
    public void setRightSon(BinarySearchTreeNode<Key, Value> aRightSon) {
        if ((this.rightSon = aRightSon) != null) {
            this.rightSon.setParentRelationship(NodeRelationship.isRightSon);
            this.rightSon.setParent(this);
        }
    }

    /**
     * This function is used to check if current {@code BinarySearchTreeNode} object has no son.
     * <p>
     * If current {@code BinarySearchTreeNode} object has no son, {@code true} is returned;
     * otherwise {@code false} is returned.
     *
     * @return A {@code boolean} value.
     */
    public boolean hasNoSon() {
        return this.leftSon == null && this.rightSon == null;
    }

    /**
     * This function is used to check if current {@code BinarySearchTreeNode} object has a right son.
     * <p>
     * If current {@code BinarySearchTreeNode} object has a right son, {@code true} is returned;
     * otherwise {@code false} is returned.
     *
     * @return A {@code boolean} value.
     */
    public boolean hasRightSon() {
        return (this.rightSon != null);
    }

    /**
     * This function is used to check if current {@code BinarySearchTreeNode} object has a left son.
     * <p>
     * If current {@code BinarySearchTreeNode} object has a left son, {@code true} is returned;
     * otherwise {@code false} is returned.
     *
     * @return A {@code boolean} value.
     */
    public boolean hasLeftSon() {
        return (this.leftSon != null);
    }

    /**
     * This function is used to check if current {@code BinarySearchTreeNode} object has only one son.
     * <p>
     * If current {@code BinarySearchTreeNode} object has only one son, {@code true} is returned;
     * otherwise {@code false} is returned.
     *
     * @return A {@code boolean} value.
     */
    public boolean hasOneSon() {
        return (this.leftSon != null && this.rightSon == null) || (this.leftSon == null && this.rightSon != null);
    }

    /**
     * This function is used to switch current {@code Key}-{@code Value} objects with
     * those stored in specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    public void switchWith(BinarySearchTreeNode<Key, Value> aNode) {

        Key tempKey;
        Value tempValue;

        // Switch Value...
        tempValue = this.value;
        this.value = aNode.getValue();
        aNode.setValue(tempValue);

        // Switch key...
        tempKey = this.key;
        this.key = aNode.getKey();
        aNode.setKey(tempKey);
    }

    // =================================================================== //
    // 'Getter' and 'Setter' methods...
    // =================================================================== //

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setParent(BinarySearchTreeNode<Key, Value> parent) {
        this.parent = parent;
    }

    public NodeRelationship getParentRelationship() {
        return parentRelationship;
    }

    void setParentRelationship(NodeRelationship parentRelationship) {
        this.parentRelationship = parentRelationship;
    }
}