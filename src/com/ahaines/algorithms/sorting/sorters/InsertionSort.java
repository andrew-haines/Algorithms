package com.ahaines.algorithms.sorting.sorters;

import java.util.List;

import com.ahaines.algorithms.sorting.Sorter;

public class InsertionSort<T extends Comparable<T>> extends Sorter<T>{

	@Override
	public List<T> sort(List<T> toBeSorted, int startPos, int endPos) {
		
		for (int i = startPos+1; i < endPos; i++){
			T key = toBeSorted.get(i);
			
			int j = i-1;
			while(j >= startPos && toBeSorted.get(j).compareTo(key) > 0){
				toBeSorted.set(j+1, toBeSorted.get(j)); // shift up
				j--;
			}
			
			toBeSorted.set(j+1, key); 
		}
		return toBeSorted;
	}

}
