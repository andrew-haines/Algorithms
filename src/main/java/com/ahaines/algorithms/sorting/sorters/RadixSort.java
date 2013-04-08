package com.ahaines.algorithms.sorting.sorters;

import java.util.List;

/** 
 * Radix sort works by sorting each digit position in an integer array.
 * 
 * @author andrewhaines
 *
 */
public class RadixSort extends CountingSort {
	
	private int radixPos = 0; // start at least significant digit
	private final RadixKeyExtractor extractor;
	
	public RadixSort(RadixKeyExtractor extractor){
		super(extractor.getMaxUniqueValues()); // each digit will only be between 0->10^digitsInKey
		this.extractor = extractor;
	}

	@Override
	public List<Integer> sort(List<Integer> toBeSorted, int startPos, int endPos) {
		radixPos = 1;
		List<Integer> arraySorts = toBeSorted;
		int totalIterations = extractor.getRadixIterations();
		for (int i = 0; i < totalIterations; i++){// work out how many times to sort. Each sort will be for a different section of the maximum integer value (2147483647 - 10 digits)
			arraySorts = super.sort(arraySorts, startPos, endPos); // assign to next sorted array
			radixPos++;
		}
		return arraySorts;
	}
// 
	@Override
	protected int getValueFromArray(List<Integer> toBeSorted, int i) {
		
		int completeNumber = toBeSorted.get(i);
		
		// calculate radix key based on the current iteration
		
		return extractor.getRadixKey(completeNumber, radixPos);
	}

	public static int getBase10RadixKey(int completeNumber, int radixPos, int digitsInKey) {
		
		int mostSignificantDigit = radixPos*digitsInKey;
		
		int mostSignificantPower = (int)(Math.pow(10, mostSignificantDigit));
		int leastSignificantPower = (int)(Math.pow(10, mostSignificantDigit - digitsInKey));
		
		return (completeNumber % mostSignificantPower) / leastSignificantPower;
	}

	public static int getBinaryRadixKey(int completeNumber, int radixPos, int digitsInKey) {
		int mask = 0;
		int offsetPos = radixPos-1;
		mask = (1 << (digitsInKey*offsetPos)+digitsInKey) - 1;
		if (mask == 0){ // we have looped all the way round so subtract an additional 1
			mask--;
		}
		return (completeNumber & mask) >> (offsetPos * digitsInKey);
	}
	
	@Override
	public String toString() {
		return super.toString()+" - extractor: "+extractor.getClass().getName();
	}

	public static interface RadixKeyExtractor{
		
		int getMaxUniqueValues();
		
		int getRadixIterations();
		
		int getRadixKey(int completeNumber, int radixPos);
		
		public static class Factory{
			
			private Factory(){}
			
			public static RadixKeyExtractor getBase10RadixKeyExtractor(final int digitsInKey){
				return new RadixKeyExtractor(){

					@Override
					public int getMaxUniqueValues() {
						return (int)Math.pow(10, digitsInKey);
					}

					@Override
					public int getRadixIterations() {
						return 10 / digitsInKey;
					}

					@Override
					public int getRadixKey(int completeNumber, int radixPos) {
						return RadixSort.getBase10RadixKey(completeNumber, radixPos, digitsInKey);
					}
					
				};
			}
			
			public static RadixKeyExtractor getBinaryRadixKeyExtractor(final int digitsInKey){
				return new RadixKeyExtractor(){

					@Override
					public int getMaxUniqueValues() {
						return 1 << digitsInKey;
					}

					@Override
					public int getRadixIterations() {
						return 32 / digitsInKey;
					}

					@Override
					public int getRadixKey(int completeNumber, int radixPos) {
						return RadixSort.getBinaryRadixKey(completeNumber, radixPos, digitsInKey);
					}
					
				};
			}
		}
		
	}
}
