package com.ahaines.datastructures.trees;

import org.junit.Test;

import com.ahaines.algorithms.sorting.AbstractSorterIntegrationTest;
import com.ahaines.utils.ComparableGenerator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

public class BinaryTreeUnitTest {

	@Test
	public void runTest(){
		
		ComparableGenerator<Integer> comparators = ComparableGenerator.Comparables.getRandomGenerator(10);
		
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		
		for (int i = 0; i < comparators.getNumberOfItems(); i++){
			tree.insertNode(comparators.getNewObject());
		}
		
		assertThat(AbstractSorterIntegrationTest.isSorted(tree), is(equalTo(true)));
	}
}
