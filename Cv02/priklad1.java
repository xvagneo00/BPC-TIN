import java.util.ArrayList;

public class Program {
	public static void main(String args[]) {
		map m = new map();
		m.addCar(new car("autor", 0, 0));
		m.addCar(new car("traktor", 0, 0));
		m.addCar(new car("lod", 0, 0));
		m.printAll();
	}
}

class car {
	private String name;
	private int x, y;
	public car(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String toString() {
		return name + ":" + Integer.toString(x) + "," + Integer.toString(y);
	}
}

class map {
	private ArrayList<car> cars;
	public map() {
		cars = new ArrayList<car>();
	}
	public void addCar(car c) {
		cars.add(c);
	}
	public car getCar(int i) {
		return cars.get(i);
	}
	public void removeCar(int i) {
		cars.remove(i);
	}
	public int getCount() {
		return cars.size();
	}
	public void printAll() {
		int i;
		for (i = 0; i < cars.size(); ++i) {
			System.out.println(cars.get(i));
		}
	}
}
