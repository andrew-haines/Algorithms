package com.ahaines.algorithms.sorting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ahaines.utils.ComparableGenerator;
import com.ahaines.utils.MemoryUtil;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

public abstract class AbstractSorterIntegrationTest<T extends Comparable<T>> {
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractSorterIntegrationTest.class);
	
	private List<T> array;
	private Iterable<Sorter<T>> sorters;
	private ComparableGenerator<T> generator;
	
	protected static final int NUMBER_ELEMENTS = 1000000;
	private static final int NUMBER_TESTS = 5; // 5 point average
	
	@Before
	public void before(){
		this.generator = getGenerator();
		array = getArrayToBeSorted(generator.getNumberOfItems());
		sorters = getSorters();
	}
	
	protected abstract ComparableGenerator<T> getGenerator();

	private List<T> getArrayToBeSorted(int numberToBeSorted){
		List<T> array = new ArrayList<T>(numberToBeSorted);
		for (int i = 0;i < numberToBeSorted; i++){
			array.add(generator.getNewObject());
		}
		return array;
	}
	
	protected abstract Iterable<Sorter<T>> getSorters();
	
	@Test
	public void testSorter(){
		LOG.info("Starting Sorter test for "+this.getClass().getSimpleName()+"...");
		for (Sorter<T> sorter: sorters){
			String sorterName = sorter.toString();
			long averageTime = 0;
			long averageMem = 0;
			for (int i = 0; i < getNumberOfTests(); i++){
				MemoryUtil.putOutTheGarbage();
				long memUsed = MemoryUtil.getMemoryUse();
				Iterable<T> array = new ArrayList<T>(this.array); // create a copy for that master array is not affected
				long startTime = System.currentTimeMillis();
				array = sorter.sort(array);
				long deltaTime = System.currentTimeMillis() - startTime;
				long deltaMem = MemoryUtil.getMemoryUse() - memUsed;
				averageTime += deltaTime;
				averageMem += deltaMem;
				
				assertThat("sorter: "+sorterName+" failed to sort correctly", isSorted(array), is(equalTo(true)));
			}
			LOG.info("Runtime of: "+sorterName+" = "+(averageTime/getNumberOfTests())+"ms using a memory footprint of: "+(averageMem/getNumberOfTests())+" bytes using "+getNumberOfTests()+" point average");
		}
	}
	
	protected int getNumberOfTests() {
		return NUMBER_TESTS;
	}

	public static <T extends Comparable<T>> boolean isSorted(Iterable<T> array){
		Iterator<T> it = array.iterator();
		if (it.hasNext()){
			
			T previous = it.next();
			
			while(it.hasNext()){
				T next = it.next();
				if (next.compareTo(previous) < 0){
					return false;
				}
				previous = next;
			}
		}
		
		return true;
	}
	
}
