package simulazione.esemplare;

import static simulazione.gui.CostantiGUI.RISORSA_IMMAGINE_BIANCO;
import static simulazione.gui.LettoreImmagini.leggiImmagineOggetto;

import java.awt.Image;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import simulazione.modello.Ambiente;
import simulazione.modello.Coordinate;

public class Bianco extends Esemplare {

	static final private Image IMMAGINE_BIANCA = leggiImmagineOggetto(RISORSA_IMMAGINE_BIANCO);

	static private int progId = 0;

	public Bianco(Ambiente ambiente) {
		super(ambiente, Bianco.progId++);
	}

	public static int getProgId() {
		return Bianco.progId;
	}

	public static void setProgId(int progId) {
		Bianco.progId = progId;
	}

	@Override
	public void mossa() {
		/* Si muove casualmente: DA RIVEDERE COME CHIESTO NELLA DOMANDA 2 */
		List<Coordinate> adiacenti = new LinkedList<>(this.getAmbiente().adiacentiA(this.getPosizione()));
		Collections.shuffle(adiacenti);
		this.setPosizione(adiacenti.get(0));
	}

	@Override
	public Image getImmagine() {
		return Bianco.IMMAGINE_BIANCA;
	}

}
