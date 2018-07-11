package dictionary.tree.node;

/**
 * A Java-Implementation of an AVL tree node.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class AVLTreeNode<Key extends Comparable<Key>, Value> extends BinarySearchTreeNode<Key, Value> {

    private long leftSubtreeHeight;
    private long rightSubtreeHeight;

    /**
     * Construct a newly allocated {@code BinarySearchTreeNode} object.
     *
     * @param aKey   - It Represents type of keys.
     * @param aValue - It Represents type of values.
     */
    public AVLTreeNode(Key aKey, Value aValue) {
        super(aKey, aValue);
    }

    // =================================================================== //
    // 'Public' methods...
    // =================================================================== //

    /**
     * This function is used to get balance factor of the current {@code AVLTreeNode} object.
     *
     * @return A {@code long} value.
     */
    public long getBalanceFactor(){
        return this.leftSubtreeHeight - this.rightSubtreeHeight;
    }

    /**
     * This function is used to update subtree's height of current node.
     */
    public void updateSubtreesHeight(){

        AVLTreeNode<Key, Value> myLeftSonNode = (AVLTreeNode<Key, Value>) this.getLeftSon();
        AVLTreeNode<Key, Value> myRightSonNode = (AVLTreeNode<Key, Value>) this.getRightSon();

        // Calc left subtree height...
        // =================================================================== //
        if (myLeftSonNode != null)
            this.leftSubtreeHeight = Math.max(myLeftSonNode.leftSubtreeHeight, myLeftSonNode.rightSubtreeHeight) + 1;
        else
            this.leftSubtreeHeight = 0;

        // Calc right subtree height...
        // =================================================================== //
        if (myRightSonNode != null)
            this.rightSubtreeHeight = Math.max(myRightSonNode.leftSubtreeHeight, myRightSonNode.rightSubtreeHeight) + 1;
        else
            this.rightSubtreeHeight = 0;
    }
}