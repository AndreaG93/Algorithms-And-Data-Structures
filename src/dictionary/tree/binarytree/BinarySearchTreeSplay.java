package dictionary.tree.binarytree;

import dictionary.tree.binarytree.node.BinarySearchTreeNode;



public class BinarySearchTreeSplay<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    // =================================================================== //
    // Override methods...
    // =================================================================== //

    @Override
    public Value insertNode(BinarySearchTreeNode<Key, Value> aNode) {
        Value myOutput;

        if ((myOutput = super.insertNode(aNode)) == null)
            this.splay(aNode);
        return myOutput;
    }

    @Override
    public Value removeNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myParentNode = aNode.getParent();
        Value myOutput;

        if ((myOutput = super.removeNode(aNode)) != null && myParentNode != null)
            this.splay(myParentNode);
        return myOutput;
    }

    @Override
    public BinarySearchTreeNode<Key, Value> searchNode(Key aKey) {
        BinarySearchTreeNode<Key, Value> myOutput;

        if ((myOutput = super.searchNode(aKey)) != null)
            this.splay(myOutput);
        return super.searchNode(aKey);
    }

    // =================================================================== //
    // Private methods...
    // =================================================================== //

    /**
     * This function is used to do a node rotation.
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
     * This method is used to perform a "splay" operation.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    private void splay(BinarySearchTreeNode<Key, Value> aNode) {

        while (!aNode.isRoot()) {

            BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();

            if (myNodeParent.isRoot())
                this.rotation(aNode);
            else{

                if (aNode.getParentRelationship() == myNodeParent.getParentRelationship()){
                    this.rotation(aNode.getParent());
                    this.rotation(aNode);
                }
                else {
                    this.rotation(aNode);
                    this.rotation(aNode);
                }
            }
        }
    }
}
