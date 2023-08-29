package simulazione.modello;

import java.util.Set;

import simulazione.esemplare.Esemplare;

public class Contatto implements Comparable<Contatto> {

	private int passo; // il passo della simulazione in cui è avvenuto il contatto
	private Set<Esemplare> coinvolti;
	private Coordinate coordinate;

	public Contatto(int passo, Set<Esemplare> coinvolti, Coordinate luogo) {
		if (coinvolti.isEmpty())
			throw new IllegalArgumentException();
		this.passo = passo;
		this.coinvolti = coinvolti;
		this.coordinate = luogo;
	}

	public Set<Esemplare> getCoinvolti() {
		return this.coinvolti;
	}

	public int getPasso() {
		return this.passo;
	}

	public int numeroCoinvolti() {
		return this.coinvolti.size();
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ": passo " + this.passo + ", #coinvolti " + this.coinvolti.size()
				+ "]";
	}

	@Override
	public int compareTo(Contatto o) {
		int cmp = this.getPasso() - o.getPasso();
		if (cmp == 0)
			cmp = this.numeroCoinvolti() - o.numeroCoinvolti();
		if (cmp == 0)
			cmp = this.getCoordinate().getX() + this.getCoordinate().getY() - o.getCoordinate().getX()
					+ o.getCoordinate().getY();
		return cmp;
	}

}
