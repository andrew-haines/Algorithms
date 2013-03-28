package com.ahaines.algorithms.sorting.sorters;

import java.util.Arrays;
import java.util.List;

import com.ahaines.algorithms.sorting.Sorter;

/**This sorting algorithm runs in linear time, that is O(n) due to the fact that no comparisons are ever used. Essentially the algorithm
 * works by creating buckets (the counting array) for each possible value that a particular input idx could represent. In each bucket we count the number of times
 * we have observed this particular value. We then create a running total throughout the counting array to show that not only for a given bucket do
 * we have the number of occurances of it but also the number of values we have seen that have a lower value then the bucket we are looking at
 * (This is because the indexes previously are of a lower value and therefore, as we are viewing a running total, is the number less then the
 * bucket we are looking at). Then, if we know the number of elements smaller in size, we can use this number as our actual position in a sorted
 * array.
 * 
 * There are a few draw backs with this approach however, firstly it can only work on numerical values (to make it work on other objects, a numerical
 * conversion would need to be performed to actuately represent this), requires some knoweldge of the input set (ie the range each input can take). Typically
 * we could use a larger input array then need be but then this is going to occupy more space and iterations in the process. This brings us to the
 * last issue which is space. Depending on the input range, this can require the use of a large temporary array. If you wanted to sort integer values
 * with no knowledge of their range (ie assume all values (-2^31 - 2^31-1), this will require a temporary array of 2^32 * 32 bits = 16.4Gb irrelevant of the 
 * input length! In this implementation we assume that the input range is between 0->n.length. This algorithm also only behaves best when it's
 * input range is less then or equal to its input size (k <= n)
 * 
 * @author andrewhaines
 *
 */
public class CountingSort extends Sorter<Integer> {
	
	private final int inputRange;
	
	public CountingSort(){
		this(-1);
	}
	public CountingSort(int inputRange){
		this.inputRange = inputRange;
	}

	@Override
	public List<Integer> sort(List<Integer> toBeSorted, int startPos, int endPos) {
		int[] occuranceCount = new int[inputRange == -1? toBeSorted.size():inputRange]; // assume input value is only between 0->toBeSorted.length - this is a big assumption!
		Integer[] sortedArray = new Integer[toBeSorted.size()];
		
		// fill counting array
		
		for (int i = 0; i < occuranceCount.length; i++){
			occuranceCount[i] = 0;
		}
		
		for (int i = 0; i < toBeSorted.size(); i++){
			int val = getValueFromArray(toBeSorted, i);
			int count = occuranceCount[val];
			count++;
			occuranceCount[val] = count;
		}
		for (int i = 1; i < occuranceCount.length; i++){
			occuranceCount[i] = occuranceCount[i] + occuranceCount[i-1];
		}
		
		for (int i = toBeSorted.size()-1; i > -1; i--){
			
			Integer idxValue = getValueFromArray(toBeSorted, i);
			int idx = occuranceCount[idxValue]-1;
			sortedArray[idx] = toBeSorted.get(i);
			occuranceCount[idxValue] = occuranceCount[idxValue]-1;
		}
		return Arrays.asList(sortedArray);
	}
	
	protected int getValueFromArray(List<Integer> toBeSorted, int i){
		return toBeSorted.get(i);
	}

}
