import java.util.HashMap;

public class Program {
	public static void main(String args[]) {
		boolean bool = false;
		system sys = new system();
		car c = new car("45C7864", "BMW", "red", 4);
		car c2 = new car("45C7864", "BMW", "blue", 4);
		bike b = new bike("741741C", "BMW", "blue", bikeType.TRAVEL);
		spot s = new spot(745, vehicleType.CAR);
		System.out.println(c instanceof vehicle);
		System.out.println(c instanceof car);
		System.out.println((Object)c instanceof bike);
		System.out.println(c.equals(c));
		System.out.println(c.equals("lol"));
		System.out.println(c.equals(c2));
		String plate = "45C7864";
		System.out.println(plate.hashCode());
		System.out.println(c.hashCode());
		System.out.println(c2.hashCode());
		System.out.println(c2);
		System.out.println(c);
		System.out.println(s);
		System.out.println(b);

		System.out.println();
		System.out.println("==========================");
		System.out.println();

		sys.addVehicle(c2);
		sys.addVehicle(c);
		sys.addVehicle(b);
		sys.addVehicle(new car("534DBHE", "Skoda", "blue", 4));
		sys.addVehicle(new car("PIUYB8J", "Skoda", "blue", 4));
		sys.addVehicle(new car("52OISXM", "Skoda", "blue", 4));
		sys.addVehicle(new car("1LCGVZB", "Skoda", "blue", 4));
		sys.addVehicle(new car("XAMS6YQ", "Skoda", "blue", 4));
		sys.addVehicle(new car("T2ZKP4S", "Skoda", "blue", 4));
		sys.addVehicle(new car("DZMPTFX", "Skoda", "blue", 4));
		sys.addVehicle(new car("DYB867T", "Skoda", "blue", 4));
		sys.addVehicle(new car("K14WUHM", "Skoda", "blue", 4));
		sys.addVehicle(new car("XOJ8A5W", "Skoda", "blue", 4));
		sys.addVehicle(new bike("2HI8FKJ", "Jawa", "red", bikeType.TRAVEL));
		sys.addVehicle(new bike("2JGXRKD", "Jawa", "red", bikeType.TRAVEL));
		sys.addVehicle(new bike("GGOTY8Q", "Jawa", "red", bikeType.SPORT));
		sys.addVehicle(new bike("HQ6RGKW", "Jawa", "red", bikeType.TRAVEL));
		sys.addVehicle(new bike("W1Z62FT", "Jawa", "red", bikeType.TRAVEL));
		sys.addVehicle(new bike("55TQ08C", "Jawa", "red", bikeType.SPORT));
		sys.addVehicle(new bike("7SUQO7M", "Jawa", "red", bikeType.TRAVEL));
		sys.addVehicle(new bike("YKMS48V", "Jawa", "red", bikeType.TRAVEL));
		sys.addVehicle(new bike("X2X0M9O", "Jawa", "red", bikeType.SPORT));
		sys.addVehicle(new bike("G7RF8CC", "Jawa", "red", bikeType.SPORT));

		sys.addSpot(s);
		sys.addSpot(new spot(763, vehicleType.CAR));
		sys.addSpot(new spot(984, vehicleType.CAR));
		sys.addSpot(new spot(844, vehicleType.BIKE));
		sys.addSpot(new spot(429, vehicleType.CAR));
		sys.addSpot(new spot(605, vehicleType.CAR));
		sys.addSpot(new spot(539, vehicleType.BIKE));
		sys.addSpot(new spot(280, vehicleType.CAR));
		sys.addSpot(new spot(274, vehicleType.BIKE));
		sys.addSpot(new spot(502, vehicleType.CAR));
		sys.addSpot(new spot(179, vehicleType.BIKE));

		System.out.println(sys.getVehicle("45C7864"));

		sys.printAll();

		System.out.println();
		System.out.println("==========================");
		System.out.println();

		sys.leave(sys.getVehicle("XOJ8A5W"));
		bool = sys.park(sys.getVehicle("XOJ8A5W"), sys.getSpot(539));
		System.out.println(bool);

		bool = sys.park(sys.getVehicle("X2X0M9O"), sys.getSpot(539));
		System.out.println(bool);

		sys.leave(sys.getVehicle("XOJ8A5W"));
		bool = sys.park(sys.getVehicle("X2X0M9O"), sys.getSpot(539));
		System.out.println(bool);

		bool = sys.park(sys.getVehicle("GGOTY8Q"), sys.getSpot(539));
		System.out.println(bool);

		bool = sys.park(sys.getVehicle("X2X0M9O"), sys.getSpot(274));
		System.out.println(bool);




		sys.printFreeSpots();

		System.out.println();
		System.out.println("==========================");
		System.out.println();

		sys.printAll();
	}
}

abstract class vehicle {
	String plate;
	String type;
	String color;
	spot s;

	public vehicle(String plate, String type, String color) {
		this.plate = plate;
		this.type = type;
		this.color = color;
	}

	@Override
	public String toString() {
		return String.format("plate=%s;type=%s;color=%s;parked=%b;", plate, type, color, s != null);
	}
	@Override
	public int hashCode() {
		return plate.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof vehicle)) {
			return false;
		}

		if (this == o) {
			return true;
		}

		return this.plate == ((vehicle)o).plate;
	}
}

class car extends vehicle {
	int doors;
	public car(String plate, String type, String color, int doors) {
		super(plate, type, color);
		this.doors = doors;
	}
	@Override
	public String toString() {
		return "car : " + super.toString() + String.format("doors=%d", doors);
	}
}

enum bikeType {
	SPORT,
	TRAVEL,
}

class bike extends vehicle {
	bikeType type;
	public bike(String plate, String type, String color, bikeType btype) {
		super(plate, type, color);
		this.type = btype;
	}
	@Override
	public String toString() {
		String type = "";
		switch (this.type) {
			case bikeType.SPORT:
				type = "sport";
				break;
			case bikeType.TRAVEL:
				type = "travel";
				break;
		}
		return "bike: " + super.toString() + String.format("bikeType=%s", type);
	}
}

enum vehicleType {
	CAR,
	BIKE,
}

class spot {
	int id;
	vehicleType type;
	vehicle v;

	public spot(int id, vehicleType type) {
		this.id = id;
		this.type = type;
	}

	@Override
	public String toString() {
		String type = "";
		switch (this.type) {
			case vehicleType.CAR:
				type = "car";
				break;
			case vehicleType.BIKE:
				type = "bike";
				break;
		}
		return String.format("spot: id=%d;type=%s;free=%b", id, type, v == null);
	}

	@Override
	public int hashCode() {
		return id;
	}
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof spot)) {
			return false;
		}

		if (this == o) {
			return true;
		}

		return this.id == ((spot)o).id;
	}
}

class system {
	private HashMap<String,vehicle> vehicles;
	private HashMap<Integer,spot> spots;

	public system() {
		vehicles = new HashMap<String,vehicle>();
		spots = new HashMap<Integer,spot>();
	}

	public void addVehicle(vehicle v) {
		vehicles.put(v.plate, v);
	}

	public void removeVehicle(vehicle v) {
		vehicles.remove(v);
	}

	public vehicle getVehicle(String plate) {
		return vehicles.get(plate);
	}

	public void addSpot(spot s) {
		spots.put(s.id, s);
	}

	public void removeSpot(spot s) {
		spots.remove(s);
	}

	public spot getSpot(int id) {
		return spots.get(id);
	}

	private void checkVehicle(vehicle v) {
		vehicle old = vehicles.get(v.plate);

		if (old == v) {
			return;
		}

		v.s = null;
		addVehicle(v);
		throw new RuntimeException("debug me");
	}

	private void checkSpot(spot s) {
		spot old = spots.get(s.id);

		if (old == s) {
			return;
		}

		s.v = null;
		addSpot(s);
		throw new RuntimeException("debug me");
	}

	public void leave(vehicle v) {
		checkVehicle(v);
		if (v.s == null) {
			return;
		}

		v.s.v = null;
		v.s = null;
	}

	public boolean park(vehicle v, spot s) {
		checkVehicle(v);
		checkSpot(s);
		if (s.v != null) {
			return false;
		}
		
		switch (s.type) {
			case vehicleType.CAR:
				if (!(v instanceof car)) {
					return false;
				}
				break;
			case vehicleType.BIKE:
				if (!(v instanceof bike)) {
					return false;
				}
				break;
		}

		leave(v);
		s.v = v;
		v.s = s;
		return true;
	}

	public void printFreeSpots() {
		for (spot s : spots.values()) {
			if (s.v == null) {
				System.out.println(s);
			}
		}
	}

	public void printAll() {
		for (vehicle v : vehicles.values()) {
			System.out.println(v);
		}
		for (spot s : spots.values()) {
			System.out.println(s);
		}
	}
}
