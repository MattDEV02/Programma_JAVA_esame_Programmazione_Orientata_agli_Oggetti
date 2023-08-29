package simulazione.esemplare;

import java.awt.Image;
import java.util.Objects;

import simulazione.modello.Ambiente;
import simulazione.modello.Coordinate;
import simulazione.modello.GeneratoreCasuale;

public abstract class Esemplare {

	private final int id;

	private Ambiente ambiente;
	private Coordinate posizione; // posizione corrente
	private Esemplare obbiettivo;

	public Esemplare(Ambiente ambiente, int id) {
		this.ambiente = ambiente;
		this.posizione = GeneratoreCasuale.posizione();
		this.id = id;
		this.obbiettivo = null;
	}

	public Esemplare getObbiettivo() {
		return this.obbiettivo;
	}

	public void setObbiettivo(Esemplare obbiettivo) {
		this.obbiettivo = obbiettivo;
	}

	public Ambiente getAmbiente() {
		return this.ambiente;
	}

	public abstract Image getImmagine();

	public int getId() {
		return this.id;
	}

	public Coordinate getPosizione() {
		return this.posizione;
	}

	public void setPosizione(Coordinate nuova) {
		this.posizione = nuova;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getClass(), this.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Esemplare other = (Esemplare) obj;
		return this.getClass().equals(obj.getClass()) && this.getId() == other.getId();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + this.getId();
	}

	public abstract void mossa();
}
