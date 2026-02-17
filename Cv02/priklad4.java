import java.util.ArrayList;

public class Program {
	public static void main(String args[]) {
		node n1 = new node();

		n1.addNode(new node());
		n1.getNode(0).addNode(new node());
		n1.addNode(new node());
		n1.getNode(0).addNode(new node());
		n1.addNode(new node());

		n1.getNode(0).addNode(n1);
		n1.addNode(n1.getNode(0).getNode(1));
		n1.getNode(3).addNode(n1.getNode(1));

		n1.printNodes();
	}
}

class node {
	private ArrayList<node> nodes;
	private int id;
	private static int count = 0;
	public node() {
		nodes = new ArrayList<node>();
		id = ++node.count;
	}
	public String toString() {
		return Integer.toString(id);
	}
	public void addNode(node n) {
		nodes.add(n);
	}
	public node getNode(int i) {
		return nodes.get(i);
	}
	public void printNodes() {
		int i;
		System.out.print(toString() + ": ");
		for (i = 0; i < nodes.size(); ++i) {
			System.out.print(nodes.get(i).toString() + " ");
		}
	}
}
