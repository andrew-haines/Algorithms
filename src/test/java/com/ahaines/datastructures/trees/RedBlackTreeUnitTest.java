package com.ahaines.datastructures.trees;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ahaines.algorithms.sorting.AbstractSorterIntegrationTest;
import com.ahaines.utils.ComparableGenerator;

public class RedBlackTreeUnitTest {

	@Test
	public void runTest(){
		
		ComparableGenerator<Integer> comparators = ComparableGenerator.Comparables.getRandomGenerator(1000000);
		
		RedBlackBinaryTree<Integer> tree = new RedBlackBinaryTree<Integer>();
		
		for (int i = 0; i < comparators.getNumberOfItems(); i++){
			tree.insertNode(comparators.getNewObject());
		}
		
		assertThat(AbstractSorterIntegrationTest.isSorted(tree), is(equalTo(true)));
	}
}
