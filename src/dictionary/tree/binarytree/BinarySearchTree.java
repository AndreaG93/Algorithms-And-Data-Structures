package dictionary.tree.binarytree;

import dictionary.Dictionary;
import dictionary.tree.binarytree.node.BinarySearchTreeNode;

/**
 * A Java-Implementation of a binary search tree data structure.
 *
 * @author Andrea Graziani
 * @version 1.5
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements Dictionary<Key, Value> {

    BinarySearchTreeNode<Key, Value> root = null;
    long size = 0;

    // =================================================================== //
    // Override methods...
    // =================================================================== //

    @Override
    public Value insert(Key aKey, Value aValue) {
        return this.insertNode(new BinarySearchTreeNode<>(aKey, aValue));
    }

    @Override
    public Value remove(Key aKey) {
        BinarySearchTreeNode<Key, Value> myNode = this.searchNode(aKey);
        return (myNode == null) ? null : removeNode(myNode);
    }

    @Override
    public Value search(Key aKey) {

        BinarySearchTreeNode<Key, Value> myNode;

        return ((myNode = searchNode(aKey)) == null) ? null : myNode.getElement();
    }

    @Override
    public boolean isEmpty() {
        return (this.root == null);
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public long size() {
        return this.size;
    }

    @Override
    public Value getMax() {

        BinarySearchTreeNode<Key, Value> myNode;

        return ((myNode = getMaxNode()) == null) ? null : myNode.getElement();
    }

    @Override
    public Value getMin() {

        BinarySearchTreeNode<Key, Value> myNode;

        return ((myNode = getMinNode()) == null) ? null : myNode.getElement();
    }

    // =================================================================== //
    // Public methods...
    // =================================================================== //

    /**
     * This method is used to retrieve tree's root.
     *
     * @return It returns tree's root if exists; otherwise {@code null}.
     */
    public BinarySearchTreeNode<Key, Value> getRootNode() {
        return this.root;
    }

    /**
     * This method is used to insertNode a new {@code BinarySearchTreeNode} object into current BST.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    public Value insertNode(BinarySearchTreeNode<Key, Value> aNode) {

        if (this.isEmpty())
            this.root = aNode;
        else {

            int comparisonResult = 1;
            BinarySearchTreeNode<Key, Value> myCurrentTreeNode = this.root;
            BinarySearchTreeNode<Key, Value> myParentTreeNode = this.root;

            // Searching correct location...
            while (myCurrentTreeNode != null) {

                myParentTreeNode = myCurrentTreeNode;
                comparisonResult = myCurrentTreeNode.getKey().compareTo(aNode.getKey());

                if (comparisonResult > 0)
                    myCurrentTreeNode = myCurrentTreeNode.getLeftSon();
                else if (comparisonResult < 0)
                    myCurrentTreeNode = myCurrentTreeNode.getRightSon();
                else {
                    Value myOldValue = myCurrentTreeNode.getElement();
                    myCurrentTreeNode.setElement(aNode.getElement());
                    return myOldValue;
                }
            }

            // Setting attributes...
            aNode.setParent(myParentTreeNode);

            if (comparisonResult > 0)
                myParentTreeNode.setLeftSon(aNode);
            else
                myParentTreeNode.setRightSon(aNode);
        }

        // Update count...
        this.size++;
        return null;
    }

    /**
     * This function is used to remove first occurrence of a specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    public Value removeNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();

        // Case 0: Specified node has NO sons...
        // ------------------------------------------------------------------------------- //
        if (aNode.hasNoSon()) {

            // Case 0.0: Specified node is NOT tree root...
            if (myNodeParent != null) {
                switch (aNode.getParentRelationship()) {
                    case isLeftSon:
                        myNodeParent.setLeftSon(null);
                        break;
                    case isRightSon:
                        myNodeParent.setRightSon(null);
                        break;
                }
            }
            // Case 0.1: Specified node IS tree root...
            else
                this.root = null;

            this.size--;
        }
        // Case 1: Specified node has ONE sons...
        // ------------------------------------------------------------------------------- //
        else if (aNode.hasOneSon()) {

            // Getting child...
            BinarySearchTreeNode<Key, Value> myNodeSon = (aNode.hasLeftSon()) ? aNode.getLeftSon() : aNode.getRightSon();

            // Case 1.0: Specified node is NOT tree root...
            if (myNodeParent != null) {
                switch (aNode.getParentRelationship()) {
                    case isLeftSon:
                        myNodeParent.setLeftSon(myNodeSon);
                        break;
                    case isRightSon:
                        myNodeParent.setRightSon(myNodeSon);
                        break;
                }
            }
            // Case 1.1: Specified node IS tree root...
            else {
                this.root = myNodeSon;
                myNodeSon.setParent(null);
            }

            this.size--;
        }
        // Case 2: Specified node has TWO sons...
        // ------------------------------------------------------------------------------- //
        else {
            BinarySearchTreeNode<Key, Value> predecessor = this.getPredecessorNode(aNode);

            aNode.switchWith(predecessor);
            removeNode(predecessor);
            return predecessor.getElement();
        }

        // Update count...
        return aNode.getElement();
    }

    /**
     * This function is used to search first occurrence of a {@code BinarySearchTreeNode} object with specified key.
     *
     * @param aKey - Represents a {@code Comparable} object.
     * @return It returns first occurrence of a {@code BinarySearchTreeNode} object which key match specified key; otherwise it returns {@code null}.
     */
    public BinarySearchTreeNode<Key, Value> searchNode(Key aKey) {

        BinarySearchTreeNode<Key, Value> myCurrentNode = this.root;

        while (myCurrentNode != null) {

            int comparisonResult = myCurrentNode.getKey().compareTo(aKey);

            if (comparisonResult < 0)
                myCurrentNode = myCurrentNode.getRightSon();
            else if (comparisonResult > 0)
                myCurrentNode = myCurrentNode.getLeftSon();
            else
                return myCurrentNode;
        }
        return null;
    }

    /**
     * This function is used to retrieve predecessor of a specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     * @return It returns a {@code BinarySearchTreeNode} object.
     */
    public BinarySearchTreeNode<Key, Value> getPredecessorNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> predecessor = aNode;

        while (true) {

            if (predecessor.hasLeftSon()) {
                predecessor = predecessor.getLeftSon();
                while (predecessor.hasRightSon())
                    predecessor = predecessor.getRightSon();
                return predecessor;
            } else
                predecessor = predecessor.getParent();
        }
    }

    /**
     * This function is used to retrieve node with max key.
     *
     * @return It returns a {@code BinarySearchTreeNode} object.
     */
    public BinarySearchTreeNode<Key, Value> getMaxNode() {

        BinarySearchTreeNode<Key, Value> maxNode = this.root;

        while (maxNode.hasRightSon())
            maxNode = maxNode.getRightSon();

        return maxNode;
    }

    /**
     * This function is used to retrieve node with min key.
     *
     * @return It returns a {@code BinarySearchTreeNode} object.
     */
    public BinarySearchTreeNode<Key, Value> getMinNode() {

        BinarySearchTreeNode<Key, Value> minNode = this.root;

        while (minNode.hasLeftSon())
            minNode = minNode.getLeftSon();

        return minNode;
    }

    /**
     * This method is used to check if current {@code BinarySearchTree} object is an authentic BST.
     *
     * @return A {@code boolean} value.
     */
    public boolean isBinarySearchTree() {
        return isBinarySearchTreeUtility(this.root, this.getMinNode().getKey(), this.getMaxNode().getKey());
    }

    // =================================================================== //
    // Private methods...
    // =================================================================== //

    /**
     * This utility method is used to check if current {@code BinarySearchTree} object is an authentic BST.
     *
     * @param aNode   - Represents a {@code BinarySearchTreeNode} object.
     * @param aMinKey - Represents a {@code Comparable} object.
     * @param aMaxKey - Represents a {@code Comparable} object.
     * @return A {@code boolean} value.
     */
    private boolean isBinarySearchTreeUtility(BinarySearchTreeNode<Key, Value> aNode, Key aMinKey, Key aMaxKey) {
        if (aNode == null)
            return true;
        else if (aNode.getKey().compareTo(aMinKey) <= 0 && aNode.getKey().compareTo(aMaxKey) >= 0)
            return false;

        return (isBinarySearchTreeUtility(aNode.getLeftSon(), aMinKey, aNode.getKey()) && isBinarySearchTreeUtility(aNode.getRightSon(), aNode.getKey(), aMaxKey));
    }

    // =================================================================== //
    // Package-Private methods...
    // =================================================================== //

    /**
     * This function is used to do a simple right rotation.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    void rightRotation(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();
        BinarySearchTreeNode<Key, Value> myNodeLeftSon = aNode.getLeftSon();

        // Setting up "Parent" node...
        // ------------------------------------------------------------------------------- //
        if (myNodeParent != null) {

            switch (aNode.getParentRelationship()) {
                case isLeftSon:
                    myNodeParent.setLeftSon(myNodeLeftSon);
                    break;
                case isRightSon:
                    myNodeParent.setRightSon(myNodeLeftSon);
                    break;
            }
        } else {
            this.root = myNodeLeftSon;
            myNodeLeftSon.setParent(null);
        }

        // Finishing...
        // ------------------------------------------------------------------------------- //
        aNode.setLeftSon(myNodeLeftSon.getRightSon());
        myNodeLeftSon.setRightSon(aNode);

    }

    /**
     * This function is used to do a simple left rotation.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    void leftRotation(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();
        BinarySearchTreeNode<Key, Value> myNodeRightSon = aNode.getRightSon();

        // Setting up "Parent" node...
        // ------------------------------------------------------------------------------- //
        if (myNodeParent != null) {

            switch (aNode.getParentRelationship()) {
                case isLeftSon:
                    myNodeParent.setLeftSon(myNodeRightSon);
                    break;
                case isRightSon:
                    myNodeParent.setRightSon(myNodeRightSon);
                    break;
            }
        } else {
            this.root = myNodeRightSon;
            myNodeRightSon.setParent(null);
        }

        // Finishing...
        // ------------------------------------------------------------------------------- //
        aNode.setRightSon(myNodeRightSon.getLeftSon());
        myNodeRightSon.setLeftSon(aNode);
    }
}