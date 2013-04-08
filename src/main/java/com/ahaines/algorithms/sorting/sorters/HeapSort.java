package com.ahaines.algorithms.sorting.sorters;

import java.util.List;

import com.ahaines.algorithms.sorting.Sorter;

/**This algorithm uses a heap, implemented in an array, to provide sorting behavior. Essentially a heap uses a binary tree whereby
 * each child of a parent has a value less then the parent. Using this we can take the root of the tree (array[0]) and always know it'll be the top
 * node with the greatest value. We then swap it with the last element in the heap and reduce the heap size by 1 (essentially we are popping the 
 * element off the heap and using the end of the array that is left to store the values in sorted order). This algorithm runs in O (n lg n).
 * 
 * @author andrewhaines
 *
 * @param <T>
 */
public class HeapSort<T extends Comparable<T>> extends Sorter<T> {

	@Override
	public List<T> sort(List<T> toBeSorted, int startPos, int endPos) {
		buildMaxHeap(toBeSorted);
		int heapSize = toBeSorted.size();
		for (int i = toBeSorted.size()-1; i > 0; i--){
			T tmp = toBeSorted.get(i);
			toBeSorted.set(i, toBeSorted.get(0));
			toBeSorted.set(0, tmp);
			setHeapPos(toBeSorted, 1, --heapSize);
		}
		return toBeSorted;
		
	}
	
	private void buildMaxHeap(List<T> toBeSorted) {
		for (int i = (int)Math.floor(toBeSorted.size()/2); i > 0; i--){ // down to 1 (we do 0 based index translation later)
			setHeapPos(toBeSorted, i, toBeSorted.size());
		}
	}

	private static int getLeftChildIndex(int i){
		return i<<1; // 2*i
	}
	private static int getRightChildIndex(int i){
		return (i<<1)+1;
	}
	
	private void setHeapPos(List<T> heap, int i, int heapSize){
		int leftPos = HeapSort.getLeftChildIndex(i)-1; 
		int rightPos = HeapSort.getRightChildIndex(i)-1;
		int idxOfI = i-1;
		int largest;
		
		if (leftPos < heapSize && heap.get(leftPos).compareTo(heap.get(idxOfI)) > 0){
			largest = leftPos;
		}
		else{
			largest = idxOfI;
		}
		if (rightPos < heapSize && heap.get(rightPos).compareTo(heap.get(largest)) > 0){
			largest = rightPos;
		}
		if (largest != idxOfI){
			T tmp = heap.get(idxOfI);
			heap.set(idxOfI, heap.get(largest));
			heap.set(largest, tmp);
			setHeapPos(heap, largest+1, heapSize);
		}
	}

}
