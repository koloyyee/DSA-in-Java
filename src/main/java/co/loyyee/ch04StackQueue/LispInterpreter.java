package co.loyyee.ch04StackQueue;

import java.util.List;
import java.util.Stack;

/**
 * Stack based algorithm <br>
 * Lisp example: ( + 1 2 ) same as ( 1 + 2) <br>
 * The goal for this is to be the basic operation in math
 *
 * reference from http://stdioe.blogspot.com/2012/01/lets-implement-simple-lisp-interpreter.html
 */
public class LispInterpreter {
  // immutable and faster lookup
  private List<Character> operators = List.of('+', '-', '*', '/', '(', ')');
  private Stack<Character> stack;
  private char[] target;
  private String expression;

  public LispInterpreter(String input) {
    this.expression = input;
    this.stack = new Stack<>();
  }

  public static void main(String[] args) {

    var li = new LispInterpreter("(/ 3 2)");
    System.out.println(li.eval());
  }

  public double eval() {
    double result = 0;
    for (char c : this.expression.toCharArray()) {
      this.stack.push(c);
      if (c == ')') {
        char token;
        Stack<Character> callStack = new Stack<>();
        var ignore = this.stack.pop(); /* This is the ) character */
        while ((token = stack.pop()) != '(') {
          if (token != ' ') {
            callStack.push(token);
          }
        }
        result = call(callStack);
      }
    }
    return result;
  }

  public double call(Stack<Character> callStack) {
    char opt = callStack.pop(); // + , - , * , /
    double result = 0;
    switch(opt) {
      case '+' -> {
        while (!callStack.isEmpty()) {
          var val = Character.digit(callStack.pop(), 10);
          result += val;
        }
      }
      case '-' -> {
        while (!callStack.isEmpty()) {
          var val = Character.digit(callStack.pop(), 10);
          result -= val;
        }
      }
      case '*' -> {
        result = 1.0;
        while (!callStack.isEmpty()) {
          var val = Character.digit(callStack.pop(), 10);
          result *= val;
        }
      }
      case '/' -> {
        result = 1.0;
        while (!callStack.isEmpty()) {
          var val = Character.digit(callStack.pop(), 10);
          result /= val;
        }
      }
    }
    return result;
  }

  public boolean checkOpeningClosing() {
    return this.expression.charAt(0) == '('
        && this.expression.charAt(this.expression.length() - 1) == ')';
  }

}
