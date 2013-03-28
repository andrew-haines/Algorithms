package com.ahaines.algorithms.sorting.sorters;

import java.util.List;

import com.ahaines.algorithms.sorting.Sorter;
/**
 * Divide and conquer algorithm that works by defining a pivot (at random) and then sorting the array into 2 partitions, 1 with all
 * elements less then the pivot's value and the other greater then. This algorithm, on average runs in O(n lg n) with a worst case of O(n^2)
 * -when the array is already sorted. It is normally quicker then {@link HeapSort} {@link MergeSort} (and other DC algorithms) but only 
 * if the input does not contain equal values. The more equal values it has, the more empty partitions it has to deal with. This 
 * algorithm also allows for in place sorting requiring no further memory space then has already been allocated.
 * @author andrewhaines
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> extends Sorter<T> {

	@Override
	public List<T> sort(List<T> toBeSorted, int startPos, int endPos) {
		if (startPos < endPos){
			int pivot = randomPartition(toBeSorted, startPos, endPos);
			sort(toBeSorted, startPos, pivot);
			sort(toBeSorted, pivot+1, endPos);
		}
		return toBeSorted;
	}

	private int randomPartition(List<T> toBeSorted, int startPos, int endPos) {
		int i = (int)Math.random() * (endPos - startPos) + startPos;
		
		T tmp = toBeSorted.get(i);
		toBeSorted.set(i, toBeSorted.get(endPos-1));
		toBeSorted.set(endPos-1, tmp);
		
		return partition(toBeSorted, startPos, endPos);
	}

	private int partition(List<T> toBeSorted, int startPos, int endPos) {
		int lastIdx = endPos -1;
		T pivot = toBeSorted.get(lastIdx);
		int pivotIdx = startPos-1;
		for (int j = startPos; j< lastIdx; j++){
			if (toBeSorted.get(j).compareTo(pivot) < 0){
				pivotIdx++;
				T tmp = toBeSorted.get(pivotIdx);
				toBeSorted.set(pivotIdx, toBeSorted.get(j));
				toBeSorted.set(j, tmp);
			}
		}
		int idxToSwap = pivotIdx+1;
		toBeSorted.set(lastIdx, toBeSorted.get(idxToSwap));
		toBeSorted.set(idxToSwap, pivot);
		
		return idxToSwap;
	}

}
