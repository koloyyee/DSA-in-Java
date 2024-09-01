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
      newNode.next = current;
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
