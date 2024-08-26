package co.loyyee.ch05LinkedList;

/**
 * Last chapter is made of Array, this is chapter in Linked List
 * */
public class StackLinkedList {
	
	private Node top;
	
	public StackLinkedList() {
		top = null;
	}
	
	public static void main(String[] args){
		var stack = new StackLinkedList();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.print();
		stack.pop();
		stack.print();
		
	}
	private void push(int val) {
		Node newNode = new Node(val);
		if (top == null) {
			top = newNode;
			return;
		}
		newNode.next = top;
		top = newNode;
	}
	
	public Node pop() {
		Node val= top;
		top = top.next;
		return val;
	}
	
	public boolean isEmpty() {
		return top == null;
	}

	public void print()  {
		Node curr = top;
		while( curr != null ){
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
		return "{ " + data + " } "	;
//			return "{ " + data + " -> " + next + " }"	;
		}
	}
}
