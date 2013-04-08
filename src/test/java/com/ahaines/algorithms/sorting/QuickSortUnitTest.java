package com.ahaines.algorithms.sorting;

import java.util.Arrays;

import com.ahaines.algorithms.sorting.sorters.QuickSort;
import com.ahaines.utils.ComparableGenerator;

public class QuickSortUnitTest extends AbstractSorterIntegrationTest<String>{

	@Override
	protected ComparableGenerator<String> getGenerator() {
		return ComparableGenerator.Comparables.getRandomStringGenerator(NUMBER_ELEMENTS, 5);
	}

	@Override
	protected Iterable<Sorter<String>> getSorters() {
		return Arrays.<Sorter<String>>asList(new QuickSort<String>());
	}

}
