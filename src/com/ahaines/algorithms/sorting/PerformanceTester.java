package com.ahaines.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

import com.ahaines.utils.ComparableGenerator;
import com.ahaines.utils.MemoryUtil;

public class PerformanceTester<T extends Comparable<T>> {
	
	private final List<T> array;
	private final List<Sorter<T>> sorters;
	private final ComparableGenerator<T> generator;
	
	private static final int NUMBER_ELEMENTS = 1000000;
	private static final int NUMBER_TESTS = 5; // 5 point average

	public static void main(String[] args) {
		PerformanceTester<Integer> tester = new PerformanceTester<Integer>(args, ComparableGenerator.Comparables.getRandomGenerator(NUMBER_ELEMENTS));
		tester.doSort();
		
	}
	
	PerformanceTester(String[] args, ComparableGenerator<T> generator){
		this.generator = generator;
		array = getArrayToBeSorted(generator.getNumberOfItems());
		sorters = getSorters(args);
	}
	
	protected List<T> getArrayToBeSorted(int numberToBeSorted){
		List<T> array = new ArrayList<T>(numberToBeSorted);
		for (int i = 0;i < numberToBeSorted; i++){
			array.add(generator.getNewObject());
		}
		return array;
	}
	
	private List<Sorter<T>> getSorters(String[] args){
		List<Sorter<T>> sorters = new ArrayList<Sorter<T>>();
		
		for (String arg: args){
			try{
				Class<Sorter<T>> clazz = (Class<Sorter<T>>)Class.forName("com.ahaines.algorithms.sorting.sorters."+arg);
				Sorter<T> sorter = clazz.newInstance();
				sorters.add(sorter);
			}
			catch (ClassNotFoundException e){
				System.out.println("Unable to find class: "+arg);
				e.printStackTrace(System.err);
			} catch (InstantiationException e) {
				System.out.println("Unable to instantiate class: "+arg);
				e.printStackTrace(System.err);
			} catch (IllegalAccessException e) {
				System.out.println("Unable to instantiate class: "+arg);
				e.printStackTrace(System.err);
			}
		}
		return sorters;
	}
	
	private void doSort(){
		System.out.println("Starting Sorter test...");
		for (Sorter<T> sorter: sorters){
			String sorterName = sorter.getClass().getName();
			long averageTime = 0;
			long averageMem = 0;
			for (int i = 0; i < NUMBER_TESTS; i++){
				MemoryUtil.putOutTheGarbage();
				long memUsed = MemoryUtil.getMemoryUse();
				List<T> array = new ArrayList<T>(this.array); // create a copy for that master array is not affected
				long startTime = System.currentTimeMillis();
				array = sorter.sort(array);
				long deltaTime = System.currentTimeMillis() - startTime;
				long deltaMem = MemoryUtil.getMemoryUse() - memUsed;
				averageTime += deltaTime;
				averageMem += deltaMem;
				
				if (!isSorted(array)){
					throw new IllegalStateException("Sorter: "+sorterName+" did not sort the array correctly");
				}
			}
			System.out.println("Runtime of: "+sorterName+" = "+(averageTime/NUMBER_TESTS)+"ms using a memory footprint of: "+(averageMem/NUMBER_TESTS)+" bytes using "+NUMBER_TESTS+" point average");
		}
	}
	
	private boolean isSorted(List<T> array){
		for (int i = 1; i < array.size(); i++){
			if (array.get(i).compareTo(array.get(i-1)) < 0){
				return false; // i-1 is bigger then i
			}
		}
		return true;
	}
	
}
