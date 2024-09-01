package co.loyyee.ch05LinkedList;

import java.util.NoSuchElementException;

public class Projects {
  public static void main(String[] args) {}

  /**
   * 5.1 Implement a priority queue based on a sorted linked list. The remove operation on the
   * priority queue should remove the item with the smallest key.
   */
  static class SortedLinkedList {

    private Node first;

    public SortedLinkedList() {
      first = null;
    }

    public static void main(String[] args) {
      var l = new SortedLinkedList();
      l.insert(10);
      l.insert(5);
      l.insert(15);
      l.print();
      l.remove();
      l.print();
      l.remove();
      l.print();
      l.remove();
      l.print();
      l.remove();
    }

    public void insert(int val) {
      Node newNode = new Node(val);
      Node curr = first;
      Node prev = null;
      while (curr != null && val > curr.data) {
        prev = curr;
        curr = curr.next;
      }
      if (prev == null) {
        first = newNode;
      } else {
        prev.next = newNode;
      }
      newNode.next = curr;
    }

    public Node remove() {
      if (isEmpty()) throw new NoSuchElementException("empty list");
      Node val = first;
      first = first.next;
      return val;
    }

    public boolean isEmpty() {
      return first == null;
    }

    public void print() {
      Node curr = first;
      while (curr != null) {
        System.out.print(curr);
        curr = curr.next;
      }
      System.out.println();
    }

    class Node {
      public int data;
      public Node next;

      public Node(int val) {
        next = null;
        data = val;
      }

      @Override
      public String toString() {
        return "{" + data + '}';
      }
    }
  }
}

/** 5.3 */
class CircularLinkedList {

  private Node current;

  public CircularLinkedList() {
    current = null;
  }

  public static void main(String[] args) {
    var cll = new CircularLinkedList();
    cll.insert(10);
    cll.insert(20);
    //    cll.print();
    cll.removeByVal(10);
    cll.print();
  }

  public void insert(int val) {
    Node newNode = new Node(val);
    if (current == null) {
      current = newNode;
      current.next = current;
    } else {
      newNode.next = current.next;
      current.next = newNode;
      current = newNode;
    }
  }

  public Node find(int val) {
    if (current.data != val) {
      int currData = current.data;
      current = current.next;
      while (current.data != val) {
        if (current.data == currData) {
          return null;
        } else {
          current = current.next;
        }
      }
    }
    return current;
  }

  public Node removeByVal(int val) {
    Node target;
    if (find(val) == null) return null;

    while (current.next.data != val) {
      if (current != null) {
        current = current.next;
      }
    }
    target = current.next;
    if (current == current.next) {
      return null;
    } else {
      current.next = current.next.next;
    }
    return target;
  }

  public void print() {
    int firstData = current.data;
    System.out.print(firstData);
    System.out.println();
    if (current != null) {
      current = current.next;
    }
    while (current.data != firstData) {
      System.out.print(current.data);
      if (current.next != null) {
        current = current.next;
      }
    }
    System.out.println();
  }

  class Node {
    public int data;
    public Node next;

    public Node(int val) {
      data = val;
      next = null;
    }

    public String toString() {
      return "{ " + data + " } ";
    }
  }
}

/** 5.4 */
class Stack {
  private Node top;

  Stack() {
    top = null;
  }

  public static void main(String[] args) {
    var s = new Stack();
    s.push(5);
    s.push(10);
    s.print();
    s.pop();
    System.out.println("---------------");
    s.print();
  }

  void push(int val) {
    Node newNode = new Node(val);
    if (top != null) {
      newNode.next = top;
    }
    top = newNode;
  }

  Node pop() {
    Node val = top;
    top = top.next;
    return val;
  }

  void print() {
    Node curr = top;

    while (curr != null) {
      System.out.println(curr);
      curr = curr.next;
    }
  }

  class Node {
    public int data;
    public Node next;

    public Node(int val) {
      data = val;
      next = null;
    }

    public String toString() {
      return "{ " + data + " } ";
    }
  }
}

/**
 * 5.5<br>
 * Steps: <br>
 * 1. Create an application that uses a circular linked list <br>
 * 2. Inputs are the number of people in the circle, the number used for counting off, and the
 * number of the person where counting starts (usually 1). <br>
 * 3. The output is the list of persons being eliminated.<br>
 * 4. When a person drops out of the circle, counting starts again from the person who was on his
 * left (assuming you go around clockwise). <br>
 * <hr> example: <br>
 * There are seven people numbered 1 through 7, and you start at 1 and count off by threes. People
 * will be eliminated in the order 4, 1, 6, 5, 7, 3. Number 2 will be left.
 */
class JosephusProblem {

  public Node current;
  private int countOff;
  private int capacity;

  public JosephusProblem(int capacity, int countOff) {
    this.countOff = countOff;
    this.capacity = capacity;
    // populate
    for (int i = 1; i < capacity + 1; i++) {
      insert(i);
    }
  }

  public static void main(String[] args) {
    int capacity = 7;
    int countOff = 3;

    var jp = new JosephusProblem(capacity, countOff);
    //    jp.print();
    jp.execute();
  }

  public Node kill() {

    for (int i = 0; i < countOff + 1; i++) {
      step();
    }
    Node person = remove();
    System.out.println("Killed " + person);
    return person;
  }

  public Node execute() {
    Node person = new Node(0);
    while (person != null) {
      person = kill();
      print();
    }
    person = current;
    System.out.println("Last person standing: " + person);
    return person;
  }

  public void step() {
    if (current != null) {
      current = current.next;
    }
  }

  private void insert(int val) {
    Node newNode = new Node(val);
    if (current == null) {
      current = newNode;
      current.next = current;
    } else {
      newNode.next = current.next;
      current.next = newNode;
      current = newNode;
    }
  }

  private Node find(int val) {
    if (current.data != val) {
      int key = current.data;
      while (val != key) {
        if (current.data == key) {
          return null;
        } else {
          current = current.next;
        }
      }
    }
    return current;
  }

  Node remove() {
    return remove(current.data);
  }

  Node remove(int val) {
    if (find(val) == null) {
      return null;
    }
    while (current.next.data != val) {
      if (current != null) {
        current = current.next;
      }
    }
    var target = current.next;
    if (current == current.next) {
      return null;
    } else {
      current.next = current.next.next;
    }
    return target;
  }

  void print() {
    var val = current;
    System.out.print(val);
    if (current != null) {
      current = current.next;
    }
    while (current.data != val.data) {
      System.out.print(current);
      current = current.next;
    }
  }

  class Node {
    public int data;
    public Node next;

    public Node(int val) {
      data = val;
      next = null;
    }

    public String toString() {
      return "{ " + data + " } ";
    }
  }
}

/* *
* TODO:
*
 * 5.6 Let’s try something a little different:
 * a two-dimensional linked list, which we’ll call a matrix.
 * This is the list analogue of a two-dimensional array.
 * It might be useful in applications such as spreadsheet programs.
 * If a spreadsheet is based on an array, and you insert a new row near the top,
 * you must move every cell in the lower rows N*M cells, which is potentially a slow process.
 * If the spreadsheet is implemented by a matrix, you need only change N pointers.
 *
 * For simplicity, we’ll assume a singly linked approach
 * (although a double-linked approach would probably be more appropriate for a spreadsheet).
 * Each link (except those on the top row and left side) is pointed to by the link directly above it
 * and by the link on its left. You can start at the upper-left link and navigate to, say,
 * the link on the third row and fifth column by following the pointers down two rows and right four columns.
 * Assume your matrix is created with specified dimensions (7 by 10, for example).
 * You should be able to insert values in specified links and display the contents of the matrix
 * */
class Matrix {
  
  private int rowSize;
  private int colSize;
  
  private Cell first;
  private Cell prev;
  
  public Matrix(int rowSize, int colSize){
    this.rowSize = rowSize;
    this.colSize = colSize;
  }
  
  public static void main(String[] args){
  
  }

  class Cell {
    public int data;
    public Cell row;
    public Cell col;

    public Cell (int val) {
      data = val;
      row = null;
      col = null;
    }

    public String toString() {
      return " { " + data + " } ";
    }
  }
}
