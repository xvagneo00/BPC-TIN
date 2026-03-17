import java.util.HashMap;
import java.util.ArrayList;

public class Program {
	public static void main(String args[]) {
		graph g = new graph();
		path p;

		//g.add("1", "7", 16);
		//g.add("1", "6", 6);
		//g.add("6", "7", 8);

		g.add("1", "6", 6);
		g.add("1", "2", 4);
		g.add("1", "7", 16);
		g.add("6", "3", 23);
		g.add("6", "7", 8);
		g.add("6", "5", 5);
		g.add("7", "5", 10);
		g.add("7", "8", 21);
		g.add("5", "4", 11);
		g.add("5", "3", 18);
		g.add("5", "8", 14);
		g.add("2", "3", 24);
		g.add("4", "3", 9);
		g.add("4", "8", 7);

		g.print();

		p = new path(g);
		p.add("1");
		p.add("6");
		p.add("5");
		p.add("4");
		p.add("3");
		p.print();
		System.out.println(p.cost);
	}
}

class neighbour {
	node n;
	int cost;
	public neighbour(node n, int cost) {
		this.n = n;
		this.cost = cost;
	}
}

class node {
	String name;
	HashMap<String,neighbour> neighbours;
	public node(String name) {
		this.name = name;
		neighbours = new HashMap<String,neighbour>();
	}
	public void addNeighbour(node n, int cost) {
		neighbours.put(n.name, new neighbour(n, cost));
	}
	public neighbour getNeighbour(String name) {
		return neighbours.get(name);
	}
	public void print() {
		for (neighbour n : neighbours.values()) {
			System.out.printf("%s -> %s : %d%n", name, n.n.name, n.cost);
		}
	}
}

class graph {
	HashMap<String,node> nodes;
	public graph() {
		nodes = new HashMap<String,node>();
	}
	private node getNode(String name) {
		node n = nodes.get(name);

		if (n == null) {
			n = new node(name);
			nodes.put(name, n);
		}

		return n;
	}
	public void add(String name1, String name2, int cost) {
		node n1, n2;

		n1 = getNode(name1);
		n2 = getNode(name2);

		n1.addNeighbour(n2, cost);
		n2.addNeighbour(n1, cost);
	}
	public node get(String name) {
		return nodes.get(name);
	}
	public void print() {
		for (node n : nodes.values()) {
			n.print();
		}
	}
}

class path {
	ArrayList<node> nodes;
	graph g;
	int cost = 0;

	public path(graph g) {
		this.g = g;
		nodes = new ArrayList<node>();
	}
	public void add(String name) {
		node n;
		neighbour ne;

		if (nodes.size() == 0) {
			nodes.add(g.get(name));
			return;
		}

		n = nodes.get(nodes.size() - 1);
		ne = n.getNeighbour(name);

		if (ne == null) {
			throw new RuntimeException(name + " is not a neighbour of " + n.name);
		}

		nodes.add(ne.n);
		cost += ne.cost;
	}
	public void print() {
		int i;

		for (i = 0; i < nodes.size(); ++i) {
			System.out.print(nodes.get(i).name);
			System.out.print((i == (nodes.size() - 1)) ? "" : " -> ");
		}

		System.out.println();
	}
}
