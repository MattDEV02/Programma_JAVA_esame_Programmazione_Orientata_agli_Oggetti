package simulazione.gui;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static simulazione.gui.LettoreImmagini.leggiImmagineMattone;
import static simulazione.modello.Costanti.DIMENSIONE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import simulazione.esemplare.Esemplare;
import simulazione.modello.Ambiente;
import simulazione.modello.Coordinate;
import simulazione.modello.Simulatore;

public class GUI extends JPanel implements CostantiGUI {

	static final private long serialVersionUID = 0L;

	static final private int MARGIN = 30;

	static final private Image IMMAGINE_MATTONE = leggiImmagineMattone();

	final private Simulatore simulatore;

	final private JFrame jframe;

	public GUI(final Simulatore simulatore) {
		this.simulatore = simulatore;
		this.jframe = new JFrame("Genesis");
		jframe.add(this);
		jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jframe.setSize(DIMENSIONE * DIM_CELLE, (DIMENSIONE + 1) * DIM_CELLE + MARGIN);
		jframe.setVisible(true);
	}

	public void initControlliDaTastiera(final Simulatore simulatore) {

		/* Gestione eventi associati alla tastiera */
		this.jframe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == VK_ESCAPE) {
					System.out.println("Richiesta la terminazione della simulazione.");
					simulatore.richiediTerminazione();
				}
			}
		});
	}

	public void riportaNelTitolo(int passo, Simulatore simulatore) {
		final StringBuilder builder = new StringBuilder("Passo: " + passo);
		builder.append(" Persone: " + simulatore.getAmbiente().getAll().size());
		builder.append(" Contatti: " + simulatore.getContatti().size());
		this.jframe.setTitle(builder.toString());
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(BLACK);
		g.fillRect(0, 0, DIMENSIONE * DIM_CELLE, DIMENSIONE * DIM_CELLE);
		final Ambiente ambiente = this.simulatore.getAmbiente();
		this.disegnaOstacoli(g, ambiente.getOstacoli());
		g.setColor(WHITE); // per stampare id

		for (Esemplare a : this.simulatore.getAll()) {
			disegna(g, a);
		}

	}

	private void disegna(Graphics g, Esemplare p) {
		final Coordinate posizione = p.getPosizione();
		final String ids = p.toString();
		disegnaTesto(g, posizione, ids);
		disegnaImmagine(g, p.getImmagine(), posizione, SCALA_IMMAGINE);
	}

	private void disegnaTesto(Graphics g, Coordinate p, String testo) {
		final int x = p.getX();
		final int y = p.getY();
		int d = DIM_CELLE;
		int gx = x * d, gy = y * d;
		g.drawString(testo, gx - d / 2, gy);
	}

	private void disegnaImmagine(Graphics g, Image image, Coordinate p) {
		final int x = p.getX();
		final int y = p.getY();
		int d = DIM_CELLE;
		int gx = x * d, gy = y * d;
		g.drawImage(image, gx, gy, d, d, null);
	}

	private void disegnaImmagine(Graphics g, Image image, Coordinate p, float scala) {
		final int x = p.getX();
		final int y = p.getY();
		int d = DIM_CELLE;
		int gx = Math.round(x * d - d * (scala - 1) / 2), gy = Math.round(y * d - d * (scala - 1) / 2);
		int size = Math.round(d * scala);
		g.drawImage(image, gx, gy, size, size, null);
	}

	private void disegnaOstacoli(Graphics g, Set<Coordinate> ostacoli) {
		for (Coordinate c : ostacoli) {
			disegnaOstacolo(g, c.getX(), c.getY(), COLORE_BORDO);
		}
	}

	private void disegnaOstacolo(Graphics g, int x, int y, Color colore) {
		g.setColor(colore);
		disegnaImmagine(g, IMMAGINE_MATTONE, new Coordinate(x, y));
	}

}
