import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;

public class Program {
	public static void main(String args[]) {
		int i;
		Population p = new Population(50);

		for (i = 0; i < 10000; ++i) {
			p.mutate(25);
			p.crossOver(25);
			p.selection(50);
			System.out.println(i);
			if (i % 100 == 0) {
				p.showSmallest();
			}
		}
		p.showSmallest();
	}
}

class Population {
	ArrayList<Chromozome> list;
	Fitness fit;
	public Population(int n) {
		int i;
		Chromozome ch;
		list = new ArrayList<Chromozome>();
		for (i = 0; i < n; ++i) {
			ch = new Chromozome();
			ch.mutateAll();
			list.add(ch);
		}
		try {
			fit = new Fitness("orloj.jpg");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public void mutate(int n) {
		int i;
		Chromozome ch;
		Random rnd = new Random();
		for (i = 0; i < n; ++i) {
			ch = list.get(rnd.nextInt(list.size())).clone();
			ch.mutate(10);
			list.add(ch);
		}
	}
	public void crossOver(int n) {
		int i;
		Chromozome ch1, ch2;
		Random rnd = new Random();
		for (i = 0; i < n; ++i) {
			ch1 = list.get(rnd.nextInt(list.size()));
			do {
				ch2 = list.get(rnd.nextInt(list.size()));
			} while (ch1 == ch2);
			ch1 = ch1.clone();
			ch2 = ch2.clone();
			ch1.crossOver(ch2);
			list.add(ch1);
			list.add(ch2);
		}
	}
	public void selection(int n) {
		int i;
		Chromozome ch1, ch2;
		Random rnd = new Random();
		ArrayList<Chromozome> newlist = new ArrayList<Chromozome>();
		for (i = 0; i < n; ++i) {
			ch1 = list.get(rnd.nextInt(list.size()));
			do {
				ch2 = list.get(rnd.nextInt(list.size()));
			} while (ch1 == ch2);
			if (fit.getFitness(ch1) > fit.getFitness(ch2)) {
				ch1 = ch2;
			}
			newlist.add(ch1);
		}
		list = newlist;
	}
	public void showSmallest() {
		Chromozome ch = null, cch;
		int i, cf, f = Integer.MAX_VALUE;
		for (i = 0; i < list.size(); ++i) {
			cch = list.get(i);
			cf = fit.getFitness(cch);
			if (cf < f) {
				f = cf;
				ch = cch;
			}
		}
		ShowChromozome.show(ch, "lol");
	}
}

public class Chromozome {
	public static final int NUM_OF_POLYG = 30;
	public static final int NUM_OF_POINTS = 5;

	// body x, y, bravy R,G,B + ALPHA
	private int[] data = new int[NUM_OF_POLYG * (NUM_OF_POINTS * 2 + 3 + 1)]; // 420

	public int getData(int index) {
		return data[index];
	}
	public void crossOver(Chromozome ch) {
		Random rnd = new Random();

		// jeden polygon zabira 14 polozek
		int POLY_LENGTH = (NUM_OF_POINTS * 2 + 3 + 1);

		// pro kazdy polygon
		for (int i = 0; i < NUM_OF_POLYG; i++) {
			// nahodne
			if (rnd.nextBoolean()) {
				// prohod polygony
				for (int j = 0; j < POLY_LENGTH; j++) {
					// prohod
					int tmp = data[i * POLY_LENGTH + j];
					data[i * POLY_LENGTH + j] = ch.data[i * POLY_LENGTH + j];
					ch.data[i * POLY_LENGTH + j] = tmp;
				}
			} else {
				// ponech jak je
			}
		}
	}

	/**
	 * Mutuj vsechny geny tohoto chromozomu.
	 */
	public void mutateAll() {
		Random rnd = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = rnd.nextInt(256);
		}
	}

	public void mutate(int perc) {
		Random rnd = new Random();
		for (int i = 0; i < data.length; i++) {
			if (rnd.nextInt(1000) < perc) {
				data[i] = rnd.nextInt(256);
			}
		}
	}


	public Chromozome clone() {
		Chromozome ch = new Chromozome();
		ch.data = data.clone();
		return ch;
	}

}

public class Fitness {

	public Fitness(String path) throws IOException {
		template = ImageIO.read(new File(path));
	}

	final static int width = 256;
	final static int height = 256;

	private BufferedImage template;
	private BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_ARGB);

	public int getFitness(Chromozome ch) {
		// vykresli chromozom do platna
		Graphics2D platno = (Graphics2D) image.getGraphics();
		ShowChromozome.drawGraphics(platno, ch);

		int error = 0;
		// pro vsechny pixely
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				// chromozom
				int c = image.getRGB(x, y);
				Color clrChromozom = new Color(c);

				// predloha
				int c2 = template.getRGB(x, y);
				Color clrObrazek = new Color(c2);
				
				// chyba
				error += Math.abs(clrChromozom.getRed() - clrObrazek.getRed());
				error += Math.abs(clrChromozom.getGreen() - clrObrazek.getGreen());
				error += Math.abs(clrChromozom.getBlue() - clrObrazek.getBlue());
			}
		}
		return error;
	}

}

/**
 * Tato trida slouzi pouze pro vizualizaci vysledu - netreba ji rozmet.
 * 
 */
public class ShowChromozome extends JPanel {
	private static final long serialVersionUID = 1L;
	private Chromozome chromozome;

	public ShowChromozome(Chromozome ch) {
		this.chromozome = ch;
		setSize(256, 256);
		setPreferredSize(new Dimension(256, 256));
	}

	public void paintComponent(Graphics g) {
		drawGraphics(g, chromozome);
	}

	public static void drawGraphics(Graphics g, Chromozome chromozome) {
		// vymazaat platno
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 256, 256);

		// vykresli polygony
		int index = 0;
		for (int i = 0; i < Chromozome.NUM_OF_POLYG; i++) {
			Polygon poly = new Polygon();
			for (int j = 0; j < Chromozome.NUM_OF_POINTS; j++) {
				poly.addPoint(chromozome.getData(index),
						chromozome.getData(index + 1));
				index += 2;
			}
			Color c = new Color(chromozome.getData(index),
					chromozome.getData(index + 1),
					chromozome.getData(index + 2),
					chromozome.getData(index + 3));
			index += 4;
			g.setColor(c);
			g.fillPolygon(poly);
		}
	}

	public static void show(Chromozome ch, String title) {
		JFrame frame = new JFrame();
		frame.setTitle(title);
		Container contentPane = frame.getContentPane();
		contentPane.add(new ShowChromozome(ch));
		frame.pack();
		frame.setVisible(true);
	}
}
