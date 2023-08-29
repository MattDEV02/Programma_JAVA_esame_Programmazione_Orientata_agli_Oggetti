package simulazione.gui;


import static java.awt.Color.LIGHT_GRAY;

import java.awt.Color;

public interface CostantiGUI {

	// Dimensione (in pixels) della cella in cui si svolge la simulazione
	static final public int DIM_CELLE = 24;
	
	static final public float SCALA_IMMAGINE = 1.2f;
	
	static final public Color COLORE_BORDO  = LIGHT_GRAY;

	static final public String RISORSA_IMMAGINE_BIANCO  = "white.png";

	static final public String RISORSA_IMMAGINE_GIALLO  = "yellow.png";

	static final public String RISORSA_IMMAGINE_ROSSO   = "red.png";

	static final public String RISORSA_IMMAGINE_VERDE   = "green.png";

	static final public String RISORSA_IMMAGINE_MATTONE = "brick.png";
	
}

