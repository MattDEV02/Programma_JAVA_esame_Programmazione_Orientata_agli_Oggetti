package simulazione.esemplare;

import static simulazione.gui.CostantiGUI.RISORSA_IMMAGINE_GIALLO;
import static simulazione.gui.LettoreImmagini.leggiImmagineOggetto;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import simulazione.modello.Ambiente;
import simulazione.modello.Coordinate;

public class Giallo extends Esemplare {

	static final private Image IMMAGINE_GIALLA = leggiImmagineOggetto(RISORSA_IMMAGINE_GIALLO);

	static private int progId = 0;

	public Giallo(Ambiente ambiente) {
		super(ambiente, Giallo.progId++);

		List<Esemplare> esemplari = this.getAmbiente().getAll();
		if (esemplari != null && esemplari.size() > 0) {
			List<Bianco> bianchi = new ArrayList<Bianco>();
			for (Esemplare esemplare : esemplari) {
				if (esemplare.getClass().equals(Bianco.class))
					bianchi.add((Bianco) esemplare);
			}
			int numeroBianchi = bianchi.size();
			if (numeroBianchi > 0) {
				Bianco obbiettivo = bianchi.get(new Random().nextInt(bianchi.size()));
				this.setObbiettivo(obbiettivo);
			}
		}
	}

	@Override
	public Image getImmagine() {
		return Giallo.IMMAGINE_GIALLA;
	}

	public static int getProgId() {
		return Giallo.progId;
	}

	public static void setProgId(int progId) {
		Giallo.progId = progId;
	}

	@Override
	public void mossa() { // giallo
		if (this.getObbiettivo() != null) {
			Coordinate posObb = this.getObbiettivo().getPosizione();
			Coordinate nuovaPos = this.getPosizione();
			List<Coordinate> adiacentiX = new ArrayList<>(
					Arrays.asList(new Coordinate(this.getPosizione().getX() - 1, this.getPosizione().getY()),
							new Coordinate(this.getPosizione().getX() + 1, this.getPosizione().getY())));
			if (adiacentiX.size() == 2) {
				if (Coordinate.distanza(adiacentiX.get(0), posObb) <= Coordinate.distanza(adiacentiX.get(1), posObb)
						&& this.getAmbiente().posValida(adiacentiX.get(0)))
					nuovaPos = adiacentiX.get(0);
				else if (this.getAmbiente().posValida(adiacentiX.get(1)))
					nuovaPos = adiacentiX.get(1);
			}

			this.setPosizione(nuovaPos);
		}
	}
}
