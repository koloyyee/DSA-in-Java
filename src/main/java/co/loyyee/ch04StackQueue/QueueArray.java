package co.loyyee.ch04StackQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * FIFO<br>
 *
 * this is a circular queue example
 * */
public class QueueArray {
	private int max;
	private long[] queue;
	private int front; // front index
	private int rear; // rear index
	private int n;
	
	public QueueArray(int max) {
		this.max = max;
		queue = new long[max];
		front = 0;
		rear = -1;
		n = 0;
	}
	public static void main(String[] args){
		QueueArray q = new QueueArray(10);
		long[]	seed = {10, 20, 40, 9, 5,1 ,8};
		IntStream.range(0,10).forEach((ignored) -> {
			q.insert(ThreadLocalRandom.current().nextLong(0, 1000));
			q.print();
		});
    System.out.println("=======================");
		while(!q.isEmpty()) {
			q.remove();
			q.print();
		}
	}
	
	// always insert from the rear
	public void insert (long value ) {
		
		if(isFull()) throw new NoSuchElementException("Queue is full");
//		if(rear == max - 1) { // wrapped around
//			rear = -1;
//		}
		queue[++rear] = value;
		n++;
	}
	
	// always take from the front
	public long remove() {
		if(isEmpty()) throw new NoSuchElementException("Queue is empty");
		int idx = front++;
		long first = queue[idx];
//		if(front == max) {
//			front = 0;
//		}
		n--;
		queue[idx] = 0; // clean up and change back to 0;
		return first;
	}
	
	public long peekFront() {
		if(isEmpty()) throw new NoSuchElementException("Queue is empty");
		return	queue[front];
	}
	public long peekRear() {
		if(isEmpty()) throw new NoSuchElementException("Queue is empty");
		return	queue[rear];
	}
	
	public boolean isFull() {
		return n == max;
	}
	public boolean isEmpty() {
		return n == 0;
	}
	public void print() {
    Arrays.stream(queue).forEach(el -> System.out.printf("%s, ", el ));
    System.out.println();
	}
}
