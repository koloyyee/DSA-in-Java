package co.loyyee.ch03SimpleSort;

import java.util.Arrays;

/**
 * Bubble Sort - Slow
 * <br>
 * Go through 0 -> N-1 ... N-N
 * <hr>
 *   Big O: O(N^2/M) therefore O(N^2)
 * */
public class BubbleSort {
	private long[] a;
	private int n;
	
	public static void main(String[] args){
		var b = new BubbleSort(5);
		b.insert(10);
		b.insert(5);
		b.insert(1);
		b.insert(6);
		b.insert(9);
		b.sort();
	}
	
	public BubbleSort(int max) {
		a = new long[max]	;
		n = 0;
	}
	
	public void insert (long value) {
		a[n] = value;
		n++;
	}
	
	public void display() {
    Arrays.stream(a).forEach(el -> System.out.print(el + ", "));
    System.out.println("");
	}
	/** The Bubble Sort */
	public void sort() {
		int out, in;
		// outer loop from backward last to first
		for( out = n - 1; out > 1 ; out-- ) {
			// inner loop from first to current max (out)
			for (in = 0; in < out ; in++ ) {
				display();
				if (a[in] > a[in + 1 ]) {
					swap(in , in+1);
				}
			}
		}
	}
	private void swap(int lhs, int rhs) {
		long temp = a[lhs];
		a[lhs] = a[rhs];
		a[rhs] = temp;
	}
}
