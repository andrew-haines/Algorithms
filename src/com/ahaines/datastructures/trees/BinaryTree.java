package com.ahaines.datastructures.trees;

public class BinaryTree<T extends Comparable<T>> extends AbstractBinaryTree<BinaryTreeNode<T>, T> {

	@Override
	protected BinaryTreeNode<T> getNewNode(T value, BinaryTreeNode<T> parent) {
		return new BinaryTreeNode<T>(value, parent);
	}

}
