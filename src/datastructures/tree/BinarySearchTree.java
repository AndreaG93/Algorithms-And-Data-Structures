package datastructures.tree;

import datastructures.Dictionary;
import datastructures.tree.node.BinarySearchTreeNode;
import datastructures.tree.node.utility.NodeRelationship;
import datastructures.tree.utility.BinaryTreeVisitJob;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * A Java-Implementation of a binary search tree.
 *
 * @param <Key> - Represents a {@code Comparable<Key>} object.
 * @param <Value> - Represents a {@code Value} object.
 * @author Andrea Graziani
 * @version 1.0
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements Dictionary<Key, Value> {

    BinarySearchTreeNode<Key, Value> root = null;
    long size = 0;

    // =================================================================== //
    // 'Override'/'Public' methods...
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

        return ((myNode = searchNode(aKey)) == null) ? null : myNode.getValue();
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
        return ((myNode = getMaxNode(this.root)) == null) ? null : myNode.getValue();
    }

    @Override
    public Value getMin() {

        BinarySearchTreeNode<Key, Value> myNode;
        return ((myNode = getMinNode(this.root)) == null) ? null : myNode.getValue();
    }

    // =================================================================== //
    // 'Public' methods...
    // =================================================================== //

    /**
     * Perform a 'breadth-first-search' on current tree.
     *
     * @param aVisitJob - Represents a {@code BinaryTreeVisitJob} object.
     */
    public void BFSVisit(BinaryTreeVisitJob<Key, Value> aVisitJob) {

        LinkedBlockingQueue<BinarySearchTreeNode<Key, Value>> myQueue = new LinkedBlockingQueue<>();

        myQueue.add(this.root);

        while(!myQueue.isEmpty()) {
            BinarySearchTreeNode<Key, Value> myCurrentNode = myQueue.poll();

            if (myCurrentNode != null){

                aVisitJob.visit(myCurrentNode);

                if (myCurrentNode.hasLeftSon())
                    myQueue.add(myCurrentNode.getLeftSon());
                if (myCurrentNode.hasRightSon())
                    myQueue.add(myCurrentNode.getRightSon());
            }
        }
    }

    // =================================================================== //
    // 'Package Private' methods...
    // =================================================================== //

    /**
     * This function is used to insert a new {@code BinarySearchTreeNode} object into current tree.
     * <p>
     * If current tree already contains a node mapped with same {@code aNode} key, the {@code Value}, already stored in
     * this tree, is returned after modifying existing node to contain new value stored in {@code aNode}.
     * <p>
     * Otherwise, specified {@code BinarySearchTreeNode} object is inserted to tree and {@code null} is returned.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value insertNode(BinarySearchTreeNode<Key, Value> aNode) {

        // Case 1: tree is empty therefore new 'BinarySearchTreeNode' became new root...
        // =================================================================== //
        if (this.isEmpty()) {
            this.root = aNode;
        }
        // Case 2: tree isn't empty therefore we start to search position where insert
        //         specified 'BinarySearchTreeNode'...
        // =================================================================== //
        else {

            // Searching correct position...
            // =================================================================== //
            for (BinarySearchTreeNode<Key, Value> myCurrentTreeNode = this.root, myParentTreeNode; ; ) {

                myParentTreeNode = myCurrentTreeNode;
                int comparisonResult = myCurrentTreeNode.getKey().compareTo(aNode.getKey());

                // Specified 'BinarySearchTreeNode' key is smaller than current node key:
                // we continue searching on left subtree...
                // =================================================================== //
                if (comparisonResult > 0) {
                    myCurrentTreeNode = myCurrentTreeNode.getLeftSon();

                    // Previous node is a leaf therefore we can add new node...
                    if (myCurrentTreeNode == null) {
                        myParentTreeNode.setLeftSon(aNode);
                        break;
                    }

                }
                // Specified 'BinarySearchTreeNode' key is smaller than current node key:
                // we continue searching on right subtree...
                // =================================================================== //
                else if (comparisonResult < 0) {
                    myCurrentTreeNode = myCurrentTreeNode.getRightSon();

                    // Previous node is a leaf therefore we can add new node...
                    if (myCurrentTreeNode == null) {
                        myParentTreeNode.setRightSon(aNode);
                        break;
                    }
                }
                // Specified 'BinarySearchTreeNode' key is already present therefore we replace stored value...
                // =================================================================== //
                else {
                    Value myOldValue = myCurrentTreeNode.getValue();
                    myCurrentTreeNode.setValue(aNode.getValue());
                    return myOldValue;
                }
            }
        }

        // Update count...
        this.size++;
        return null;
    }

    /**
     * This function is used to remove a specified {@code BinarySearchTreeNode} object.
     * <p>
     * If current tree contains a node mapped with specified {@code Key}, its corresponding {@code Value} object is
     * returned and node is definitively removed; otherwise {@code null} is returned.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value removeNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();

        // Case 1: Current node is a leaf...
        // =================================================================== //
        if (aNode.hasNoSon()) {

            // Case 1.1: Current node isn't tree root...
            // =================================================================== //
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
            // Case 1.2: Current node isn't tree root...
            // =================================================================== //
            else
                this.root = null;
        }
        // Case 2: Current node has one son...
        // =================================================================== //
        else if (aNode.hasOneSon()) {

            // Getting child...
            BinarySearchTreeNode<Key, Value> myNodeSon = (aNode.hasLeftSon()) ? aNode.getLeftSon() : aNode.getRightSon();

            // Case 2.1: Current node isn't tree root...
            // =================================================================== //
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
            // Case 2.2: Current node isn't tree root...
            // =================================================================== //
            else {
                this.root = myNodeSon;
                myNodeSon.setParent(null);
            }


        }
        // Case 3: Current node has two sons...
        // =================================================================== //
        else {
            BinarySearchTreeNode<Key, Value> predecessor = this.getPredecessorNode(aNode);

            aNode.switchWith(predecessor);
            removeNode(predecessor);
            return predecessor.getValue();
        }

        this.size--;
        return aNode.getValue();
    }

    /**
     * This function is used to search a {@code BinarySearchTreeNode} object mapped with specified {@code Key} object..
     * <p>
     * If current tree contains a {@code BinarySearchTreeNode} object mapped with specified {@code Key}, it will be
     * returned; otherwise {@code null} is returned.
     *
     * @param aKey - Represents a {@code Key} object.
     * @return A {@code BinarySearchTreeNode} object or {@code null}.
     */
    BinarySearchTreeNode<Key, Value> searchNode(Key aKey) {

        // Searching a 'BinarySearchTreeNode' mapped with specified key...
        // =================================================================== //
        for (BinarySearchTreeNode<Key, Value> myCurrentNode = this.root; myCurrentNode != null; ) {

            int comparisonResult = myCurrentNode.getKey().compareTo(aKey);

            // Specified key is smaller than current node key: we continue searching on
            // left subtree...
            // =================================================================== //
            if (comparisonResult < 0) {
                myCurrentNode = myCurrentNode.getRightSon();
            }
            // Specified key is bigger than current node key: we continue searching on
            // right subtree...
            // =================================================================== //
            else if (comparisonResult > 0) {
                myCurrentNode = myCurrentNode.getLeftSon();
            }
            // Specified key is found...
            // =================================================================== //
            else
                return myCurrentNode;
        }
        return null;
    }

    /**
     * This function is used to perform a right rotation of a specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    void rightRotation(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();
        BinarySearchTreeNode<Key, Value> myNodeLeftSon = aNode.getLeftSon();

        // Managing 'parent' node...
        // =================================================================== //
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
        // =================================================================== //
        aNode.setLeftSon(myNodeLeftSon.getRightSon());
        myNodeLeftSon.setRightSon(aNode);
    }

    /**
     * This function is used to perform a left rotation of a specified {@code BinarySearchTreeNode} object.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     */
    void leftRotation(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myNodeParent = aNode.getParent();
        BinarySearchTreeNode<Key, Value> myNodeRightSon = aNode.getRightSon();

        // Managing 'parent' node...
        // =================================================================== //
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
        // =================================================================== //
        aNode.setRightSon(myNodeRightSon.getLeftSon());
        myNodeRightSon.setLeftSon(aNode);
    }

    /**
     * This function is used to search a predecessor of a specified {@code BinarySearchTreeNode} object.
     * <p>
     * If specified {@code BinarySearchTreeNode} has a predecessor node the latter it is returned;
     * otherwise {@code null} is returned.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     * @return A {@code BinarySearchTreeNode} object or {@code null}.
     */
    BinarySearchTreeNode<Key, Value> getPredecessorNode(BinarySearchTreeNode<Key, Value> aNode) {

        // Case 1: specified node is null...
        // =================================================================== //
        if (aNode == null) {
            return null;
        }
        // Case 2: specified 'BinarySearchTreeNode' is root of a left subtree; predecessor
        //         is the node with max key in this subtree...
        // =================================================================== //
        if (aNode.hasLeftSon()) {
            return getMaxNode(aNode.getLeftSon());
        }
        // Case 3: we visit the tree upwards until the first right turn...
        // =================================================================== //
        else {

            BinarySearchTreeNode<Key, Value> myCurrentNode = aNode;
            BinarySearchTreeNode<Key, Value> myPredecessor = aNode.getParent();

            while (myPredecessor != null && myCurrentNode.getParentRelationship() == NodeRelationship.isLeftSon) {
                myCurrentNode = myPredecessor;
                myPredecessor = myPredecessor.getParent();
            }

            return myPredecessor;
        }
    }

    /**
     * This function is used to search a successor of a specified {@code BinarySearchTreeNode} object.
     * <p>
     * If specified {@code BinarySearchTreeNode} has a successor node the latter it is returned;
     * otherwise {@code null} is returned.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     * @return A {@code BinarySearchTreeNode} object or {@code null}.
     */
    BinarySearchTreeNode<Key, Value> getSuccessorNode(BinarySearchTreeNode<Key, Value> aNode) {

        // Case 1: specified node is null...
        // =================================================================== //
        if (aNode == null) {
            return null;
        }
        // Case 2: specified 'BinarySearchTreeNode' is root of a right subtree; successor
        //         is the node with min key in this subtree...
        // =================================================================== //
        if (aNode.hasRightSon()) {
            return getMinNode(aNode.getLeftSon());
        }
        // Case 3: we visit the tree upwards until the first left turn...
        // =================================================================== //
        else {

            BinarySearchTreeNode<Key, Value> myCurrentNode = aNode;
            BinarySearchTreeNode<Key, Value> mySuccessor = aNode.getParent();

            while (mySuccessor != null && myCurrentNode.getParentRelationship() == NodeRelationship.isRightSon) {
                myCurrentNode = mySuccessor;
                mySuccessor = mySuccessor.getParent();
            }

            return mySuccessor;
        }
    }

    // =================================================================== //
    // 'Private' methods...
    // =================================================================== //

    /**
     * This function is used to search node with max key from a specified root {@code BinarySearchTreeNode}
     * object subtree.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     * @return A {@code BinarySearchTreeNode} object or {@code null}.
     */
    private BinarySearchTreeNode<Key, Value> getMaxNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myMaxNode = aNode;

        if (myMaxNode == null)
            return null;
        else {

            // Search last leaf on right subtree...
            // =================================================================== //
            while (myMaxNode.hasRightSon())
                myMaxNode = myMaxNode.getRightSon();
        }
        return myMaxNode;
    }

    /**
     * This function is used to search node with min key from a specified root {@code BinarySearchTreeNode}
     * object subtree.
     *
     * @param aNode - Represents a {@code BinarySearchTreeNode} object.
     * @return A {@code BinarySearchTreeNode} object or {@code null}.
     */
    private BinarySearchTreeNode<Key, Value> getMinNode(BinarySearchTreeNode<Key, Value> aNode) {

        BinarySearchTreeNode<Key, Value> myMinNode = aNode;

        if (myMinNode == null)
            return null;
        else {

            // Search last leaf on right subtree...
            // =================================================================== //
            while (myMinNode.hasLeftSon())
                myMinNode = myMinNode.getLeftSon();
        }
        return myMinNode;
    }
}