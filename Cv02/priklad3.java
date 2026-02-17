public class Program {
	public static void main(String args[]) {
		dir d1 = new dir();

		d1.setRight(new dir());
		d1.getRight().setRight(new dir());
		d1.setLeft(new dir());
		d1.getRight().setLeft(new dir());
	}
}

class dir {
	private dir left, right;
	public dir getLeft() {
		return left;
	}
	public dir getRight() {
		return right;
	}
	public void setLeft(dir left) {
		this.left = left;
	}
	public void setRight(dir right) {
		this.right = right;
	}
}
