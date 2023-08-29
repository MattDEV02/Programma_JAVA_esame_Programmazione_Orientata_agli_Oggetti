package simulazione.modello;

import java.util.Objects;

/**
 * Rappresenta le coordinate di una posizione all'interno di un piano
 * cartesiano. Modellata come coppia di interi: ascissa (x) ed ordinata (y).
 * <B>(DA COMPLETARE VEDI DOMANDA 1)</B>
 */
public class Coordinate {

	static public double distanza(Coordinate c0, Coordinate c1) {
		final int dx = c1.getX() - c0.getX();
		final int dy = c1.getY() - c0.getY();
		return Math.sqrt(dx * dx + dy * dy); // Teorema di Pitagora
	}

	private int x;

	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getX(), this.getY());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return this.getX() == other.getX() && this.getY() == other.getY();
	}

}
