package com.ahaines.algorithms.sorting;

import java.util.Arrays;

import com.ahaines.algorithms.sorting.sorters.CountingSort;
import com.ahaines.utils.ComparableGenerator;

public class CountingSortUnitTest extends AbstractSorterIntegrationTest<Integer>{

	@Override
	protected ComparableGenerator<Integer> getGenerator() {
		return ComparableGenerator.Comparables.getRandomGenerator(NUMBER_ELEMENTS);
	}

	@Override
	protected Iterable<Sorter<Integer>> getSorters() {
		return Arrays.<Sorter<Integer>>asList(new CountingSort());
	}

}
