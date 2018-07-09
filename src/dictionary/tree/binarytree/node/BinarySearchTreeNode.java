package dictionary.tree.binarytree.node;

/**
 * This class represents a node of an binary search tree (BST) data structure.
 *
 * @author Andrea Graziani
 * @version 0.8
 */
public class BinarySearchTreeNode<Key extends Comparable<Key>, Value> {

    private Key key;
    private Value element;
    private BinarySearchTreeNode<Key, Value> parent;
    private BinarySearchTreeNode<Key, Value> leftSon;
    private BinarySearchTreeNode<Key, Value> rightSon;
    private NodeRelationship parentRelationship;

    /**
     * Construct a newly allocated {@code BinarySearchTreeNode} object.
     *
     * @param aKey     - It Represents type of keys.
     * @param aElement - It Represents type of values.
     */
    public BinarySearchTreeNode(Key aKey, Value aElement) {
        this.key = aKey;
        this.element = aElement;
    }


    public BinarySearchTreeNode<Key, Value> getGrandparent() {
        return (this.parent == null) ? null : this.parent.getParent();
    }



    public BinarySearchTreeNode<Key, Value> getBrother() {

        if (this.parent != null)
            switch (this.getParentRelationship()) {
                case isLeftSon:
                    return this.parent.getRightSon();
                case isRightSon:
                    return this.parent.getLeftSon();
            }
        return null;
    }



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
     * This function is used to retrieve parent node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object.
     */
    public BinarySearchTreeNode<Key, Value> getParent() {
        return this.parent;
    }

    /**
     * This function is used to retrieve left son node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object.
     */
    public BinarySearchTreeNode<Key, Value> getLeftSon() {
        return this.leftSon;
    }

    /**
     * This function is used to retrieve right son node of current {@code BinarySearchTreeNode} object.
     *
     * @return A {@code BinarySearchTreeNode} object.
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
     * This function is used to check if current {@code BinarySearchTreeNode} object has only one son.
     *
     * @return It returns {@code true} if current {@code BinarySearchTreeNode} object has only one son, otherwise it returns {@code false}.
     */
    public boolean hasNoSon() {
        return this.leftSon == null && this.rightSon == null;
    }

    /**
     * This function is used to check if current {@code BinarySearchTreeNode} object has a right son.
     *
     * @return It returns {@code true} if current {@code BinarySearchTreeNode} object has a right child, otherwise it returns {@code false}.
     */
    public boolean hasRightSon() {
        return (this.rightSon != null);
    }

    /**
     * This function is used to check if current {@code BinarySearchTreeNode} object has a left son.
     *
     * @return It returns {@code true} if current {@code BinarySearchTreeNode} object has a left child, otherwise it returns {@code false}.
     */
    public boolean hasLeftSon() {
        return (this.leftSon != null);
    }

    /**
     * This function is used to check if current {@code BinarySearchTreeNode} object has only one son.
     *
     * @return It returns {@code true} if current {@code BinarySearchTreeNode} object has only one son, otherwise it returns {@code false}.
     */
    public boolean hasOneSon() {
        return (this.leftSon != null && this.rightSon == null) || (this.leftSon == null && this.rightSon != null);
    }

    /**
     * This function is used to switch key and value with a specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    public void switchWith(BinarySearchTreeNode<Key, Value> aNode) {

        Key tempKey;
        Value tempValue;

        // Switch Value...
        tempValue = this.element;
        this.element = aNode.getElement();
        aNode.setElement(tempValue);

        // Switch key...
        tempKey = this.key;
        this.key = aNode.getKey();
        aNode.setKey(tempKey);
    }


    public boolean isRoot(){
        return (this.getParent() == null);
    }



    // ------------------------------------------------------------------------------- //
    // Getter and setter method...
    // ------------------------------------------------------------------------------- //


    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getElement() {
        return element;
    }

    public void setElement(Value element) {
        this.element = element;
    }

    public void setParent(BinarySearchTreeNode<Key, Value> parent) {
        this.parent = parent;
    }

    public NodeRelationship getParentRelationship() {
        return parentRelationship;
    }

    public void setParentRelationship(NodeRelationship parentRelationship) {
        this.parentRelationship = parentRelationship;
    }
}