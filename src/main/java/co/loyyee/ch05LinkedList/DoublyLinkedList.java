package co.loyyee.ch05LinkedList;

public class DoublyLinkedList {
	
	public Node first;
	public Node last;
	
	public static void main(String[] args){
		var dll = new DoublyLinkedList();
		dll.append(10);
		dll.append(40);
		dll.prepend(20);
		dll.printForward();
		dll.printBackward();
		dll.insertAfter(20, 30);
		dll.printForward();
		dll.printBackward();
		dll.removeByKey(20);
		dll.printForward();
		dll.printBackward();
		
	}
	
	public DoublyLinkedList() {
		first = null;
		last = null;
	}

	public void prepend(int data) {
		Node newNode = new Node(data);
		if(isEmpty()){
			last = newNode;
		 } else {
			first.prev = newNode;
		}
		newNode.next = first;
		first = newNode;
	}
	public Node removeFirst() {
		Node val = first;
		if( first.next == null )	{
			last = null;
		} else {
			first.next.prev = null;
		}
		first = first.next;
		return val;
	}
	
	public void append(int data) {
		Node newNode = new Node(data);
		if(first == null ) {
			first = newNode;
		} else {
			last.next = newNode;
			newNode.prev = last;
		}
		last = newNode;
	}
	
	public Node removeLast() {
		Node val = last;
		if( first.next != null) {
			first = null;
		} else {
			last.prev.next = null;
		}
		last = last.prev;
		return val;
	}
	public boolean insertAfter(int key, int val)	 {
		Node curr = first;
		
		while(curr.data != key) { // traversing the linked list.
			curr = curr.next;
			if( curr == null ) {
				return false;
			}
		}
		Node newNode = new Node(val);
		if ( curr == last) { // append
			newNode.next = null;
			last = newNode;
		} else { // inserting
			newNode.next = curr.next;
			curr.next.prev = newNode;
		}
		newNode.prev = curr;
		curr.next = newNode;
		return true;
	}
	public Node removeByKey(int key){
		Node curr = first;
		while(curr.data != key) { // traversing
			curr = curr.next;
			if(curr == null )	 {
				return null;
			}
		}
		if(curr == first){
			first = curr.next; // moved the pointer
		} else {
			curr.prev.next = curr.next;  // old prev becomes old next
		}
		if( curr == last ) {
			last = curr.prev; // old prev is the last
		} else {
			curr.next.prev = curr.prev; // old prev becomes old next
		}
		return curr;
	}
	public boolean isEmpty() {
		return first == null;
	}
	
	public void printForward() {
		System.out.print("first -> last: ");
		Node curr = first;
		while (curr != null) {
      System.out.print(curr + " ");
			curr = curr.next;
		}
    System.out.println();
	}
	public void printBackward() {
    System.out.print("last -> first: ");
		Node curr = last;
		while (curr != null) {
			System.out.print(curr + " ");
			curr = curr.prev;
		}
		System.out.println();
	}
	
	
	class Node {
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
//			return "{ "	+ data + " -> " + next + " } ";
			return "{ "	+ data + " } ";
		}
		
	}
}
