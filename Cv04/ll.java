public class Program {
	public static void main(String args[]) {
		int i;
		ll l = new ll();
		for (i = 0; i < 20; ++i) {
			l.addFront(i);
		}
		l.print();
		l.reverse();
		System.out.println("==========");
		l.print();
		l.removeFront();
		System.out.println("==========");
		l.print();
		System.out.println(l.contains(20));
	}
}

class node {
	private int data;
	private node next;

	public node(int data, node next) {
		this.data = data;
		this.next = next;
	}

	public node getNext() {
		return next;
	}
	public void setNext(node next) {
		this.next = next;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
}

class ll {
	private node head;
	public void addFront(int data) {
		head = new node(data, head);
	}
	public void removeFront() {
		head = head.getNext();
	}
	public node getHead() {
		return head;
	}
	public boolean contains(int data) {
		node n;
		for (n = head; n != null; n = n.getNext()) {
			if (n.getData() == data) {
				return true;
			}
		}
		return false;
	}
	public void reverse() {
		node n;
		ll l = new ll();
		for (n = head; n != null; n = n.getNext()) {
			l.addFront(n.getData());
		}
		head = l.getHead();
	}
	public void print() {
		int i;
		node n;
		for (i = 0, n = head; n != null; n = n.getNext(), ++i) {
			System.out.printf("%2d) %d%n", i, n.getData());
		}
	}
}
