package simulazione.modello;

import static simulazione.modello.Costanti.DURATA_SIMULAZIONE;
import static simulazione.modello.Costanti.NUMERO_INIZIALE_PER_TIPOLOGIA;
import static simulazione.modello.Costanti.RITMO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

import simulazione.esemplare.Bianco;
import simulazione.esemplare.Esemplare;
import simulazione.esemplare.Giallo;
import simulazione.esemplare.Rosso;
import simulazione.esemplare.Verde;
import simulazione.gui.GUI;
import simulazione.statistiche.Statistiche;

public class Simulatore {

	final private Ambiente ambiente;
	final private List<Contatto> contatti;

	private GUI gui;

	private int passo;

	volatile boolean terminazioneRichiesta;

	public Simulatore() {
		this.passo = 0;
		this.contatti = new LinkedList<>();
		this.terminazioneRichiesta = false;
		this.ambiente = new Ambiente();
		this.popolaAmbiente();
	}

	/** DA AGGIORNARE (VEDI DOMANDA 2) **/
	private void popolaAmbiente() {
		for (int i = 0; i < NUMERO_INIZIALE_PER_TIPOLOGIA; i++) {
			this.ambiente.add(new Bianco(this.ambiente));
			this.ambiente.add(new Giallo(this.ambiente));
			this.ambiente.add(new Rosso(this.ambiente));
			this.ambiente.add(new Verde(this.ambiente));
		}
	}

	public List<Esemplare> getAll() {
		return this.ambiente.getAll();
	}

	public void setGUI(GUI gui) {
		this.gui = gui;
	}

	public Ambiente getAmbiente() {
		return this.ambiente;
	}

	public int getPasso() {
		return this.passo;
	}

	public void simula() {

		for (this.passo = 0; this.passo < DURATA_SIMULAZIONE; this.passo++) {
			eseguiUnPasso();
			aggiornaStatistiche();
			pausa();
			if (terminazioneRichiesta()) {
				System.out.println("Interruzione richiesta al passo " + passo);
				System.out.println();
				break;
			}
		}
		System.out.println("Simulazione terminata.");

		/* Termina la simulazione corrente; */
		/* Stampa le statistiche finali */
		new Statistiche().stampaFinale(this);

		terminaBrutalmente();
	}

	private boolean terminazioneRichiesta() {
		return this.terminazioneRichiesta;
	}

	private void eseguiUnPasso() {
		/* crea una lista ordinata casualmente di tutta la popolazione */
		final List<Esemplare> all = new ArrayList<>(this.ambiente.getAll());
		Collections.shuffle(all); // mischia

		for (Esemplare e : all) { // prima muovo tutte
			e.mossa();
		}
		for (Esemplare e : all) { //
			final Set<Esemplare> stessaPosizione = this.getAmbiente().get(e.getPosizione());
			/* registra eventuale contatto */
			if (stessaPosizione.size() > 1) { // meno di 2 non e' un contatto....
				final Contatto contatto = new Contatto(this.passo, stessaPosizione, e.getPosizione());
				this.registraContatto(contatto);
			}
		}
	}

	private void registraContatto(final Contatto contatto) {
		this.contatti.add(contatto);
	}

	public List<Contatto> getContatti() {
		return this.contatti;
	}

	private void aggiornaStatistiche() {
		/* stampa passo simulazione */
		this.gui.riportaNelTitolo(this.passo, this);
	}

	private void pausa() {
		try {
			this.updateGui();
			Thread.sleep(RITMO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void updateGui() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Simulatore.this.gui.repaint();
			}
		});
	}

	/**
	 * Termina la simulazione corrente arrestando l'intera JVM!!!
	 */
	public void terminaBrutalmente() {
		System.exit(-1);
	}

	public void richiediTerminazione() {
		this.terminazioneRichiesta = true;
	}

}
