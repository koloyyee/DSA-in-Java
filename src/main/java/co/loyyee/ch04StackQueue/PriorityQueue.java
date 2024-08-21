package co.loyyee.ch04StackQueue;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class PriorityQueue {
	private int max;
	private long[] queue;
	private int n;
	
	// there are no front and rear index is because we will order it ourselves
	
	public PriorityQueue(int max) {
		this.max = max;
		queue = new long[max];
		n = 0;
	}
	public static void main(String[] args){
		PriorityQueue pq = new PriorityQueue(10);
		IntStream.range(0,10).forEach(( ignored) -> {
			pq.insert(ThreadLocalRandom.current().nextLong(0,100));
			pq.print();
		});
		while(!pq.isEmpty()) {
			pq.remove();
			pq.print();
		}
	}
	
	
	// insert position based on the value
	public void insert(long val){
		int j;
		if (n == 0) {
			queue[n++] = val;
		} else {
			for(j = n -1 ; j >= 0; j--) {
				if(val > queue[j]) {
//					print();
					queue[j+1] = queue[j]; // shift upward
				} else {
					break;
				}
			}
			queue[j+1] = val;
			n++;
		}
	}
	
	public long remove() {
		int idx = --n;
		long result = queue[idx];
		queue[idx] = 0;
		return result;
	}
	public long peek() {
		return queue[n-1];
	}
	public boolean isEmpty() { return n == 0;}
	public boolean isFull() { return n == max;}
	private void print() {
		Arrays.stream(queue).forEach(el -> System.out.print(el + ", "));
		System.out.println();
	}
}
