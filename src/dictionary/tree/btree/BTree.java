package dictionary.tree.btree;

import dictionary.Dictionary;

import java.util.List;


/**
 * treeOrder == maxchildren
 *
 * @param <Key>
 * @param <Value>
 */
public class BTree<Key extends Comparable<Key>, Value> implements Dictionary<Key, Value> {

    private final int TREE_ORDER;
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
        this.MAX_KEY_CARDINALITY = 2 * TREE_ORDER - 1;
    }


    @Override
    public Value insert(Key aKey, Value aValue) {

        // Case 1: tree is empty...
        // =================================================================== //
        if (this.isEmpty()) {

            BTreeNode<Key, Value> myNode = new BTreeNode<>();

            myNode.getValues().add(aValue);
            myNode.getKeys().add(aKey);
            this.root = myNode;
        }
        // Case 2: tree isn't empty: in each step, we search a leaf where insert new key...
        // =================================================================== //
        else {

            BTreeNode<Key, Value> myCurrentNode = this.root;

            while (true) {

                int index = 0;

                // Searching correct new key position...
                // =================================================================== //
                for (Key myNodeKey : myCurrentNode.getKeys()) {

                    // Specified key is already present; replace stored element...
                    if (myNodeKey.compareTo(aKey) == 0) {
                        return myCurrentNode.getValues().set(index, aValue);
                    }
                    // Specified key is bigger than current stored key; increment index...
                    else if (myNodeKey.compareTo(aKey) < 0) {
                        index++;
                    }
                    // Specified key is smaller than current stored key; new key position found...
                    else
                        break;
                }

                // Case 1: Found node can contain our specified 'key-value' pair because is a leaf...
                // =================================================================== //
                if (myCurrentNode.getChildren().isEmpty()) {

                    myCurrentNode.getKeys().add(index, aKey);
                    myCurrentNode.getValues().add(index, aValue);

                    this.split(myCurrentNode);

                    return null;
                }
                // Case 2: Found node can't contain our specified 'key-value' because is not a leaf.
                //         We continue searching in a subtree that can contain it...
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

        return null;
        /*
        // Case 1: tree is empty...
        // =================================================================== //
        if (this.isEmpty()) {
            return null;
        }
        // Case 2: tree isn't empty...
        // =================================================================== //
        else {

            BTreeNode<Key, Value> myCurrentNode = this.root;

            while (true) {

                int index = 0;

                // Searching key-to-delete position...
                // =================================================================== //
                for (Key myNodeKey : myCurrentNode.getKeys()) {

                    // Specified key is found; deleting started...
                    if (myNodeKey.compareTo(aKey) == 0) {

                        this.size--;

                        // Case 1: Found node is a leaf; removing 'key-value' pair...
                        // =================================================================== //
                        if (myCurrentNode.getChildren().isEmpty()) {

                            myCurrentNode.getKeys().remove(index);
                            return myCurrentNode.getValues().remove(index);
                        }
                        // Case 2: Found node isn't a leaf...
                        // =================================================================== //
                        else {

                        }










                    }
                    // Specified key is bigger than current stored key; increment index...
                    else if (myNodeKey.compareTo(aKey) < 0) {
                        index++;
                    }
                    // Specified key is smaller than current stored key; new key position found...
                    else
                        break;
                }

                // Case 1: Specified key not exists...
                // =================================================================== //
                if (myCurrentNode.getChildren().isEmpty()) {
                    return null;
                }
                // Case 2: Found node can't contain our specified 'key' because is not a leaf.
                //         We continue searching in a subtree that can contain it...
                // =================================================================== //
                else
                    myCurrentNode = myCurrentNode.getChildren().get(index);
            }
        }
        */
    }

    @Override
    public Value search(Key aKey) {

        // Case 1: tree is empty...
        // =================================================================== //
        if (this.isEmpty()) {
            return null;
        }
        // Case 1: tree isn't empty...
        // =================================================================== //
        else {

            BTreeNode<Key, Value> myCurrentNode = this.root;

            while (true) {

                int index = 0;

                // Searching specified key in current node...
                // =================================================================== //
                for (Key myNodeKey : myCurrentNode.getKeys()) {

                    // Case 1: Specified key is present, return stored element...
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

                // Case 1: Current node is a leaf...
                if (myCurrentNode.getChildren().isEmpty()) {
                    return null;
                }
                // Case 2: Continue searching in a subtree that can contain specified key...
                else {
                    myCurrentNode = myCurrentNode.getChildren().get(index);
                }
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

            // If current node is a leaf add new key...
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

            // Getting current node's parent node and his index...
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
