public class Program {
	public static void main(String args[]) {
		int i;
		packet p, head = new packet();
		for (i = 0, p = head; i < 3; ++i, p = p.getNext()) {
			p.setNext(new packet());
		}
		System.out.println("Test");
	}
}

class packet {
	private packet next;
	static int id = 0;
	public packet() {
		System.out.println(packet.id++);
	}
	public packet getNext() {
		return next;
	}
	public void setNext(packet next) {
		this.next = next;
	}
}
