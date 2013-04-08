package com.ahaines.algorithms.sorting;

import java.util.Arrays;

import com.ahaines.algorithms.sorting.sorters.InsertionSort;
import com.ahaines.utils.ComparableGenerator;

public class InsertionSortUnitTest extends AbstractSorterIntegrationTest<Integer>{

	@Override
	protected ComparableGenerator<Integer> getGenerator() {
		return ComparableGenerator.Comparables.getRandomGenerator(NUMBER_ELEMENTS / 10);
	}

	@Override
	protected Iterable<Sorter<Integer>> getSorters() {
		return Arrays.<Sorter<Integer>>asList(new InsertionSort<Integer>());
	}

	@Override
	protected int getNumberOfTests() {
		return 2;
	}
}
