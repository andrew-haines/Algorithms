package com.ahaines.algorithms.sorting;

import java.util.Arrays;

import org.junit.Test;

import com.ahaines.algorithms.sorting.sorters.RadixSort;
import com.ahaines.algorithms.sorting.sorters.RadixSort.RadixKeyExtractor.Factory;
import com.ahaines.utils.ComparableGenerator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

public class RadixSortUnitTest extends AbstractSorterIntegrationTest<Integer>{
	
	private static final int TEST_NUMBER = 1234567890;
	private static final int TEST_BINARY_NUMBER = 0xBCFE1BB;

	@Test
	public void given1234567890_whenGetRadixKeyWithFirstRadix_then90Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 1, 2), is(equalTo(90)));
	}
	
	@Test
	public void given1234567890_whenGetRadixKeyWithSecondRadix_then78Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 2, 2), is(equalTo(78)));
	}
	
	@Test
	public void given1234567890_whenGetRadixKeyWithThirdRadix_then56Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 3, 2), is(equalTo(56)));
	}
	
	@Test
	public void given1234567890_whenGetRadixKeyWithFourthRadix_then34Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 4, 2), is(equalTo(34)));
	}
	
	@Test
	public void given1234567890_whenGetRadixKeyWithFifthRadix_then12Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 5, 2), is(equalTo(12)));
	}
	
	@Test
	public void given1234567890_whenGetRadixKeyWithSixthRadix_then0Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 6, 2), is(equalTo(0)));
	}
	
	@Test
	public void given1234567890_with3DigitRadixKeys_whenGetRadixKeyWithFirstRadix_then890Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 1, 3), is(equalTo(890)));
	}
	
	@Test
	public void given1234567890_with3DigitRadixKeys_whenGetRadixKeyWithSecondRadix_then567Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 2, 3), is(equalTo(567)));
	}
	
	@Test
	public void given1234567890_with3DigitRadixKeys_whenGetRadixKeyWithThirdRadix_then234Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 3, 3), is(equalTo(234)));
	}
	
	@Test
	public void given1234567890_with3DigitRadixKeys_whenGetRadixKeyWithFourthRadix_then1Returned(){
		assertThat(RadixSort.getBase10RadixKey(TEST_NUMBER, 4, 3), is(equalTo(1)));
	}
	
	@Test
	public void given0x8BCFE1BB_with3DigitBinaryRadixKeys_whenGetRadixKeyWithFirstRadix_then3Returned(){
		assertThat(RadixSort.getBinaryRadixKey(TEST_BINARY_NUMBER, 1, 3), is(equalTo(3)));
	}
	
	@Test
	public void given0x8BCFE1BB_with3DigitBinaryRadixKeys_whenGetRadixKeyWithSecondRadix_then7Returned(){
		assertThat(RadixSort.getBinaryRadixKey(TEST_BINARY_NUMBER, 2, 3), is(equalTo(7)));
	}
	
	@Override
	protected ComparableGenerator<Integer> getGenerator() {
		return ComparableGenerator.Comparables.getRandomGenerator(NUMBER_ELEMENTS);
	}

	@Override
	protected Iterable<Sorter<Integer>> getSorters() {
		return Arrays.<Sorter<Integer>>asList(new RadixSort(Factory.getBinaryRadixKeyExtractor(4)), 
											  new RadixSort(Factory.getBinaryRadixKeyExtractor(8)),
											  new RadixSort(Factory.getBinaryRadixKeyExtractor(16)), 
											  new RadixSort(Factory.getBase10RadixKeyExtractor(2)), 
											  new RadixSort(Factory.getBase10RadixKeyExtractor(3)), 
											  new RadixSort(Factory.getBase10RadixKeyExtractor(4)), 
											  new RadixSort(Factory.getBase10RadixKeyExtractor(5)));
	}

}
