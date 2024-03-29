package com.ahaines.datastructures.trees;

import java.util.Iterator;

public abstract class AbstractBinaryTree<NodeType extends AbstractBinaryTreeNode<NodeType,T>, T extends Comparable<T>> implements Iterable<T>{
	
	protected NodeType root;
	
	public NodeType insertNode(T valueToInsert){
		NodeType tempParent = getSentinal();
		NodeType x = root;
		boolean isLeftRelationship = false;
		
		while (x != getSentinal()){
			tempParent = x;
			if (valueToInsert.compareTo(x.getValue()) < 0){
				x = x.getLeft();
				isLeftRelationship = true;
			}
			else{
				x = x.getRight();
				isLeftRelationship = false;
			}
		}
		
		NodeType newNode = getNewNode(valueToInsert, tempParent);
		
		if (tempParent == getSentinal()){
			root = newNode; // root does not have a parent
		}	
		else if (isLeftRelationship){
			tempParent.setLeft(newNode);
		}
		else{
			tempParent.setRight(newNode);
		}
		return newNode;
	}
	protected NodeType getSentinal() {
		return null;
	}
	protected abstract NodeType getNewNode(T value, NodeType parent);
	
	private void transplant(NodeType node1, NodeType node2){
		if (node1.getParent() == null){
			root = node2;
		}
		else if (node1 == node1.getParent().getLeft()){ // if node1 is less then the parent
			node1.getParent().setLeft(node2); // set the left child of node1's parent to be node2
		}
		else{
			node1.getParent().setRight(node2); // node1 was on the right hand side of it's parent (therefore greater then it)
		}
		if (node2 != null){
			node2.setParent(node1.getParent()); // we now assign the parent of node2 to node1's old parent. node1 is now not referenced in the tree
		}
	}
	
	public void deleteValue(T valueToDelete){
		NodeType nodeContainingValue = search(root, valueToDelete);
		deleteNode(nodeContainingValue);
	}
	
	public void deleteNode(NodeType valueToDelete){
		if (valueToDelete.getLeft() == null){
			transplant(valueToDelete, valueToDelete.getRight());
		}
		else if (valueToDelete.getRight() == null){
			transplant(valueToDelete, valueToDelete.getLeft());
		}
		else {
			NodeType nextSuccessor = getMinimum(valueToDelete.getRight());
			if (nextSuccessor.getParent() != valueToDelete){ // this node appears somewhere in valueToDelete's subtree and is not a direct child of it
				transplant(nextSuccessor, nextSuccessor.getRight());
				nextSuccessor.setRight(valueToDelete.getRight()); // set successor to have the same right hand tree that valueToDelete had
				nextSuccessor.getRight().setParent(nextSuccessor); // set this new right value to reference the next successor value now	
			}
			transplant(valueToDelete, nextSuccessor);
			nextSuccessor.setLeft(valueToDelete.getLeft()); // do with the left branch what we did with the right branch above
			nextSuccessor.getLeft().setParent(nextSuccessor);
		}
	}
	
	public NodeType search(NodeType nodeToSearchFrom, T valueToSearch){
		//if (nodeToSearchFrom == null || valueToSearch.equals(nodeToSearchFrom.getValue())){
		//	return nodeToSearchFrom;
		//}
		//if (valueToSearch.compareTo(nodeToSearchFrom.getValue()) < 0){
		//	return search(nodeToSearchFrom.getLeft(), valueToSearch);
		//}
		//else{
		//	return search(nodeToSearchFrom.getRight(), valueToSearch);
		//}
		
		while (nodeToSearchFrom != null && !valueToSearch.equals(nodeToSearchFrom.getValue())){
			if (valueToSearch.compareTo(nodeToSearchFrom.getValue()) < 0){
				nodeToSearchFrom = nodeToSearchFrom.getLeft();
			}
			else{
				nodeToSearchFrom = nodeToSearchFrom.getRight();
			}
		}
		return nodeToSearchFrom;
	}
	
	/**
	 * O(log n) or O(h) - where h is height. This is a best case. Worst case is O(n) where by the elements are added in sorted order
	 * (either ascending or descending) and therefore always go down one particular branch (so that the height of the tree is equal to n).
	 * @param nodeToStartFrom
	 * @return
	 */
	public NodeType getMinimum(NodeType nodeToStartFrom){
		while(nodeToStartFrom != null && nodeToStartFrom.getLeft() != getSentinal()){
			nodeToStartFrom = nodeToStartFrom.getLeft();
		}
		return nodeToStartFrom;
	}
	
	public NodeType getSuccessor(NodeType node){
		if (node.getRight() != getSentinal()){
			return getMinimum(node.getRight()); // look at the next minumum for the right (greater then) child
		}
		
		NodeType tempParent = node.getParent();
		while (tempParent != getSentinal() && node == tempParent.getRight()){ // keep looking up at the parent until the node we are transversing up is no longer on the right (ie no longer greater then). This means that this parent has to be greater then this node and thus the next successor
			node = tempParent;
			tempParent = node.getParent();
		}
		return tempParent;
	}

	@Override
	public String toString() {
		NodeType minVal = getMinimum(root);
		StringBuilder builder = new StringBuilder();
		while (minVal != null){
			builder.append(minVal.getValue());
			builder.append(",");
			minVal = getSuccessor(minVal);
		}
		return builder.toString();
	}
	@Override
	public Iterator<T> iterator() {
		
		return new Iterator<T>(){
			
			NodeType next = getMinimum(root);
			
			@Override
			public boolean hasNext() {
				return next != getSentinal();
			}

			@Override
			public T next() {
				T val = next.getValue();
				next = getSuccessor(next);
				
				return val;
			}

			@Override
			public void remove() {
				deleteNode(next);
			}
			
		};
	}

	
}
