package simulazione;

import simulazione.gui.GUI;
import simulazione.modello.Simulatore;


/**
 * Eseguire il metodo {@linkplain #main(String[])} 
 * di questa classe per lanciare l'applicazione.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		final Simulatore simulatore = new Simulatore();
		final GUI gui = new GUI(simulatore);
		gui.initControlliDaTastiera(simulatore);
		simulatore.setGUI(gui);
		simulatore.simula();
	}

}
