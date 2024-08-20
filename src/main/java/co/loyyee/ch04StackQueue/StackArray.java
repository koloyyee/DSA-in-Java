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

class Reversal {
	private String input;
	private char[] letters;
	private int top;
	private int max;
	
	public Reversal(String word){
		this.input = word;
		this.top = -1;
		this.max = 0;
		
	}
	
	public static void main(String[] args){
		boolean cont = true;
		
		try(Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter /exit to quit:");
			while(cont) {
				
        System.out.println("Input a word:");
				String input = sc.nextLine();
        System.out.flush();
				while(input.isEmpty())	{
          System.out.println("Cannot be empty");
					System.out.println("Input a word:");
					input = sc.nextLine();
				}
				if(input.equals("/exit")) {
          System.out.println("Bye!");
					cont = false;
					break;
				}
				Reversal r = new Reversal(input);
				String output = r.reverse();
        System.out.println(output);
			}
		}
	}

	public String reverse() {
		char[] target = input.toCharArray();
		letters = new char[target.length];
		max = target.length;
		for(char c : target) {
			push(c);
		}
		
		var output = "";
		while(!isEmpty()) {
			var c = pop();
			output += c;
		}
		return output;
	}
	
	public long peek(){
		if(isEmpty()) throw new NoSuchElementException("Stack is empty");
		return letters[top];
	}
	
	public void push(char c) {
		if(isFull()) throw new IllegalArgumentException("Stack is full");
		letters[++top] = c;
	}
	public char pop() {
		if(isEmpty()) throw new NoSuchElementException("Stack is empty");
		int idx = top--;
		char c = letters[idx];
		letters[idx] = 0;
		return c;
	}
	
	public boolean isFull() {return top == max -1;}
	public boolean isEmpty() {return top == -1;}
	
	public void print(){
		System.out.println(this.letters.toString());
	}
}
