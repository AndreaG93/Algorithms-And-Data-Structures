package dictionary.tree.binarytree;

import dictionary.tree.binarytree.node.*;

import java.awt.*;

public class BinarySearchTreeRB<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    // =================================================================== //
    // Override methods...
    // =================================================================== //

    @Override
    public Value insert(Key aKey, Value aValue) {
        return this.insertNode(new BinarySearchTreeNodeRB<>(aKey, aValue));
    }

    @Override
    public Value insertNode(BinarySearchTreeNode<Key, Value> aNode) {

        Value myOutputValue = super.insertNode(aNode);

        if (myOutputValue != null)
            return myOutputValue;
        else
            nodeCheck((BinarySearchTreeNodeRB<Key, Value>) aNode);

        return null;
    }

    @Override
    public Value remove(Key aKey) {
        BinarySearchTreeNodeRB<Key, Value> myNode = (BinarySearchTreeNodeRB<Key, Value>) this.searchNode(aKey);
        return (myNode == null) ? null : removeNode(myNode);
    }

    @Override
    public Value removeNode(BinarySearchTreeNode<Key, Value> aNode) {
        // TODO
        return null;

    }



    /**
     * This method is used to check inserted node...
     * @param aNode
     */
    private void nodeCheck(BinarySearchTreeNodeRB<Key, Value> aNode) {

        while(true) {

            BinarySearchTreeNodeRB<Key, Value> myNodeParent = (BinarySearchTreeNodeRB<Key, Value>) aNode.getParent();
            BinarySearchTreeNodeRB<Key, Value> myNodeGrandParent = (BinarySearchTreeNodeRB<Key, Value>) aNode.getGrandparent();
            BinarySearchTreeNodeRB<Key, Value> myNodeUncle = (BinarySearchTreeNodeRB<Key, Value>) aNode.getUncle();

            if (myNodeParent == null) {
                aNode.setNodeColor(NodeColor.Black);
                return;
            }
            else if (myNodeParent.getNodeColor() == NodeColor.Black)
                return;
            else if (myNodeUncle != null && myNodeUncle.getNodeColor() == NodeColor.Red) {

                myNodeParent.setNodeColor(NodeColor.Black);
                myNodeUncle.setNodeColor(NodeColor.Black);
                myNodeGrandParent.setNodeColor(NodeColor.Red);

                aNode = myNodeGrandParent;
            }
            else if (aNode.getParentRelationship() == NodeRelationship.isRightSon && myNodeParent.getParentRelationship() == NodeRelationship.isLeftSon) {

                this.leftRotation(myNodeParent);
                aNode = (BinarySearchTreeNodeRB<Key, Value>) aNode.getLeftSon();
            }
            else if (aNode.getParentRelationship() == NodeRelationship.isLeftSon && myNodeParent.getParentRelationship() == NodeRelationship.isRightSon) {

                this.rightRotation(myNodeParent);
                aNode = (BinarySearchTreeNodeRB<Key, Value>) aNode.getRightSon();
            }
            else {

                myNodeParent.setNodeColor(NodeColor.Black);
                myNodeGrandParent.setNodeColor(NodeColor.Red);

                if (aNode.getParentRelationship() == NodeRelationship.isLeftSon && myNodeParent.getParentRelationship() == NodeRelationship.isLeftSon)
                    this.rightRotation(myNodeGrandParent);
                else
                    this.leftRotation(myNodeGrandParent);
                return;
            }
        }
    }
}