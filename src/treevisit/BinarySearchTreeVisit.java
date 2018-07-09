package treevisit;

import java.util.concurrent.PriorityBlockingQueue;

import dictionary.tree.binarytree.BinarySearchTree;
import dictionary.tree.binarytree.node.BinarySearchTreeNode;

public class BinarySearchTreeVisit {

	/**
	 * 
	 * @param aTree
	 */
	public void BFSBSTVisit(BinarySearchTree aTree) {

		PriorityBlockingQueue<BinarySearchTreeNode> myQueue = new PriorityBlockingQueue<>();
		myQueue.add(aTree.getRootNode());
		
		while(!myQueue.isEmpty()) {
			BinarySearchTreeNode myNode = myQueue.poll();

		
			if(myNode.hasRightSon())
				myQueue.add(myNode.getRightSon());
			if(myNode.hasLeftSon())
				myQueue.add(myNode.getLeftSon());		
		}
	}
}
