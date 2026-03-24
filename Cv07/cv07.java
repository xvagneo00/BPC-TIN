import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
//import org.apache.commons.lang.builder.HashCodeBuilder;

public class Main {
	public static void main(String[] args) {
		HraciPole h = new HraciPole();
		for (int i = 0; i < 10000; i++) {
			h.pohni(new Random().nextInt(4)+ 1);
		}

		//h.pohni(1);
		//h.pohni(3);

		System.out.println(h);
		System.out.println(h.hashCode());
		BFS.vypisTahy(h);
		
	}
}

public class HraciPole{
	private int[][] data = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 0 } };
	private int x = 2;
	private int y = 2;
	private ArrayList<Integer> pohyby = new ArrayList<Integer>();

	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	public HraciPole klonujAPohni(int smer) {
		HraciPole nove = new HraciPole();
		nove.x = this.x;
		nove.y = this.y;
		nove.pohyby.addAll(this.pohyby);
		nove.pohyby.add(smer);
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				nove.data[x][y] = this.data[x][y];
			}
		}

		boolean jeMozny = nove.pohni(smer);
		if (jeMozny) {
			return nove;
		} else {
			return null;
		}
	}

	public boolean pohni(int smer) {
		switch (smer) {
		case UP:
			// nahoru
			if (y < 1) {
				return false;
			}
			data[x][y] = data[x][y - 1];
			data[x][y - 1] = 0;
			y = y - 1; // y--;
			return true;
		case DOWN:
			// nahoru
			if (y > 1) {
				return false;
			}
			data[x][y] = data[x][y + 1];
			data[x][y + 1] = 0;
			y++; // y = y + 1;
			return true;
		case LEFT:
			// nahoru
			if (x < 1) {
				return false;
			}
			data[x][y] = data[x - 1][y];
			data[x - 1][y] = 0;
			x = x - 1; // y--;
			return true;
		case RIGHT:
			if (x > 1) {
				return false;
			}
			data[x][y] = data[x + 1][y];
			data[x + 1][y] = 0;
			x++; // y = y + 1;
			return true;// TODO
		}
		return false;
	}


	@Override
	public String toString() {
		String res = "pohyby:" + pohyby + "\n";
		for (int iy = 0; iy < 3; iy++) {
			for (int ix = 0; ix < 3; ix++) {
				res += data[ix][iy] + ",";
			}
			res += "\n";
		}
		return res;
	}

	@Override
	public int hashCode() {
		int x, y, r = 0, s = 1;
		for (y = 0; y < 3; ++y) {
			for (x = 0; x < 3; ++x) {
				//assumes one digit
				r += data[x][y] * s;
				s *= 10;
			}
		}
		return r;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		return this.hashCode() == ((HraciPole)o).hashCode();
	}

	public boolean jeReseni() {
		return hashCode() == 87654321;
	}
}

public class BFS {
	public static void vypisTahy(HraciPole zadani) {
		
		// TIP: Vyzkousejte s ruznyma strukturama
		HashSet<HraciPole> closed = new HashSet<HraciPole>();
		// TreeSet<HraciPole> closed = new TreeSet<HraciPole>();
		// LinkedList<HraciPole> closed = new LinkedList<HraciPole>();

		int i, j;
		HraciPole curr, tmp;
		LinkedList<HraciPole> open = new LinkedList<HraciPole>();

		open.addLast(zadani);
		closed.add(zadani);

		j = 0;
		while (!open.isEmpty()) {
			curr = open.removeFirst();
			closed.add(curr);

			if (curr.jeReseni()) {
				System.out.println(j);
				System.out.println(curr);
				return;
			}

			for (i = 1; i < 5; ++i) {
				tmp = curr.klonujAPohni(i);
				if (tmp != null && !closed.contains(tmp)) {
					closed.add(tmp);
					open.addLast(tmp);
				}
			}
			++j;
		}
		System.out.println(j);
	}
}
