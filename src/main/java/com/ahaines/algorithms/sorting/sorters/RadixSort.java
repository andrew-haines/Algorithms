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
			extractor.newRadixPos(radixPos);
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
		
		int mostSignificantDigitNum = radixPos*digitsInKey;
		
		int mostSignificantPower = getMostSignificantDigitPower(mostSignificantDigitNum);
		int leastSignificantPower = getLeastSignificantDigitPower(mostSignificantDigitNum, digitsInKey);
		
		return getBase10RadixKeyFromPowers(completeNumber, mostSignificantPower, leastSignificantPower);
	}
	
	private static int getMostSignificantDigitPower(int mostSignificantDigitNum){
		return (int)(Math.pow(10, mostSignificantDigitNum));
	}
	
	private static int getLeastSignificantDigitPower(int mostSignificantDigitNum, int digitsInKey){
		return (int)(Math.pow(10, mostSignificantDigitNum - digitsInKey));
	}
	
	private static int getBase10RadixKeyFromPowers(int completeNumber, int mostSignificantPower, int leastSignificantPower) {
		return (completeNumber % mostSignificantPower) / leastSignificantPower;
	}

	public static int getBinaryRadixKey(int completeNumber, int radixPos, int digitsInKey) {
		
		int mask = getBinaryMaskForRadix(radixPos, digitsInKey);
		int shiftAmount = getBinaryShiftAmountForRadix(radixPos, digitsInKey);
		return getBinaryRadixKeyFromMask(completeNumber, mask, shiftAmount);
	}
	
	private static int getBinaryMaskForRadix(int radixPos, int digitsInKey){
		int mask = 0;
		int offsetPos = radixPos-1;
		mask = (1 << (digitsInKey*offsetPos)+digitsInKey) - 1;
		if (mask == 0){ // we have looped all the way round so subtract an additional 1
			mask--;
		}
		
		return mask;
	}
	
	private static int getBinaryShiftAmountForRadix(int radixPos, int digitsInKey){
		int offsetPos = radixPos-1;
		return offsetPos * digitsInKey;
	}
	
	private static int getBinaryRadixKeyFromMask(int completeNumber, int mask, int shift){
		return (completeNumber & mask) >> shift;
	}
	
	@Override
	public String toString() {
		return super.toString()+" - extractor: "+extractor.toString();
	}

	public static interface RadixKeyExtractor{
		
		int getMaxUniqueValues();
		
		int getRadixIterations();
		
		int getRadixKey(int completeNumber, int radixPos);
		
		void newRadixPos(int radixPos);
		
		public static class Factory{
			
			private Factory(){}
			
			public static RadixKeyExtractor getBase10RadixKeyExtractor(final int digitsInKey){
				return new RadixKeyExtractor(){
					
					private int currentMostSignificantDigit;
					private int currentLeastSignificantDigit;

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
						
						return RadixSort.getBase10RadixKeyFromPowers(completeNumber, currentMostSignificantDigit, currentLeastSignificantDigit);
					}
					
					public String toString(){
						return "base10("+digitsInKey+")";
					}

					@Override
					public void newRadixPos(int radixPos) {
						// recalculate radixPos
						int mostSignificantDigitNum = radixPos*digitsInKey;
						currentMostSignificantDigit = RadixSort.getMostSignificantDigitPower(mostSignificantDigitNum);
						currentLeastSignificantDigit = RadixSort.getLeastSignificantDigitPower(mostSignificantDigitNum, digitsInKey);
					}
					
				};
			}
			
			public static RadixKeyExtractor getBinaryRadixKeyExtractor(final int digitsInKey){
				return new RadixKeyExtractor(){

					private int currentRadixMask;
					private int currentRadixShift;
					
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
						return RadixSort.getBinaryRadixKeyFromMask(completeNumber, currentRadixMask, currentRadixShift);
					}
					
					public String toString(){
						return "binary("+digitsInKey+")";
					}

					@Override
					public void newRadixPos(int radixPos) {
						currentRadixMask = RadixSort.getBinaryMaskForRadix(radixPos, digitsInKey);
						currentRadixShift = RadixSort.getBinaryShiftAmountForRadix(radixPos, digitsInKey);
					}
					
				};
			}
		}
		
	}
}
