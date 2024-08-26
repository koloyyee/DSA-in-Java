package co.loyyee.ch05LinkedList;

/**
 * Same ADT but implemented in Linked List instead of Array
 * */
public class QueueLinkedList {
	
	private Node front;
	private Node last;
	
	public QueueLinkedList() {
		front = null;
		last = null;
	}
	public static void main(String[] args){
		var q = new QueueLinkedList();
		q.append(10);
		q.append(20);
		q.print();
		q.removeFront();
		q.print();
	}
	/** To the back*/
public void append(int val)	{
	Node newNode = new Node(val);
	if(front == null ) {
		front = newNode;
	} else {
		last.next = newNode;
	}
	last = newNode;
// alternative, if you only have front.
//	if (front == null) {
//		front = newNode;
//		return;
//	}
//	Node curr = front;
//	while ( curr.next != null ) {
//		curr = curr.next;
//	}
//	curr.next = newNode;
}

public Node removeFront() {
	Node val = front;
	if( front.next == null) {
		last = null;
	}
	front = front.next;
	return val;
}
	
	public boolean isEmpty() {
		return front == null;
	}

	public void print() {
		Node curr = front;
		while ( curr != null ) {
      System.out.print(curr);
			curr = curr.next;
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
		
		@Override
		public String toString() {
			return "{ " + data + " } ";
		}
	}
}
