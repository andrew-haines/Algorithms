package com.ahaines.datastructures.trees;

import com.ahaines.datastructures.trees.RedBlackBinaryTreeNode.Colour;

public class RedBlackBinaryTree<T extends Comparable<T>> extends AbstractBinaryTree<RedBlackBinaryTreeNode<T>,T>{
	
	private final RedBlackBinaryTreeNode<T>SENTINAL = new RedBlackBinaryTreeNode<T>(null, Colour.BLACK, null, null);

	public RedBlackBinaryTree(){
		super();
		root = SENTINAL;
	}
	
	private void leftRotate(RedBlackBinaryTreeNode<T> pivot){
		RedBlackBinaryTreeNode<T> rightNode = pivot.getRight();
		
		//following sets pivot to point to rightNode's left child (as a right child) and sets this node to point to pivot as the parent
		pivot.setRight(rightNode.getLeft());
		
		if (rightNode.getLeft() != SENTINAL && rightNode.getLeft() != null){
			rightNode.getLeft().setParent(pivot);
		}
		// following makes the rightNode point to pivot's old parent (eradixssentially swapping places with it)
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
	
	private void rightRotate(RedBlackBinaryTreeNode<T> pivot){
		RedBlackBinaryTreeNode<T> leftNode = pivot.getLeft();
		
		pivot.setLeft(leftNode.getRight());
		
		if (leftNode.getRight() != SENTINAL && leftNode.getRight() != null){
			leftNode.getRight().setParent(pivot);
		}
		
		leftNode.setParent(pivot.getParent());
		
		if (pivot.getParent() == SENTINAL){
			root = leftNode;
		} 
		else if (pivot == pivot.getParent().getRight()){
			pivot.getParent().setRight(leftNode);
		}
		else {
			pivot.getParent().setLeft(leftNode);
		}
		leftNode.setRight(pivot);
		pivot.setParent(leftNode);
	}

	@Override
	protected RedBlackBinaryTreeNode<T> getNewNode(T value, RedBlackBinaryTreeNode<T> parent) { // returns a default black node
		return new RedBlackBinaryTreeNode<T>(value, RedBlackBinaryTreeNode.Colour.BLACK,parent, SENTINAL);
	}

	@Override
	public RedBlackBinaryTreeNode<T> insertNode(T valueToInsert) {
		RedBlackBinaryTreeNode<T> newNode = super.insertNode(valueToInsert);
		
		newNode.setLeft(SENTINAL);
		newNode.setRight(SENTINAL);
		newNode.setColour(Colour.RED);
		reorder(newNode);
		return newNode;
		
	}

	@Override
	protected RedBlackBinaryTreeNode<T> getSentinal() {
		return SENTINAL;
	}

	private void reorder(RedBlackBinaryTreeNode<T> newNode) {
		while(newNode.getParent().getColour() == Colour.RED){
			RedBlackBinaryTreeNode<T> grandParent = newNode.getParent().getParent();
			if (newNode.getParent() == grandParent.getLeft()){
				RedBlackBinaryTreeNode<T> y = grandParent.getRight();
				
				if (y.getColour() == Colour.RED){
					newNode.getParent().setColour(Colour.BLACK);
					y.setColour(Colour.BLACK);
					grandParent.setColour(Colour.RED);
					newNode = grandParent;
				}
				else if (newNode == newNode.getParent().getRight()){
					newNode = newNode.getParent();
					leftRotate(newNode);
				} else {
					newNode.getParent().setColour(Colour.BLACK);
					grandParent.setColour(Colour.RED);
					rightRotate(grandParent);
				}
				
			}
			else {
				RedBlackBinaryTreeNode<T> y = grandParent.getLeft();
				
				if (y.getColour() == Colour.RED){
					newNode.getParent().setColour(Colour.BLACK);
					y.setColour(Colour.BLACK);
					grandParent.setColour(Colour.RED);
					newNode = grandParent;
				}
				else if (newNode == newNode.getParent().getLeft()){
					newNode = newNode.getParent();
					leftRotate(newNode);
				} else {
					newNode.getParent().setColour(Colour.BLACK);
					grandParent.setColour(Colour.RED);
					rightRotate(grandParent);
				}
			}
		}
		root.setColour(Colour.BLACK);
	}
	
}
