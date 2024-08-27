package co.loyyee.ch05LinkedList;

import java.util.Scanner;

/** List Iterator from scratch */
public class ListIter {
  private Node curr;
  private Node prev;
  private LinkedList linkedList;

  public ListIter(LinkedList list) {
    this.linkedList = list;
  }

  public static void main(String[] args) {}

  public void run() {}

  // resetting the current pointer.
  public void reset() {
    curr = linkedList.getFirst();
    prev = null;
  }

  public boolean atEnd() {
    return curr.next == null;
  }

  public void nextLink() {
    prev = curr;
    curr = curr.next;
  }

  public Node getCurr() {
    return curr;
  }

  public void insertAfter(int val) {
    Node newNode = new Node(val);
    if (linkedList.isEmpty()) {
      linkedList.setFirst(newNode);
      curr = newNode;
    } else {
      newNode.next = curr.next; // make the new next pointer points to the old next.
      curr.next = newNode; // move the old next pointer to the new node.
      nextLink(); // move the pointer of the prev and curr
    }
  }

  public void insertBefore(int val) {
    Node newNode = new Node(val);
    if (prev == null) { // beginning of the list
      newNode.next =
          linkedList
              .getFirst(); // set new node as the first by pointing next pointer to current first.
      linkedList.setFirst(newNode); // set the new node into the first position.
      reset();
    } else {
      newNode.next = prev.next; // point the new next pointer to prev next
      prev.next = newNode; // make the prev next as new node
      curr = newNode; // make the curr as the new node,  prev -> curr(new node) -> old prev
    }
  }

  public Node deleteCurr() {
    Node val = curr;
    if (prev == null) { // beginning of the list
      linkedList.setFirst(curr.next); // move the curr pointer towards next
      reset();
    } else {
      prev.next = curr.next;
      if (atEnd()) {
        reset();
      } else {
        curr = curr.next;
      }
    }
    return val;
  }

  static class Node {
    public int data;
    public Node next;
    public Node prev;

    public Node(int val) {
      data = val;
      next = null;
      prev = null;
    }

    @Override
    public String toString() {
      return " { " + data + " } ";
    }
  }

  static class LinkedList {
    private Node first;

    public LinkedList() {
      first = null;
    }

    public static void main(String[] args) {}

    public Node getFirst() {
      return first;
    }

    public void setFirst(Node node) {
      first = node;
    }

    public ListIter getIterator() {
      return new ListIter(this);
    }

    public void append(int val1, double val2) {
      Node newNode = new Node(val1);
      if (first == null) {
        first = newNode;
        return;
      }
      Node curr = first;
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = newNode;
    }

    public void prepend(int val) {
      Node newNode = new Node(val);
      newNode.next = first;
      first = newNode;
    }

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
      if (first.data == val) {
        Node curr = first;
        first = first.next;
        return curr;
      }
      Node curr = first;

      while (curr.next != null) {
        if (curr.next.data == val) {
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
        if (curr.data == val || curr.data == val) {
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
        System.out.print(curr + " ");
        curr = curr.next;
      }
      System.out.println();
    }
  }

  static class IterApp {
    public static void main(String[] args) {
      LinkedList list = new LinkedList();
      ListIter iter = new ListIter(list);
      Node val;

      iter.insertAfter(20);
      iter.insertAfter(40);
      iter.insertAfter(80);
      iter.insertBefore(60);

      boolean cont = true;
      try (Scanner sc = new Scanner(System.in)) {
        while (cont) {
          System.out.println(
              "Enter first letter of (s)how, (r)eset, (n)ext, (g)et, (b)efore, (a)fter, (d)elete");
          String choice = sc.nextLine();
          switch (choice) {
            case "s":
              if (!list.isEmpty()) {
                list.print();
              } else {
                System.out.println("list is empty");
              }
              break;

            case "r":
              iter.reset();
              break;
            case "n":
              if (!list.isEmpty() && !iter.atEnd()) {
                iter.nextLink();
              } else {
                System.out.println("Cant go the next node.");
              }
            case "g":
              if (!list.isEmpty()) {
                val = iter.getCurr();
                System.out.println("Returned: " + val);
              } else {
                System.out.println("list is empty");
              }
              break;
            case "b":
              System.out.print("Enter an integer: ");
              int num1 = Integer.parseInt(sc.nextLine());
              iter.insertBefore(num1);
              break;
            case "a":
              System.out.print("Enter an integer: ");
              int num2 = Integer.parseInt(sc.nextLine());
              iter.insertAfter(num2);
              break;
            case "d":
              if (!list.isEmpty()) {
                val = iter.deleteCurr();
                System.out.println("Deleted " + val);
              } else {
                System.out.println("Unable to delete");
              }
              break;
            default:
              System.out.println("Invalid entry");
          }
          System.out.println("Continue? enter n to stop, any key to continue");
          String willCont = sc.nextLine();
          if(willCont.contains("n")) {
            cont = false;
            break;
          }
        }
      }
    }
  }
}
