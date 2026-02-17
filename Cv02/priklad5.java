import java.util.HashSet;

public class Program {
	public static void main(String args[]) {
		board b1 = new board();
		b1.move(1);
		b1.print();

		board b2 = new board();
		b2.move(1);
		b2.print();

		HashSet<board> hs = new HashSet<board>();
		hs.add(b1);
		hs.add(b2);

		System.out.println(b1.hashCode());
		System.out.println(b2.hashCode());
		System.out.println(b1.equals(b2));
		System.out.println(b2.equals(b1));
	}
}

class board {
	private int[][] grid = {
		{1, 2, 3},
		{4, 5, 6},
		{7, 8, 0},
	};
	private void swap(int x1, int y1, int x2, int y2) {
		int tmp = grid[y1][x1];
		grid[y1][x1] = grid[y2][x2];
		grid[y2][x2] = tmp;
	}
	public void move(int dir) {
		int x = 0, y;
		//boolean found; //should always find
		out:
		for (y = 0; y < grid.length; ++y) {
			for (x = 0; x < grid[0].length; ++x) {
				if (grid[y][x] == 0) {
					//System.out.println(Integer.toString(x) + " " + Integer.toString(y));
					break out;
				}
			}
		}
		switch (dir) {
			case 0: //UP
				if (y != (grid.length - 1)) {
					swap(x, y, x, y+1);
				}
				break;
			case 1: //DOWN
				if (y != 0) {
					swap(x, y, x, y-1);
				}
				break;
			case 2: //LEFT
				if (x != (grid[0].length - 1)) {
					swap(x, y, x+1, y);
				}
				break;
			case 3: //RIGHT
				if (x != 0) {
					swap(x, y, x-1, y);
				}
				break;
		}
	}
	public void print() {
		int x, y;
		for (y = 0; y < grid.length; ++y) {
			for (x = 0; x < grid[0].length; ++x) {
				System.out.print(Integer.toString(grid[y][x]) + " ");
			}
			System.out.println();
		}
	}
	public int hashCode() {
		int x, y, r = 0, s = 1;
		for (y = 0; y < grid.length; ++y) {
			for (x = 0; x < grid[0].length; ++x) {
				//assumes one digit
				r += grid[y][x] * s;
				s *= 10;
			}
		}
		return r;
	}
	public boolean equals(board b) {
		return this.hashCode() == b.hashCode();
	}
}
