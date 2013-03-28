package com.ahaines.utils;

public interface ComparableGenerator<T extends Comparable<T>> {
	
	static class Comparables{
		public static final ComparableGenerator<Integer> getRandomGenerator(final int numberOfItems){
			return new ComparableGenerator<Integer>(){
		
				@Override
				public Integer getNewObject() {
					return new Integer((int)Math.floor(Math.random() * numberOfItems));
				}

				@Override
				public int getNumberOfItems() {
					return numberOfItems;
				}
				
			};
		}
		private static final ComparableGenerator<Integer> FIXED_GENERATOR = new ComparableGenerator<Integer>(){
			
			private int i = 0;
			//private final int[] vals = new int[]{4,1,3,2,16,9,10,14,8,7};
			private final int[] vals = new int[]{2,5,3,0,2,3,0,3};
	
			@Override
			public Integer getNewObject() {
				return vals[i++];
			}

			@Override
			public int getNumberOfItems() {
				return 8;
			}
			
		};
	}

	public T getNewObject();
	
	public int getNumberOfItems();
}
