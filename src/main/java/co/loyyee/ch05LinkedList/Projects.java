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
      while( curr != null && val > curr.data )  {
        prev = curr;
        curr = curr.next;
      }
      if ( prev == null )  {
        first = newNode;
      } else {
        prev.next = newNode;
      }
      newNode.next = curr;
    }
    
    public Node remove() {
      if(isEmpty()) throw new NoSuchElementException("empty list");
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
