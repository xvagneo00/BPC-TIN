public class Program {
	public static void main(String args[]) {
		tree t = new tree();
		t.insert(7);
		t.insert(5);
		t.insert(14);
		t.insert(1);
		t.insert(6);
		t.insert(16);
		t.insert(10);

		t.print(tree.PREORDER, false);
		System.out.println("=====================");
		t.print(tree.PREORDER, true);

		System.out.println("=====================");
		System.out.println("=====================");

		t.print(tree.INORDER, false);
		System.out.println("=====================");
		t.print(tree.INORDER, true);

		System.out.println("=====================");
		System.out.println("=====================");

		t.print(tree.POSTORDER, false);
		System.out.println("=====================");
		t.print(tree.POSTORDER, true);

		System.out.println("=====================");
		System.out.println("=====================");

		System.out.println(t.contains(16));
		System.out.println(t.contains(17));

		System.out.println("=====================");
		System.out.println("=====================");

		t.printLeafs();
	}
}

class node {
	int data;
	node nodes[] = {null, null};
	public node(int data) {
		this.data = data;
	}
}

class tree {
	private node head;
	public final static int PREORDER = 0;
	public final static int INORDER = 1;
	public final static int POSTORDER = 2;
	public void insert(int data) {
		int i;
		node n;

		if (head == null) {
			head = new node(data);
			return;
		}

		for (n = head;;) {
			i = (n.data > data) ? 0 : 1;

			if (n.nodes[i] != null) {
				n = n.nodes[i];
			} else {
				n.nodes[i] = new node(data);
				break;
			}
		}
	}

	private void postorder(node n, boolean reverse) {
		if (n == null) {
			return;
		}

		if (!reverse) {
			postorder(n.nodes[0], reverse);
			postorder(n.nodes[1], reverse);
			System.out.println(n.data);
		} else {
			System.out.println(n.data);
			postorder(n.nodes[1], reverse);
			postorder(n.nodes[0], reverse);
		}
	}

	private void inorder(node n, boolean reverse) {
		if (n == null) {
			return;
		}

		if (!reverse) {
			inorder(n.nodes[0], reverse);
			System.out.println(n.data);
			inorder(n.nodes[1], reverse);
		} else {
			inorder(n.nodes[1], reverse);
			System.out.println(n.data);
			inorder(n.nodes[0], reverse);
		}
	}

	private void preorder(node n, boolean reverse) {
		if (n == null) {
			return;
		}

		if (!reverse) {
			System.out.println(n.data);
			preorder(n.nodes[0], reverse);
			preorder(n.nodes[1], reverse);
		} else {
			preorder(n.nodes[1], reverse);
			preorder(n.nodes[0], reverse);
			System.out.println(n.data);
		}
	}

	public void print(int order, boolean reverse) {
		switch (order) {
			case tree.PREORDER:
				preorder(head, reverse);
				break;
			case tree.INORDER:
				inorder(head, reverse);
				break;
			case tree.POSTORDER:
				postorder(head, reverse);
				break;
		}
	}
	public boolean contains(int data) {
		node n;
		for (n = head; n != null;) {
			if (n.data == data) {
				return true;
			}

			n = n.nodes[(n.data > data) ? 0 : 1];
		}
		return false;
	}
	private void printLeaf(node n) {
		if (n == null) {
			return;
		}

		printLeaf(n.nodes[0]);
		printLeaf(n.nodes[1]);
		if (n.nodes[0] == null && n.nodes[1] == null) {
			System.out.println(n.data);
		}
	}
	public void printLeafs() {
		printLeaf(head);
	}
}
