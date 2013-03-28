package com.ahaines.algorithms.sorting.sorters;

import java.util.List;

import com.ahaines.algorithms.sorting.Sorter;

/**This algorithm uses a Divide and conquer technique by halving the array recursively and then merging the 2 back together. Because each
 * split of the array is going to be sorted (due to the recursive nature of this algorithm) we only need to compare the top of each split
 * together. We then pop the top off the split with the greatest element. This, like the other DC algorithms completes in O(n lg n) time but
 * has the disadvantage that it needs to create new temporary arrays for each split so can be quite memory intensive...
 * 
 * @author andrewhaines
 *
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> extends Sorter<T> {

	@Override
	public List<T> sort(List<T> toBeSorted, int startPos, int endPos) {
		if (endPos - startPos > 1){
			int midPoint = (int)Math.ceil((startPos + endPos) /2);
			sort(toBeSorted, startPos, midPoint); // sort left side of split
			sort(toBeSorted, midPoint, endPos); // sort right side of split
			merge(toBeSorted, startPos, midPoint, endPos); // then merge the 2 sides together
		}
		return toBeSorted;
	}

	private void merge(List<T> toBeSorted, int startPos, int midPoint,int endPos) {
		
		List<T> leftPile = toBeSorted.subList(startPos, midPoint); // remember this will already be sorted
		List<T> rightPile = toBeSorted.subList(midPoint, endPos); // remember this will already be sorted
		
		int rightIndex = 0;
		int leftIndex = 0;
		for (int i = startPos; i < endPos; i++){
			boolean toLeftSide = rightIndex == rightPile.size() || (leftIndex < leftPile.size() && leftPile.get(leftIndex).compareTo(rightPile.get(rightIndex)) < 0);
			
			if (toLeftSide){
				toBeSorted.set(i, leftPile.get(leftIndex++));// also increment the leftindex variable after assignment
			}
			else {
				toBeSorted.set(i, rightPile.get(rightIndex++));// also increment the rightindex variable after assignment
			}
		}
	}

}
