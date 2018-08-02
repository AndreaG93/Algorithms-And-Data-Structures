package datastructures.tree;

import datastructures.Tree;
import datastructures.tree.node.BinarySearchTreeNode;
import datastructures.tree.node.utility.NodeRelationship;
import datastructures.tree.utility.BinaryTreeVisitJob;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class represents a Java-Implementation of a binary search tree.
 *
 * @param <Key>   - It represents an object its class extends {@code Comparable} class.
 * @param <Value> - It represents a generic object.
 * @author Andrea Graziani
 * @version 1.3
 */
class BinarySearchTree<Key extends Comparable<Key>, Value> implements Tree<Key, Value> {

    BinarySearchTreeNode<Key, Value> root = null;
    long size = 0;

    @Override
    public Value insert(Key pKey, Value pValue) {
        return this.insertNode(new BinarySearchTreeNode<>(pKey, pValue));
    }

    @Override
    public Value remove(Key pKey) {
        BinarySearchTreeNode<Key, Value> mNode = this.searchNode(pKey);
        return (mNode == null) ? null : removeNode(mNode);
    }

    @Override
    public Value search(Key pKey) {

        BinarySearchTreeNode<Key, Value> mNode;

        return ((mNode = searchNode(pKey)) == null) ? null : mNode.getValue();
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

        BinarySearchTreeNode<Key, Value> mNode;
        return ((mNode = getMaxNode(this.root)) == null) ? null : mNode.getValue();
    }

    @Override
    public Value getMin() {

        BinarySearchTreeNode<Key, Value> mNode;
        return ((mNode = getMinNode(this.root)) == null) ? null : mNode.getValue();
    }

    @Override
    public void BFSVisit(BinaryTreeVisitJob<Key, Value> pVisitJob) {

        LinkedBlockingQueue<BinarySearchTreeNode<Key, Value>> myQueue = new LinkedBlockingQueue<>();

        myQueue.add(this.root);

        while(!myQueue.isEmpty()) {
            BinarySearchTreeNode<Key, Value> myCurrentNode = myQueue.poll();

            if (myCurrentNode != null){

                pVisitJob.visit(myCurrentNode);

                if (myCurrentNode.hasLeftSon())
                    myQueue.add(myCurrentNode.getLeftSon());
                if (myCurrentNode.hasRightSon())
                    myQueue.add(myCurrentNode.getRightSon());
            }
        }
    }

    /**
     * This function is used to insert a new {@code BinarySearchTreeNode} object into current tree.
     * <p>
     * If current tree already contains a node mapped with same {@code aNode} key, the {@code Value}, already stored in
     * this tree, is returned after modifying existing node to contain new value stored in {@code aNode}.
     * <p>
     * Otherwise, specified {@code BinarySearchTreeNode} object is inserted to tree and {@code null} is returned.
     *
     * @param pNode - Represents a {@code BinarySearchTreeNode} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value insertNode(BinarySearchTreeNode<Key, Value> pNode) {

        // Case 1: tree is empty therefore new 'BinarySearchTreeNode' became new root...
        // =================================================================== //
        if (this.isEmpty()) {
            this.root = pNode;
        }
        // Case 2: tree isn't empty therefore we start to search position where insert
        //         specified 'BinarySearchTreeNode'...
        // =================================================================== //
        else {

            // Searching correct position...
            // =================================================================== //
            for (BinarySearchTreeNode<Key, Value> mCurrentTreeNode = this.root, mParentTreeNode; ; ) {

                mParentTreeNode = mCurrentTreeNode;
                int comparisonResult = mCurrentTreeNode.getKey().compareTo(pNode.getKey());

                // Specified 'BinarySearchTreeNode' key is smaller than current node key:
                // we continue searching on left subtree...
                // =================================================================== //
                if (comparisonResult > 0) {
                    mCurrentTreeNode = mCurrentTreeNode.getLeftSon();

                    // Previous node is a leaf therefore we can add new node...
                    if (mCurrentTreeNode == null) {
                        mParentTreeNode.setLeftSon(pNode);
                        break;
                    }

                }
                // Specified 'BinarySearchTreeNode' key is smaller than current node key:
                // we continue searching on right subtree...
                // =================================================================== //
                else if (comparisonResult < 0) {
                    mCurrentTreeNode = mCurrentTreeNode.getRightSon();

                    // Previous node is a leaf therefore we can add new node...
                    if (mCurrentTreeNode == null) {
                        mParentTreeNode.setRightSon(pNode);
                        break;
                    }
                }
                // Specified 'BinarySearchTreeNode' key is already present therefore we replace stored value...
                // =================================================================== //
                else {
                    Value myOldValue = mCurrentTreeNode.getValue();
                    mCurrentTreeNode.setValue(pNode.getValue());
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
     * @param pNode - Represents a {@code BinarySearchTreeNode} object.
     * @return A {@code Value} object or {@code null}.
     */
    Value removeNode(BinarySearchTreeNode<Key, Value> pNode) {

        BinarySearchTreeNode<Key, Value> mNodeParent = pNode.getParent();

        // Case 1: Current node is a leaf...
        // =================================================================== //
        if (pNode.hasNoSon()) {

            // Case 1.1: Current node isn't tree root...
            // =================================================================== //
            if (mNodeParent != null) {
                switch (pNode.getParentRelationship()) {
                    case isLeftSon:
                        mNodeParent.setLeftSon(null);
                        break;
                    case isRightSon:
                        mNodeParent.setRightSon(null);
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
        else if (pNode.hasOneSon()) {

            // Getting child...
            BinarySearchTreeNode<Key, Value> myNodeSon = (pNode.hasLeftSon()) ? pNode.getLeftSon() : pNode.getRightSon();

            // Case 2.1: Current node isn't tree root...
            // =================================================================== //
            if (mNodeParent != null) {
                switch (pNode.getParentRelationship()) {
                    case isLeftSon:
                        mNodeParent.setLeftSon(myNodeSon);
                        break;
                    case isRightSon:
                        mNodeParent.setRightSon(myNodeSon);
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
            BinarySearchTreeNode<Key, Value> predecessor = this.getPredecessorNode(pNode);

            pNode.switchWith(predecessor);
            removeNode(predecessor);
            return predecessor.getValue();
        }

        this.size--;
        return pNode.getValue();
    }

    /**
     * This function is used to search a {@code BinarySearchTreeNode} object mapped with specified {@code Key} object..
     * <p>
     * If current tree contains a {@code BinarySearchTreeNode} object mapped with specified {@code Key}, it will be
     * returned; otherwise {@code null} is returned.
     *
     * @param pKey - Represents a {@code Key} object.
     * @return A {@code BinarySearchTreeNode} object or {@code null}.
     */
    BinarySearchTreeNode<Key, Value> searchNode(Key pKey) {

        // Searching a 'BinarySearchTreeNode' mapped with specified key...
        // =================================================================== //
        for (BinarySearchTreeNode<Key, Value> mCurrentNode = this.root; mCurrentNode != null; ) {

            int comparisonResult = mCurrentNode.getKey().compareTo(pKey);

            // Specified key is smaller than current node key: we continue searching on
            // left subtree...
            // =================================================================== //
            if (comparisonResult < 0) {
                mCurrentNode = mCurrentNode.getRightSon();
            }
            // Specified key is bigger than current node key: we continue searching on
            // right subtree...
            // =================================================================== //
            else if (comparisonResult > 0) {
                mCurrentNode = mCurrentNode.getLeftSon();
            }
            // Specified key is found...
            // =================================================================== //
            else
                return mCurrentNode;
        }
        return null;
    }

    /**
     * This function is used to perform a right rotation of a specified {@code BinarySearchTreeNode} object.
     *
     * @param pNode - Represents a {@code BinarySearchTreeNode} object.
     */
    void rightRotation(BinarySearchTreeNode<Key, Value> pNode) {

        BinarySearchTreeNode<Key, Value> mNodeParent = pNode.getParent();
        BinarySearchTreeNode<Key, Value> mNodeLeftSon = pNode.getLeftSon();

        // Managing 'parent' node...
        // =================================================================== //
        if (mNodeParent != null) {

            switch (pNode.getParentRelationship()) {
                case isLeftSon:
                    mNodeParent.setLeftSon(mNodeLeftSon);
                    break;
                case isRightSon:
                    mNodeParent.setRightSon(mNodeLeftSon);
                    break;
            }
        } else {
            this.root = mNodeLeftSon;
            mNodeLeftSon.setParent(null);
        }

        // Finishing...
        // =================================================================== //
        pNode.setLeftSon(mNodeLeftSon.getRightSon());
        mNodeLeftSon.setRightSon(pNode);
    }

    /**
     * This function is used to perform a left rotation of a specified {@code BinarySearchTreeNode} object.
     *
     * @param pNode - Represents a {@code BinarySearchTreeNode} object.
     */
    void leftRotation(BinarySearchTreeNode<Key, Value> pNode) {

        BinarySearchTreeNode<Key, Value> mNodeParent = pNode.getParent();
        BinarySearchTreeNode<Key, Value> mNodeRightSon = pNode.getRightSon();

        // Managing 'parent' node...
        // =================================================================== //
        if (mNodeParent != null) {

            switch (pNode.getParentRelationship()) {
                case isLeftSon:
                    mNodeParent.setLeftSon(mNodeRightSon);
                    break;
                case isRightSon:
                    mNodeParent.setRightSon(mNodeRightSon);
                    break;
            }
        } else {
            this.root = mNodeRightSon;
            mNodeRightSon.setParent(null);
        }

        // Finishing...
        // =================================================================== //
        pNode.setRightSon(mNodeRightSon.getLeftSon());
        mNodeRightSon.setLeftSon(pNode);
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