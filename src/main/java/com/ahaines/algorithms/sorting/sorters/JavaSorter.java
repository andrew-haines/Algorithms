package com.ahaines.algorithms.sorting.sorters;

import java.util.Collections;
import java.util.List;

import com.ahaines.algorithms.sorting.Sorter;

public class JavaSorter<T extends Comparable<T>> extends Sorter<T> {

	@Override
	public List<T> sort(List<T> toBeSorted, int startPos, int endPos) {
		Collections.sort(toBeSorted);
		return toBeSorted;
	}

}
