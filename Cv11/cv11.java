import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

public class Program {
	public static void main(String args[]) {
		ArrayList<edge> st;
		graph g = new graph();
		g.add(1, 2, 4);
		g.add(2, 3, 24);
		g.add(3, 4, 9);
		g.add(4, 5, 11);
		g.add(5, 6, 5);
		g.add(6, 7, 8);
		g.add(7, 8, 21);
		g.add(1, 6, 6);
		g.add(1, 7, 16);
		g.add(3, 5, 18);
		g.add(3, 6, 23);
		g.add(4, 8, 7);
		g.add(5, 7, 10);
		g.add(5, 8, 14);
		st = g.getSpanningTree();
		for (edge e : st) {
			System.out.printf("%d %d%n", e.v1.data, e.v2.data);
		}
	}
}

class vertex {
	Integer data;
	public vertex(int data) {
		this.data = Integer.valueOf(data);
	}
	@Override
	public int hashCode() {
		return data;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		return this.data.equals(((vertex)o).data);
	}
}

class edge implements Comparable<edge> {
	vertex v1;
	vertex v2;
	int cost;
	public edge(vertex v1, vertex v2, int cost) {
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}
	@Override
	public int hashCode() {
		return (v1.data * 1000000) + (v2.data * 1000) + cost;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		return hashCode() == ((edge)o).hashCode();
	}
	@Override
	public int compareTo(edge e) {
		return Integer.compare(cost, e.cost);
	}
}

class graph {
	HashMap<Integer, vertex> vertices;
	HashSet<edge> edges;
	public graph() {
		vertices = new HashMap<Integer, vertex>();
		edges = new HashSet<edge>();
	}
	public void add(int vi1, int vi2, int cost) {
		vertex v1, v2;
		v1 = newVertex(vi1);
		v2 = newVertex(vi2);
		edges.add(new edge(v1, v2, cost));
	}
	private vertex newVertex(int vi) {
		vertex v = vertices.get(vi);
		if (v == null) {
			v = new vertex(vi);
			vertices.put(vi, v);
		}
		return v;
	}
	public ArrayList<edge> getSpanningTree() {
		HashSet<vertex> s1, s2;
		HashMap<vertex, HashSet<vertex>> forest;
		LinkedList<edge> edges;
		ArrayList<edge> st;

		forest = new HashMap<vertex, HashSet<vertex>>();
		for (vertex v : vertices.values()) {
			s1 = new HashSet<vertex>();
			s1.add(v);
			forest.put(v, s1);
		}

		st = new ArrayList<edge>();

		edges = new LinkedList<edge>();
		edges.addAll(this.edges);

		Collections.sort(edges);

		for (edge e : edges) {
			s1 = forest.get(e.v1);
			s2 = forest.get(e.v2);
			if (!s1.equals(s2)) {
				st.add(e);

				s1.addAll(s2);
				for (vertex v : s1) {
					forest.put(v, s1);
				}
			}
		}

		return st;
	}
}
