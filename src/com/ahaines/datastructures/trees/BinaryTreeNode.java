package com.ahaines.datastructures.trees;

public class BinaryTreeNode<T extends Comparable<T>> extends AbstractBinaryTreeNode<BinaryTreeNode<T>, T> {

	public BinaryTreeNode(T value, BinaryTreeNode parent) {
		super(value, parent);
	}
}
