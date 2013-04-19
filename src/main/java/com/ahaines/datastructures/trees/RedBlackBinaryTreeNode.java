package com.ahaines.datastructures.trees;

public class RedBlackBinaryTreeNode<T extends Comparable<T>> extends AbstractBinaryTreeNode<RedBlackBinaryTreeNode<T>, T> {

	public static enum Colour{RED,BLACK};
	
	private Colour colour;

	public RedBlackBinaryTreeNode(T value, Colour colour, RedBlackBinaryTreeNode<T> parent, RedBlackBinaryTreeNode<T> sentinal) {
		super(value, parent);
		setColour(colour);
		setLeft(sentinal);
		setRight(sentinal);
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}
	
	
}
