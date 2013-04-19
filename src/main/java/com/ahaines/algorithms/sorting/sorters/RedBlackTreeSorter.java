package com.ahaines.algorithms.sorting.sorters;

import java.util.List;

import com.ahaines.algorithms.sorting.Sorter;
import com.ahaines.datastructures.trees.RedBlackBinaryTree;

public class RedBlackTreeSorter<T extends Comparable<T>> extends Sorter<T>{

	@Override
	public Iterable<T> sort(List<T> toBeSorted, int startPos, int endPos) {
		RedBlackBinaryTree<T> tree = new RedBlackBinaryTree<T>();
		
		for (T item: toBeSorted){
			tree.insertNode(item);
		}
		return tree;
	}

}
