package simulazione.modello;

import static simulazione.modello.Costanti.DIMENSIONE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import simulazione.esemplare.Esemplare;

public class Ambiente {

	final private int dimensione;

	final private Set<Coordinate> ostacoli;

	final private List<Esemplare> popolazione;

	/**
	 * Crea un ambiente (quadrato) delle dimensioni prefissate
	 */
	public Ambiente() {
		this(DIMENSIONE);
	}

	public Ambiente(int dim) {
		this.dimensione = dim;
		this.ostacoli = new HashSet<>();
		this.inizializzaBordi(this.dimensione);
		this.popolazione = new ArrayList<>();
	}

	private void inizializzaBordi(int dim) {
		for (int i = 0; i < dim; i++) {
			addOstacolo(0, i);
			addOstacolo(i, 0);
			addOstacolo(dim - 1, i);
			addOstacolo(i, dim - 1);
		}
	}

	public int getDimensione() {
		return this.dimensione;
	}

	public void addOstacolo(int x, int y) {
		this.ostacoli.add(new Coordinate(x, y));
	}

	public Set<Coordinate> getOstacoli() {
		return this.ostacoli;
	}

	public boolean collideConOstacolo(Coordinate pos) {
		return this.getOstacoli().contains(pos);
	}

	public boolean isFuoriDaBordi(Coordinate pos) {
		final int x = pos.getX();
		final int y = pos.getY();
		return x <= 0 || x >= this.dimensione - 1 || y <= 0 || y >= this.dimensione - 1;
	}

	public void add(Esemplare p) {
		this.popolazione.add(p);
	}

	public List<Esemplare> getAll() {
		return this.popolazione;
	}

	public Set<Esemplare> get(Coordinate posizione) {
		final Set<Esemplare> risultato = new HashSet<>();
		for (Esemplare e : getAll())
			if (e.getPosizione().equals(posizione))
				risultato.add(e);
		return risultato;
	}

	public void rimuoviAll(Set<Esemplare> all) {
		this.popolazione.removeAll(all);
	}

	public void rimuovi(Esemplare e) {
		this.popolazione.remove(e);
	}

	public Set<Coordinate> adiacentiA(Coordinate corrente) {
		Set<Coordinate> adiacenti = new HashSet<>();
		final int x = corrente.getX();
		final int y = corrente.getY();
		adiacenti.add(new Coordinate(x + 1, y + 1));
		adiacenti.add(new Coordinate(x - 1, y - 1));
		adiacenti.add(new Coordinate(x + 1, y - 1));
		adiacenti.add(new Coordinate(x - 1, y + 1));
		Set<Coordinate> possibili = new HashSet<>();
		for (Coordinate c : adiacenti)
			if (!this.collideConOstacolo(c) && !this.isFuoriDaBordi(c))
				possibili.add(c);
		return possibili;
	}

	public boolean posValida(Coordinate pos) {
		return pos != null && !collideConOstacolo(pos) && !isFuoriDaBordi(pos);
	}

}
