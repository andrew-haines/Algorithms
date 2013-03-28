package com.ahaines.datastructures.trees;

import com.ahaines.datastructures.trees.RedBlackBinaryTreeNode.Colour;

public class RedBlackBinaryTree<T extends Comparable<T>> extends AbstractBinaryTree<RedBlackBinaryTreeNode<T>,T>{
	
	private final RedBlackBinaryTreeNode<T>SENTINAL = new RedBlackBinaryTreeNode<T>(null, Colour.BLACK, null);

	private void leftRotate(RedBlackBinaryTreeNode<T> pivot){
		RedBlackBinaryTreeNode<T> rightNode = pivot.getRight();
		
		//following sets pivot to point to rightNode's left child (as a right child) and sets this node to point to pivot as the parent
		pivot.setRight(rightNode.getLeft());
		
		if (rightNode.getLeft() != SENTINAL){
			rightNode.getLeft().setParent(pivot);
		}
		// following makes the rightNode point to pivot's old parent (essentially swapping places with it)
		rightNode.setParent(pivot.getParent());
		if (pivot.getParent() == SENTINAL){
			root = rightNode;
		}
		else if (pivot == pivot.getParent().getLeft()){ // pivot is on the left side of it's parent
			pivot.getParent().setLeft(rightNode);
		}
		else{ // it must be on the right
			pivot.getParent().setRight(rightNode);
		}
		rightNode.setLeft(pivot);
		pivot.setParent(rightNode);
	}

	@Override
	protected RedBlackBinaryTreeNode<T> getNewNode(T value, RedBlackBinaryTreeNode<T> parent) { // returns a default black node
		return new RedBlackBinaryTreeNode<T>(value, RedBlackBinaryTreeNode.Colour.BLACK,parent);
	}

	@Override
	public RedBlackBinaryTreeNode<T> insertNode(T valueToInsert) {
		RedBlackBinaryTreeNode<T> newNode = super.insertNode(valueToInsert);
		
		newNode.setLeft(SENTINAL);
		newNode.setRight(SENTINAL);
		newNode.setColour(Colour.RED);
		
		return newNode;
		
	}
	
}
