package com.ahaines.algorithms.sorting;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class Sorter<T extends Comparable<T>> {

	protected abstract Iterable<T> sort(List<T> toBeSorted, int startPos, int endPos);
	
	public Iterable<T> sort(Iterable<T> toBeSorted){
		
		List<T> toBeSortedList;
		if (toBeSorted instanceof List){
			toBeSortedList = (List<T>)toBeSorted;
		} else{
			toBeSortedList = Lists.newArrayList(toBeSorted);
		}
		
		return sort(toBeSortedList, 0, toBeSortedList.size());
	}
	protected final void swap(List<T> toBeSorted, int index1, int index2){
		T tmp = toBeSorted.get(index1);
		toBeSorted.set(index1, toBeSorted.get(index2));
		toBeSorted.set(index2, tmp);
	}
	
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
