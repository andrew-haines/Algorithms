A collection of sorting algorithms and datastructures in java

Below is the output of a single run on a macbook pro so that performance characteristics can be compared. 

BubbleSort			- On 100000 random integer values: 52368ms
InsertionSort			- On 100000 random integer values: 16374ms
HeapSort			- On 1000000 random integer values: 829ms
QuickSort			- On 1000000 random integer values: 354ms
JavaSorter			- On 1000000 random integer values: 309ms
MergeSort			- On 1000000 random integer values: 175ms
RadixSort (binary base4)	- On 1000000 random integer values: 972ms	# Note that these Radix Sorts require large amounts of temporary memory. As you increase the base, the more memory is required.
RadixSort (binary base8)	- On 1000000 random integer values: 477ms
RadixSort (binary base16)	- On 1000000 random integer values: 192ms
RadixSort (binary base24)	- On 1000000 random integer values: 107ms
RadixSort (decimal base100)	- On 1000000 random integer values: 721ms	# Note that these implementation keep the radix calculations in decimal meaning that there is a much greater constant operating cost converting to decimal
RadixSort (decimal base1000)	- On 1000000 random integer values: 229ms
RadixSort (decimal base10000)	- On 1000000 random integer values: 233ms
CountingSort			- On 1000000 random integer values: 88ms 	# note that although this is the fastest, it only works for values between 0->1000000. Use the RadixSorts above that dont have this limitation
