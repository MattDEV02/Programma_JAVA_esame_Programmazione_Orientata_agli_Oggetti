package simulazione.modello;

public class Costanti {

	/**
	 * La {@link Ambiente} in cui si svolge la simulazione e' un quadrato di queste dimensioni (incluso i bordi)
	 */
	static final public int DIMENSIONE = 30;

	/**
	 * Numero di esemplari per ogni tipologia
	 */
	static final public int NUMERO_INIZIALE_PER_TIPOLOGIA = 10;

	/**
	 * Durata (in passi) totale della simulazione
	 */
	static final public int DURATA_SIMULAZIONE = 200;

	/**
	 * Pausa (in millisecondi) tra due passi consecutivi della simulazione
	 */
	static final public int RITMO  = 100; // millis

	
	static final public float COSTANTE_DI_PROBABILITA  = 0.2f;


}
