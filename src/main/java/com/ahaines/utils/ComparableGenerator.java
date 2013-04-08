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
		
		private static final char[] VALID_CHARS = new char[74];
		
		static {
			// add ascii characters
			
			int idx = 0;
			for (char i = 'a'; i <= 'z'; i++){
				VALID_CHARS[idx++] = i;
			}
			
			for (char i = 'A'; i <= 'Z'; i++){
				VALID_CHARS[idx++] = i;
			}
			
			for (char i = '0'; i <= '9'; i++){
				VALID_CHARS[idx++] = i;
			}
			
			for (char i = '!'; i <= ')'; i++){
				VALID_CHARS[idx++] = i;
			}
		}
		
		public static final ComparableGenerator<String> getRandomStringGenerator(final int numberOfItems, final int maxLengthOfString){
			return new ComparableGenerator<String>(){
		
				@Override
				public String getNewObject() {
					
					int currentPos = 0;
					StringBuilder buffer = new StringBuilder();
					
					while (currentPos < maxLengthOfString){
						if (Math.random() < 0.8){
							buffer.append(VALID_CHARS[(int)(Math.random() * VALID_CHARS.length)]);
						}
						currentPos++;
					}
					
					return buffer.toString();
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
