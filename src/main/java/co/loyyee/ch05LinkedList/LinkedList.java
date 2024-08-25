package co.loyyee.ch05LinkedList;


/**
 * Basic Linked List
 *
 * In the book it calls Link, here I call it Node
 *
 * */
public class LinkedList {
	private Node first;
	
	public LinkedList() {
		first= null;
	}
	public static void main(String[] args){
		LinkedList ll = new LinkedList();
		ll.append(11, 2.2);
		ll.append(22, 3.3);
		ll.print();
//		ll.prepend(0, 1.1);
//		ll.print();
	}
	public void append( int val1, double val2) {
			Node newNode = new Node(val1, val2);
			if ( first == null)	 {
				first = newNode;
				return;
			}
			Node curr = first;
			while( curr.next != null ) {
				curr = curr.next;
			}
			curr.next = newNode;
	}
	
	public void prepend(int val, double val2) {
		Node newNode = new Node(val, val2);
		newNode.next = first;
		first = newNode;
	}
	
	
	public Node removeFirst() {
			Node val = first;
			first = first.next;
			return val;
	}
	
	public Node removeLast() {
		Node val = first;
		while(val.next != null )	{
			val = val.next;
		}
		return val;
	}
	public Node removeByValue(int val, double val2 ) {
		if( first == null )	return null;
		if(first.iData == val && first.dData == val2) return first;
		Node curr = first;
		while(curr.next != null ) {
			if(curr.next.iData == val && curr.next.dData == val2){
				Node target = curr.next;
				curr.next = curr.next.next;
				return target;
			}
			curr = curr.next;
		}
		return null;
	}
	
	public boolean isEmpty() { return first == null;}
	public boolean hasNext() {
		Node curr = first;
		return curr.next != null;
	}
	
	public void print () {
		Node curr = first;
		while( curr != null ) {
			curr.display();
			curr = curr.next;
		}
	}
	
	class Node {
		public int iData;
		public double dData;
		public Node next;
		
		public Node(int i, double d ) {
			iData = i;
			dData = d;
			next = null;
		}
		public void display() {
      System.out.print(this + " ");
		}
		public String toString() {
//			return "{ " + iData + ", " +  dData + ", " + next + " }";
			return "{ " + iData + ", " +  dData + " }";
		}
	}
}
