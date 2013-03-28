package com.ahaines.algorithms.sorting;

import java.util.List;

public abstract class Sorter<T extends Comparable<T>> {

	public abstract List<T> sort(List<T> toBeSorted, int startPos, int endPos);
	
	public List<T> sort(List<T> toBeSorted){
		return sort(toBeSorted, 0, toBeSorted.size());
	}
	public final void swap(List<T> toBeSorted, int index1, int index2){
		T tmp = toBeSorted.get(index1);
		toBeSorted.set(index1, toBeSorted.get(index2));
		toBeSorted.set(index2, tmp);
	}
}
