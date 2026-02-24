import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics2D;

public class Program {
	public static void main(String args[]) {
		canvas c = new canvas(800, 800);

		c.add(new circle(10, 15, 30));
		c.add(new circle(22, 4, 14));
		c.add(new square(50, 50, 30));

		group g = new group();
		g.add(new circle(30, 30, 100));
		g.add(new square(100, 100, 80));
		c.add(g);

		c.draw("test.png");
	}
}

interface drawable {
	public void draw(Graphics2D g);
}

abstract class shape implements drawable {
	protected int x, y;
	public shape(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public abstract void draw(Graphics2D g);
}

class circle extends shape {
	private int r;
	public circle(int x, int y, int r) {
		super(x, y);
		this.r = r;
	}
	public void draw(Graphics2D g) {
		System.out.printf("[*] Drawing circle at (%d,%d) with radius %d%n", x, y, r);
		g.drawOval(x, y, r, r);
	}
}

class rect extends shape {
	private int w, h;
	public rect(int x, int y, int w, int h) {
		super(x, y);
		this.w = w;
		this.h = h;
	}
	public void draw(Graphics2D g) {
		System.out.printf("[*] Drawing rectangle at (%d,%d) with width %d and height %d%n", x, y, w, h);
		g.drawRect(x, y, w, h);
	}
}

class square extends rect {
	public square(int x, int y, int s) {
		super(x, y, s, s);
	}
}

class group implements drawable {
	private ArrayList<drawable> drawables;
	public group() {
		drawables = new ArrayList<drawable>();
	}
	public void add(drawable d) {
		drawables.add(d);
	}
	public void draw(Graphics2D g) {
		int i;
		for (i = 0; i < drawables.size(); ++i) {
			drawables.get(i).draw(g);
		}
	}
}

class canvas extends group {
	private BufferedImage img;
	public canvas(int w, int h) {
		super();
		img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	}
	public void draw(String filename) {
		super.draw((Graphics2D)img.getGraphics());
		try {
			ImageIO.write(img, "PNG", new File(filename));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
