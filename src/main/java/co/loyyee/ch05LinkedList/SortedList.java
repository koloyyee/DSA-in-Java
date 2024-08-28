package co.loyyee.ch05LinkedList;

public class SortedList {

  private Node first;

  public SortedList() {
    first = null;
  }

  public static void main(String[] args) {
    SortedList ll = new SortedList();
    ll.insert(10);
    ll.insert(20);
    ll.insert(15);
    ll.print();
  }

  public void insert(int val){
    Node newNode = new Node(val);
    Node curr = first ;
    Node prev = null ;
    while(curr != null && val > curr.iData) {
      prev = curr;
      curr = curr.next;
    }
    if(prev == null){
      first = newNode;
    } else {
      prev.next = newNode;
    }
    newNode.next = curr;
  }
//  public void append(int val1) {
//    Node newNode = new Node(val1);
//    if (first == null) {
//      first = newNode;
//      return;
//    }
//    Node curr = first;
//    while (curr.next != null) {
//      curr = curr.next;
//    }
//    curr.next = newNode;
//  }
//
//  public void prepend(int val) {
//    Node newNode = new Node(val);
//    newNode.next = first;
//    first = newNode;
//  }

  public Node removeFirst() {
    Node val = first;
    first = first.next;
    return val;
  }

  public Node removeLast() {
    Node curr = first;
    Node prev = first;
    while (curr.next != null) {
      prev = curr;
      curr = curr.next;
    }
    Node val = curr;
    prev.next = null;
    this.print();
    return val;
  }

  public Node remove(int val) {
    if (first == null) return null;
    if (first.iData == val) {
      Node curr = first;
      first = first.next;
      return curr;
    }
    Node curr = first;

    while (curr.next != null) {
      if (curr.next.iData == val) {
        Node target = curr.next;
        curr.next = curr.next.next;
        return target;
      }
      curr = curr.next;
    }
    return curr;
  }

  public Node find(int val) {
    Node curr = first;
    while (curr != null) {
      if (curr.iData == val ) {
        return curr;
      }
      curr = curr.next;
    }
    return curr;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public boolean hasNext() {
    Node curr = first;
    return curr.next != null;
  }

  public void print() {
    Node curr = first;
    while (curr != null) {
      curr.display();
      curr = curr.next;
    }
    System.out.println();
  }

  class Node {
    public int iData;
    public Node next;

    public Node(int i) {
      iData = i;
      next = null;
    }

    public void display() {
      System.out.print(this + " ");
    }

    public String toString() {
      //			return "{ " + iData + ", " +  dData + ", " + next + " }";
      return "{ " + iData +  " }";
    }
  }
}
