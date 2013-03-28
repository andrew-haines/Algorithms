package com.ahaines.datastructures.trees;

public class AbstractBinaryTreeNode<NodeType extends AbstractBinaryTreeNode<NodeType, T>, T extends Comparable<T>> {
	
	private NodeType parent;
	private NodeType left;
	private NodeType right;
	private T value;
	
	public AbstractBinaryTreeNode(T value, NodeType parent){
		this.parent = parent;
		this.value = value;
	}
	
	public NodeType getLeft() {
		return left;
	}

	public void setLeft(NodeType left) {
		this.left = left;
	}

	public NodeType getRight() {
		return right;
	}

	public void setRight(NodeType right) {
		this.right = right;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public NodeType getParent() {
		return parent;
	}

	void setParent(NodeType parent){
		this.parent = parent;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		builder.append(" [L:\n ");
		builder.append(getLeft());
		builder.append("] [R:\n ");
		builder.append(getRight());
		builder.append("]");
		return builder.toString();
	}

}
