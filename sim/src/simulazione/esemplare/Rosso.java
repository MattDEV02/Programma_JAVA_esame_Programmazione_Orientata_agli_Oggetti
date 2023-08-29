package simulazione.esemplare;

import static simulazione.gui.CostantiGUI.RISORSA_IMMAGINE_ROSSO;
import static simulazione.gui.LettoreImmagini.leggiImmagineOggetto;

import java.awt.Image;
import java.util.List;
import java.util.Set;

import simulazione.modello.Ambiente;
import simulazione.modello.Coordinate;

public class Rosso extends Esemplare {

	static final private Image IMMAGINE_ROSSO = leggiImmagineOggetto(RISORSA_IMMAGINE_ROSSO);

	static private int progId = 0;

	public Rosso(Ambiente ambiente) {
		super(ambiente, Rosso.progId++);
	}

	@Override
	public Image getImmagine() {
		return Rosso.IMMAGINE_ROSSO;
	}

	public static int getProgId() {
		return Rosso.progId;
	}

	public static void setProgId(int progId) {
		Rosso.progId = progId;
	}

	@Override
	public void mossa() {
		List<Esemplare> esemplari = this.getAmbiente().getAll();
		double distanza = 0;
		double distanzaMax = 0;
		Coordinate posObb = this.getPosizione();
		Esemplare obbiettivo = null;
		for (Esemplare esemplare : esemplari) {
			posObb = esemplare.getPosizione();
			if (this.getAmbiente().posValida(posObb)) {
				distanza = Coordinate.distanza(this.getPosizione(), posObb);
				if (distanza > distanzaMax) {
					distanzaMax = distanza;
					posObb = esemplare.getPosizione();
					obbiettivo = esemplare;
				}
			}
		}
		this.setObbiettivo(obbiettivo);
		distanza = 0;
		double distanzaMin = Double.MAX_VALUE;
		Coordinate nuovaPos = this.getPosizione();
		Set<Coordinate> adiacenti = this.getAmbiente().adiacentiA(this.getPosizione());
		for (Coordinate adiacente : adiacenti) {
			if (this.getAmbiente().posValida(posObb) && this.getAmbiente().posValida(adiacente)) {
				distanza = Coordinate.distanza(adiacente, posObb);
				if (distanza < distanzaMin) {
					distanzaMin = distanza;
					nuovaPos = adiacente;
				}
			}
		}
		this.setPosizione(nuovaPos);
	}
}
