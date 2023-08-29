package simulazione.esemplare;

import static simulazione.gui.CostantiGUI.RISORSA_IMMAGINE_VERDE;
import static simulazione.gui.LettoreImmagini.leggiImmagineOggetto;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simulazione.modello.Ambiente;
import simulazione.modello.Coordinate;

public class Verde extends Esemplare {

	static final private Image IMMAGINE_VERDE = leggiImmagineOggetto(RISORSA_IMMAGINE_VERDE);

	static private int progId = 0;

	public Verde(Ambiente ambiente) {
		super(ambiente, Verde.progId++);
		List<Esemplare> esemplari = this.getAmbiente().getAll();
		if (esemplari != null && esemplari.size() > 0) {
			Rosso obbiettivo = null;
			for (Esemplare esemplare : esemplari) {
				if (esemplare.getClass().equals(Rosso.class))
					obbiettivo = (Rosso) esemplare;
			}
			this.setObbiettivo(obbiettivo);
		}
	}

	@Override
	public Image getImmagine() {
		return Verde.IMMAGINE_VERDE;
	}

	public static int getProgId() {
		return Verde.progId;
	}

	public static void setProgId(int progId) {
		Verde.progId = progId;
	}

	@Override
	public void mossa() {
		if (this.getObbiettivo() != null) {
			Coordinate nuovaPos = this.getPosizione();
			Coordinate posObb = this.getObbiettivo().getPosizione();
			List<Coordinate> adiacentiY = new ArrayList<>(
					Arrays.asList(new Coordinate(this.getPosizione().getX(), this.getPosizione().getY() + 1),
							new Coordinate(this.getPosizione().getX(), this.getPosizione().getY() - 1)));
			if (adiacentiY.size() == 2) {
				if (Coordinate.distanza(adiacentiY.get(0), posObb) <= Coordinate.distanza(adiacentiY.get(1), posObb)
						&& this.getAmbiente().posValida(adiacentiY.get(0)))
					nuovaPos = adiacentiY.get(0);
				else if (this.getAmbiente().posValida(adiacentiY.get(1)))
					nuovaPos = adiacentiY.get(1);
			}
			this.setPosizione(nuovaPos);
		}
	}
}
