package co.loyyee.ch04StackQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * LIFO <br>
 * Array
 * */
public class StackArray {
	private int max;
	private int top; // we need to offset by 1
	private long[] stackArray;
	
	public StackArray(int max) {
		this.max = max;
		stackArray = new long[max];
		top = -1;
	}
	
	public static void main(String[] args){
		var stack = new StackArray(10);
		stack.push(10);
		stack.push(20);
		stack.print();
		stack.pop();
		stack.print();
	}
	
	public void push(long val) {
		if(isFull()) throw new IllegalArgumentException("Stack is full.");
		stackArray[++top]	= val;
	}
	public long pop() {
		int idx = top --;
		stackArray[idx] = 0;
		return stackArray[idx];
	}
	public long peek() {
		if(isEmpty()) throw new NoSuchElementException("Stack is empty");
		return stackArray[top];
	}
	public boolean isFull() {
		return top == max-1;
	}
	public boolean isEmpty(){
		return top == -1;
	}
	public void print(){
    Arrays.stream(stackArray).forEach(el -> System.out.printf("%d, ", el));
    System.out.println();
	}
}

