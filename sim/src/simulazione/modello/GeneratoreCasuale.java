package simulazione.modello;

import static simulazione.modello.Costanti.DIMENSIONE;

import java.util.Random;

public class GeneratoreCasuale {

	static final private Random random = new Random();
	
	/**
	 * @return le coordinate di una posizione scelta a caso tra quelle
	 *         all'interno della zona 
	 */
	static public Coordinate posizione() {
		final int x = 1 + random.nextInt(DIMENSIONE-2);
		final int y = 1 + random.nextInt(DIMENSIONE-2);
		return new Coordinate(x,y);
	}

	/**
	 * Genera un numero intero casuale
	 * @param n - max valore restituito (escluso)
	 * @return un numero intero casuale tra 0 ed n-1
	 */
	static final public int numeroSinoA(int n) {
		return random.nextInt(n);
	}
	
	/**
	 * Metodo di utilita' per semplificare la generazioni di eventi con una
	 * data probabilita'
	 * @param p - una probabilita' (0<=p<=1)
	 * @return true - se e solo se l'evento di una certa probabilita' si e' verificato
	 */
	static final public boolean siVerificaEventoDiProbabilita(float p) {
		return ( random.nextFloat() <= p ) ;
	}
	
}
