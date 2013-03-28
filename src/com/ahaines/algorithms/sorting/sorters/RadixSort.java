package com.ahaines.algorithms.sorting.sorters;

import java.util.List;

/** Radix sort works by sorting each digit position in an integer array.
 * 
 * @author andrewhaines
 *
 */
public class RadixSort extends CountingSort {
	
	private int radixPos = 0; // start at least significant digit
	
	public RadixSort(){
		super(100); // each digit will only be between 0->100
	}

	@Override
	public List<Integer> sort(List<Integer> toBeSorted, int startPos, int endPos) {
		radixPos = 1;
		List<Integer> arraySorts = toBeSorted;
		for (int i = 0; i < 5; i++){// use 4 digits here as this is how many digits in the maxiumum number for an integer (2,147,483,648)
			arraySorts = super.sort(arraySorts, startPos, endPos); // assign to next sorted array
			radixPos++;
		}
		return arraySorts;
	}

	@Override
	protected int getValueFromArray(List<Integer> toBeSorted, int i) {
//		int val = toBeSorted.get(i);
//		int mod = val % (int)Math.pow(10, radixPos);
//		mod = mod / ((int)Math.pow(10, radixPos-1));
//		
//		return mod;
		String digits = String.valueOf(toBeSorted.get(i));
		int digit = 0;
		int largestSignificant = digits.length() - radixPos*2;
		if (largestSignificant > -1){
			digit = Integer.parseInt(digits.substring(largestSignificant, largestSignificant+2));
		}
		return digit;
	}
}
