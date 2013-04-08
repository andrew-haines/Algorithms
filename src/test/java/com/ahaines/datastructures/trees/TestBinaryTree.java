package com.ahaines.datastructures.trees;

import com.ahaines.utils.ComparableGenerator;

public class TestBinaryTree {

	public static void main(String[] args){
		
		ComparableGenerator<Integer> comparators = ComparableGenerator.Comparables.getRandomGenerator(10);
		
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		
		for (int i = 0; i < comparators.getNumberOfItems(); i++){
			tree.insertNode(comparators.getNewObject());
		}
		System.out.println(tree);
	}
}
