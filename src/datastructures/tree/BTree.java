package datastructures.tree;

import datastructures.Dictionary;
import datastructures.tree.node.BTreeNode;

/**
 * This class represents a Java-Implementation of B-tree.
 *
 * @param <Key>   - It represents an object its class extends {@code Comparable} class.
 * @param <Value> - It represents a generic object.
 * @author Andrea Graziani
 * @version 1.3
 */
@SuppressWarnings("unused")
class BTree<Key extends Comparable<Key>, Value> implements Dictionary<Key, Value> {

    private final int TREE_ORDER;
    private final int MIN_KEY_CARDINALITY;
    private final int MAX_KEY_CARDINALITY;
    private BTreeNode<Key, Value> root = null;
    private long size = 0;

    /**
     * Construct a newly allocated {@code BTree} object.
     *
     * @param aTreeOrder - It represents a {@code int} value.
     */
    public BTree(int aTreeOrder) {

        this.TREE_ORDER = aTreeOrder;
        this.MIN_KEY_CARDINALITY = TREE_ORDER - 1;
        this.MAX_KEY_CARDINALITY = 2 * TREE_ORDER - 1;
    }

    @Override
    public Value insert(Key aKey, Value aValue) {

        // Case 1: tree is empty therefore new 'key-value' pair became new root...
        // =================================================================== //
        if (this.isEmpty()) {

            BTreeNode<Key, Value> myNode = new BTreeNode<>();

            myNode.getValues().add(aValue);
            myNode.getKeys().add(aKey);
            this.root = myNode;
        }
        // Case 2: tree isn't empty therefore we start to search a leaf where insert
        //         specified 'key-value' pair...
        // =================================================================== //
        else {

            BTreeNode<Key, Value> myCurrentNode = this.root;

            for (int index = 0 ;; index = 0) {

                // Searching new key correct position compared to current node keys...
                // =================================================================== //
                for (Key myNodeKey : myCurrentNode.getKeys()) {

                    // Specified key is already present therefore we replace stored element...
                    if (myNodeKey.compareTo(aKey) == 0) {
                        return myCurrentNode.getValues().get(index);
                    }
                    // Specified key is bigger than current stored node key therefore we increment index...
                    else if (myNodeKey.compareTo(aKey) < 0) {
                        index++;
                    }
                    // New key position found...
                    else
                        break;
                }

                // Case 2.1: Current treenode is a leaf therefore it can contains new specified
                //           'key-value' pair...
                // =================================================================== //
                if (myCurrentNode.getChildren().isEmpty()) {

                    myCurrentNode.getKeys().add(index, aKey);
                    myCurrentNode.getValues().add(index, aValue);

                    // Checking for split operation...
                    this.split(myCurrentNode);

                    break;
                }
                // Case 2.2: Current treenode can't contain our specified 'key-value' because is not a leaf.
                //           We must continue searching in a subtree that can contain it...
                // =================================================================== //
                else
                    myCurrentNode = myCurrentNode.getChildren().get(index);
            }
        }

        this.size++;
        return null;
    }

    @Override
    public Value remove(Key aKey) {

        // Case 1: tree is empty therefore there are nothing to delete...
        // =================================================================== //
        if (this.isEmpty()) {
            return null;
        }
        // Case 2: tree isn't empty therefore we start to search a leaf that can
        //         can contain specified key...
        // =================================================================== //
        else {

            BTreeNode<Key, Value> myCurrentNode = this.root;

            for (int index = 0 ;; index = 0) {

                // Searching specified key in current treenode...
                // =================================================================== //
                for (Key myNodeKey : myCurrentNode.getKeys()) {

                    // Specified key is found therefore we start to remove stored element...
                    if (myNodeKey.compareTo(aKey) == 0) {

                        this.size--;

                        // Removing stored key...
                        // =================================================================== //
                        while(true) {

                            BTreeNode<Key, Value> myPrecedingChild = getPrecedingChild(myCurrentNode);
                            BTreeNode<Key, Value> mySuccessorChild = getSuccessorChild(myCurrentNode);

                            // Case 2.1: Found treenode is a leaf...
                            // =================================================================== //
                            if (myCurrentNode.getChildren().isEmpty()) {


                                // Case 2.1.1: Current treenode has
                                // =================================================================== //
                                if (myCurrentNode.getKeys().size() > MIN_KEY_CARDINALITY) {

                                    myCurrentNode.getKeys().remove(index);
                                    return myCurrentNode.getValues().remove(index);
                                }
                                // Case 2.1.1: Current treenode has
                                // =================================================================== //
                                else if (myCurrentNode.getKeys().size() > MIN_KEY_CARDINALITY) {

                                }





                            }
                            // Case 2.2: Found treenode is an internal treenode...
                            // =================================================================== //
                            else {

                                // Case 2.2.1: child that precedes specified key in current treenode, contains
                                //             a keys quantity above the allowed minimum...
                                // =================================================================== //
                                if (myPrecedingChild.getKeys().size() > MIN_KEY_CARDINALITY){

                                }
                                // Case 2.2.2: child that follows specified key in current treenode, contains
                                //             a keys quantity above the allowed minimum...
                                // =================================================================== //
                                else if (mySuccessorChild.getKeys().size() > MIN_KEY_CARDINALITY){

                                }
                                // Case 2.2.3: both children that precede and follow specified key in current treenode,
                                //             contains a minimum keys quantity...
                                // =================================================================== //
                                else {

                                }
                            }
                        }
                    }
                    // Specified key is bigger than current stored key; increment index...
                    else if (myNodeKey.compareTo(aKey) < 0) {
                        index++;
                    }
                    // Specified key is smaller than current stored key...
                    else
                        break;
                }

                // Case 2.1: Current treenode is a leaf and doesn't contain our specified 'key'
                //           therefore our remove operation terminate...
                // =================================================================== //
                if (myCurrentNode.getChildren().isEmpty()) {
                    return null;
                }
                // Case 2.2: Current treenode isn't a leaf and doesn't contain our specified 'key'
                //           therefore we must continue searching in a subtree that can contain it...
                // =================================================================== //
                else
                    myCurrentNode = myCurrentNode.getChildren().get(index);
            }
        }
    }

    @Override
    public Value search(Key aKey) {

        // Case 1: tree is empty therefore our search operation terminate...
        // =================================================================== //
        if (this.isEmpty()) {
            return null;
        }
        // Case 2: tree isn't empty therefore we start to search a leaf that can
        //         can contain specified key...
        // =================================================================== //
        else {

            BTreeNode<Key, Value> myCurrentNode = this.root;

            for (int index = 0 ;; index = 0) {

                // Searching specified key in current treenode...
                // =================================================================== //
                for (Key myNodeKey : myCurrentNode.getKeys()) {

                    // Specified key is found therefore we return stored element...
                    if (myNodeKey.compareTo(aKey) == 0) {
                        return myCurrentNode.getValues().get(index);
                    }
                    // Specified key is bigger than current stored key; increment index...
                    else if (myNodeKey.compareTo(aKey) < 0) {
                        index++;
                    }
                    // Specified key is smaller than current stored key...
                    else
                        break;
                }

                // Case 2.1: Current treenode is a leaf and doesn't contain our specified 'key'
                //           therefore our search operation terminate...
                // =================================================================== //
                if (myCurrentNode.getChildren().isEmpty()) {
                    return null;
                }
                // Case 2.2: Current treenode isn't a leaf and doesn't contain our specified 'key'
                //           therefore we must continue searching in a subtree that can contain it...
                // =================================================================== //
                else
                    myCurrentNode = myCurrentNode.getChildren().get(index);
            }
        }
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
        return null;
    }

    @Override
    public Value getMin() {
        return null;
    }


    /**
     *
     * @param aKey
     * @return
     */
    public BTreeNode<Key, Value> searchNode(Key aKey) {

        BTreeNode<Key, Value> myCurrentNode = this.root;

        while (true) {

            int index = 0;

            // Searching correct new key position...
            // =================================================================== //
            for (Key myNodeKey : myCurrentNode.getKeys()) {

                // Specified key is already present; replace stored element...
                if (myNodeKey.compareTo(aKey) == 0) {
                    return myCurrentNode;
                }
                // Specified key is bigger than current stored key; increment index...
                else if (myNodeKey.compareTo(aKey) < 0) {
                    index++;
                }
                // Specified key is smaller than current stored key; new key position found...
                else
                    break;
            }

            // If current treenode is a leaf add new key...
            // =================================================================== //
            if (myCurrentNode.getChildren().isEmpty()) {
                return null;
            }
            // Otherwise we continue search in a subtree that can contain it...
            // =================================================================== //
            else
                myCurrentNode = myCurrentNode.getChildren().get(index);


        }
    }



    private BTreeNode<Key,Value> getPrecedingChild(BTreeNode<Key,Value> myCurrentNode) {
        return null;
    }

    private BTreeNode<Key,Value> getSuccessorChild(BTreeNode<Key,Value> myCurrentNode) {
        return null;
    }

    /**
     * This method is used to split up a specified {@code BTreeNode} object..
     *
     * @param aNode - It represents a {@code BTreeNode} object.
     */
    private void split(BTreeNode<Key, Value> aNode) {

        BTreeNode<Key, Value> myCurrentNode = aNode;

        while (myCurrentNode.getKeys().size() > this.MAX_KEY_CARDINALITY) {

            // Initialization...
            // =================================================================== //

            // Creating new nodes...
            BTreeNode<Key, Value> mySplitNode1 = new BTreeNode<>();
            BTreeNode<Key, Value> mySplitNode2 = new BTreeNode<>();

            // Getting 't-th key' and 't-th value'...
            Key myTKey = myCurrentNode.getKeys().get(this.TREE_ORDER - 1);
            Value myTValue = myCurrentNode.getValues().get(this.TREE_ORDER - 1);

            // Getting current treenode's parent treenode and his index...
            BTreeNode<Key, Value> myNodeParent = myCurrentNode.getParent();
            int index = myCurrentNode.getChildIndex();

            // Start Node-Splitting...
            // =================================================================== //

            // Splitting keys and values...
            for (int i = 0; i < this.TREE_ORDER - 1; i++) {
                mySplitNode1.getKeys().add(myCurrentNode.getKeys().get(i));
                mySplitNode1.getValues().add(myCurrentNode.getValues().get(i));
            }

            for (int i = this.TREE_ORDER; i < myCurrentNode.getKeys().size(); i++) {
                mySplitNode2.getKeys().add(myCurrentNode.getKeys().get(i));
                mySplitNode2.getValues().add(myCurrentNode.getValues().get(i));
            }

            // Splitting children...
            for (int i = 0; i < myCurrentNode.getChildren().size(); i++) {
                if (i <= this.TREE_ORDER)
                    mySplitNode1.getChildren().add(myCurrentNode.getChildren().get(i));
                else
                    mySplitNode2.getChildren().add(myCurrentNode.getChildren().get(i));
            }

            // Creating a new root...
            // =================================================================== //
            if (myNodeParent == null) {

                BTreeNode<Key, Value> myNode = new BTreeNode<>();

                // Set key and element...
                myNode.getValues().add(myTValue);
                myNode.getKeys().add(myTKey);

                // Set children 1...
                mySplitNode1.setParent(myNode);
                mySplitNode1.setChildIndex(0);
                myNode.getChildren().add(mySplitNode1);

                // Set children 2...
                mySplitNode2.setParent(myNode);
                mySplitNode2.setChildIndex(1);
                myNode.getChildren().add(mySplitNode2);

                this.root = myNode;
                return;
            }
            // Update root parent...
            // =================================================================== //
            else {

                System.out.println(myNodeParent.getChildren().get(0).getKeys().get(0));
                System.out.println(myNodeParent.getChildren().get(1).getKeys().get(0));

                // Set children 1...
                mySplitNode1.setParent(myNodeParent);
                mySplitNode1.setChildIndex(index);
                myNodeParent.getChildren().add(index, mySplitNode1);

                // Set children 2...
                mySplitNode2.setParent(myNodeParent);
                mySplitNode2.setChildIndex(index + 1);
                myNodeParent.getChildren().add(index + 1, mySplitNode2);

                // Add key and element...
                myNodeParent.getValues().add(index, myTValue);
                myNodeParent.getKeys().add(index, myTKey);

                myCurrentNode = myNodeParent;
            }
        }
    }
}