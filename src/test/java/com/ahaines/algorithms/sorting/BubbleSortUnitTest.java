package com.ahaines.algorithms.sorting;

import java.util.Arrays;

import com.ahaines.algorithms.sorting.sorters.BubbleSort;
import com.ahaines.utils.ComparableGenerator;

public class BubbleSortUnitTest extends AbstractSorterIntegrationTest<Integer>{

	@Override
	protected ComparableGenerator<Integer> getGenerator() {
		return ComparableGenerator.Comparables.getRandomGenerator(NUMBER_ELEMENTS / 10);
	}

	@Override
	protected Iterable<Sorter<Integer>> getSorters() {
		return Arrays.<Sorter<Integer>>asList(new BubbleSort());
	}

	@Override
	protected int getNumberOfTests() {
		return 1;
	}
}
