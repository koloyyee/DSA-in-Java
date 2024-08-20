package co.loyyee.ch04StackQueue;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class DelimiterMatcher {
	private int top;
	private int max;
	private char[] stack;
	private String input;
	
	public DelimiterMatcher(String input){
		this.input = input;
		max = 0;
		top = -1;
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
					var dm = new DelimiterMatcher(input);
					var isValid = dm.check();
					System.out.println("%s is %s.".formatted(dm.input, isValid ? "valid" : "invalid"));
				}
			}
	}
	public boolean check() {
		max = input.toCharArray().length;
		stack = new char[max];
		var chars = input.toCharArray();
		boolean isValid = true;
		
		
		for(int i = 0; i < chars.length; i++ )	 {
			char c = chars[i];
			switch(c) {
				case '{', '[', '(' -> push(c);
				case '}', ']', ')' -> {
						char  leftOpt = pop();
						if(
								c == '}' && leftOpt != '{'  ||
								c == ')' && leftOpt != '(' ||
								c == ']' && leftOpt != '[' ) {
							isValid = false;
              System.out.println("Error: " + c + "at " + i);
					}
				}
			}
		}
		if (!isEmpty()) {
			System.out.println("Missing right delimiter");
			isValid = false;
		}
		return isValid;
	}
	
	public char peek() {
		if(isEmpty()) throw new NoSuchElementException("Stack is empty");
		return stack[top];
	}
	public char pop() {
		if(isEmpty()) throw new NoSuchElementException("Stack is empty");
		int idx = top--;
		char c = stack[idx];
		stack[idx] = 0;
		return c;
	}
	public void push(char opt) {
		if(isFull())throw new IllegalArgumentException("Stack is full");
		stack[++top] = opt;
	}
	public boolean isFull() {return top == max -1;}
	public boolean isEmpty() {return top == -1;}
	public void print() {
    System.out.println(new String(stack));
		}
}
