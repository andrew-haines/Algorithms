package com.ahaines.algorithms.sorting.sorters;

import java.util.List;

import com.ahaines.algorithms.sorting.Sorter;

public class BubbleSort<T extends Comparable<T>> extends Sorter<T> {
	
	@Override
	public List<T> sort(List<T> toBeSorted, int startPos, int endPos) {
		for (int i = startPos; i < endPos; i++){
			for (int j = endPos-1; j > i; j--){
				int indexBefore = j-1;
				T valueAtJ = toBeSorted.get(j);
				T valueBefore = toBeSorted.get(indexBefore);
				if (valueAtJ.compareTo(valueBefore) < 0){
					toBeSorted.set(j, valueBefore);
					toBeSorted.set(indexBefore, valueAtJ);
				}
			}
		}
		return toBeSorted;
	}

}
