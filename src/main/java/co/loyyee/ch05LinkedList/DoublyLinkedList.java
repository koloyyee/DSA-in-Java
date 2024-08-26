package co.loyyee.ch05LinkedList;

public class DoublyLinkedList {
	
	public Node first;
	public Node last;
	
	public DoublyLinkedList() {
		first = null;
		last = null;
	}

	public void append(int data) {
		Node newNode = new Node(data);
		if(first == null ) {
			first = newNode;
			return;
		}
		newNode.next = first;
		first = newNode;
	}
	public Node removeFirst() {
		Node val = first;
		if( first.next == null )	{
			last = null;
		}
		first = first.next;
		return val;
	}
	
	public void prepend(int data) {
		Node newNode = new Node(data);
		if(first == null ) {
			first = newNode;
		} else {
			last.next = newNode;
		}
		last = newNode;
	}
	
	public boolean isEmpty() {
		return first == null && last == null;
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
			return "{ "	+ data + " -> " + next + " } ";
		}
		
	}
}
