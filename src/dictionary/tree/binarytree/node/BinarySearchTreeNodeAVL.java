package dictionary.tree.binarytree.node;

/**
 *
 */
public class BinarySearchTreeNodeAVL<Key extends Comparable<Key>, Value> extends BinarySearchTreeNode<Key, Value> {

    private BinarySearchTreeNode<Key, Value> root;
    private long leftSubtreeHeight;
    private long rightSubtreeHeight;


    // =================================================================== //
    // Override methods...
    // =================================================================== //









    public BinarySearchTreeNodeAVL(Key aKey, Value aValue) {
        super(aKey, aValue);
    }

    /**
     * This function determines the balance factor of the current {@code BinarySearchTreeNodeAVL<Key, Value>} object.
     * The balance factor is equal to the difference between the height of the left subtree and the right subtree of current node.
     *
     * @return It returns a {@code long} value.
     */
    public long getBalanceFactor(){
        return this.leftSubtreeHeight - this.rightSubtreeHeight;
    }

    /**
     * This function is used to update subtree's height of current node.
     */
    public void updateSubtreesHeight(){

        BinarySearchTreeNodeAVL<Key, Value> myLeftSonNode = (BinarySearchTreeNodeAVL<Key, Value>) this.getLeftSon();
        BinarySearchTreeNodeAVL<Key, Value> myRightSonNode = (BinarySearchTreeNodeAVL<Key, Value>) this.getRightSon();

        // Calc left subtree height...
        // ------------------------------------------------------------------------------- //
        if (myLeftSonNode != null)
            this.leftSubtreeHeight = Math.max(myLeftSonNode.leftSubtreeHeight, myLeftSonNode.rightSubtreeHeight) + 1;
        else
            this.leftSubtreeHeight = 0;

        // Calc right subtree height...
        // ------------------------------------------------------------------------------- //
        if (myRightSonNode != null)
            this.rightSubtreeHeight = Math.max(myRightSonNode.leftSubtreeHeight, myRightSonNode.rightSubtreeHeight) + 1;
        else
            this.rightSubtreeHeight = 0;
    }
}
