package co.loyyee.ch04StackQueue;

import java.util.Scanner;
import java.util.Stack;

/**
 * Arithmetic Expression Parsing from Infix to Postfix <hr> infix: 1 + 2 <br>
 * postfix: 1 2 + <br>
 * prefix: + 1 2 <br>
 */
public class InToPostfix {
  private String input;
  private Stack stack;
  private IntStack intStack;
  private String output = "";

  public InToPostfix(String in) {
    input = in;
    int size = input.length();
    stack = new Stack(size);
  }

  // try with: A*(B+C)-D/(E+F)
  public static void main(String[] args) {

    try (var sc = new Scanner(System.in)) {
      boolean cont = true;
      while (cont) {
        System.out.println("Enter expression in infix: ");
        String input = sc.nextLine();
        if (input.isBlank()) {
          cont = false;
          break;
        }

        var inToPost = new InToPostfix(input);
        String output = inToPost.doTrans();
        System.out.println("Postfix is " + output);
        int result = inToPost.doParse();
        System.out.println("The result: " + result + "\n");
        System.out.println("Continue?");
        if (sc.nextLine().contains("y")) {
          cont = true;
        } else {
          System.out.println("Bye");
          cont = false;
          break;
        }
      }
    }
  }

  public String doTrans() {
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      stack.display("For " + c); // logging
      switch (c) {
        case '+':
        case '-':
          gotOper(c, 1);
          break;
        case '*':
        case '/':
          gotOper(c, 2);
          break;
        case '(':
          stack.push(c);
          break;
        case ')':
          gotParen(c);
          break;
        default:
          output = output + c;
          break;
      }
    }
    while (!stack.isEmpty()) {
      stack.display("While "); // logging
      output = output + stack.pop();
    }
    stack.display("End ");
    return output;
  }

  // prec1: 1 will be + -, 2 will be * /
  // gotOper only handle operands
  private void gotOper(char currOp, int prec1) {
    while (!stack.isEmpty()) {
      char opTop = stack.pop();
      if (opTop == '(') {
        stack.push(opTop); // restore (
        break;
      } else { // it is an operator
        int prec2; // precedence of new op
        if (opTop == '+' || opTop == '-') { // find new op prec
          prec2 = 1;
        } else {
          prec2 = 2;
        }
        if (prec2 < prec1) { // if prec of new op less than prec of old
          stack.push(opTop); // save newly-popped op
          break;
        } else { // prec of new not less than prec of old
          output = output + opTop; //
        }
      }
    }
    stack.push(currOp);
  }

  private void gotParen(char c) {
    while (!stack.isEmpty()) {
      char ch = stack.pop();
      if (ch == '(') {
        break;
      } else {
        output = output + ch;
      }
    }
  }

  public int doParse() {
    intStack = new IntStack(20);
    char c;
    int i;
    int num1, num2, interAns;

    for (i = 0; i < output.length(); i++) {
      c = output.charAt(i);
      intStack.display(" " + c + " ");
      if (c >= '0' && c <= '9') {
        intStack.push((int) c - '0');
      } else {
        num2 = intStack.pop();
        num1 = intStack.pop();
        interAns =
            switch (c) {
              case '+' -> num1 + num2;
              case '-' -> num1 - num2;
              case '*' -> num1 * num2;
              case '/' -> num1 / num2;
              default -> 0;
            };
        intStack.push(interAns);
      }
    }

    interAns = intStack.pop();
    return interAns;
  }

  class IntStack {
    private int max;
    private int top;
    private int[] stack;

    public IntStack(int max) {
      this.max = max;
      top = -1;
      stack = new int[max];
    }

    public void push(int c) {
      stack[++top] = c;
    }

    public int pop() {
      int idx = top--;
      int c = stack[idx];
      stack[idx] = 0;
      return c;
    }

    public int peek(int idx) {
      return stack[idx];
    }

    public int size() {
      return top + 1;
    }

    public boolean isFull() {
      return top == max - 1;
    }

    public boolean isEmpty() {
      return top == -1;
    }

    public void display(String s) {
      System.out.print(s);
      System.out.print(" Stack (bottom -> top): ");

      for (int i = 0; i < size(); i++) {
        System.out.print(peek(i));
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  class Stack {
    private int max;
    private int top;
    private char[] stack;

    public Stack(int max) {
      this.max = max;
      top = -1;
      stack = new char[max];
    }

    public void push(char c) {
      stack[++top] = c;
    }

    public char pop() {
      int idx = top--;
      char c = stack[idx];
      stack[idx] = 0;
      return c;
    }

    public char peek(int idx) {
      return stack[idx];
    }

    public int size() {
      return top + 1;
    }

    public boolean isFull() {
      return top == max - 1;
    }

    public boolean isEmpty() {
      return top == -1;
    }

    public void display(String s) {
      System.out.print(s);
      System.out.print(" Stack (bottom -> top): ");

      for (int i = 0; i < size(); i++) {
        System.out.print(peek(i));
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}
