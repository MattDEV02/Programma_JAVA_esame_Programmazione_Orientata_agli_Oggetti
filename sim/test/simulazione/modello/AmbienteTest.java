package simulazione.modello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import simulazione.esemplare.Bianco;
import simulazione.esemplare.Giallo;
import simulazione.esemplare.Rosso;
import simulazione.esemplare.Verde;

public class AmbienteTest { // nello stesso test-case testo le mosse dei 4 esemplari per rispondere alla
							// domanda 4.

	private Bianco bianco;
	private Giallo giallo;
	private Rosso rosso;
	private Verde verde;
	private Ambiente ambiente;

	@Before
	public void setUp() throws Exception {
		this.ambiente = new Ambiente(7); // 7x7
		this.bianco = new Bianco(this.ambiente);
		this.rosso = new Rosso(this.ambiente);
		this.ambiente.add(this.bianco);
		this.ambiente.add(this.rosso);
		this.giallo = new Giallo(this.ambiente);
		this.verde = new Verde(this.ambiente);
		this.ambiente.add(this.giallo);
		this.ambiente.add(this.verde);
	}

	@Test
	public void testBianco() {
		this.bianco.setPosizione(new Coordinate(2, 2)); // centro
		this.bianco.mossa();
		assertNotNull(this.bianco);
		assertTrue(this.ambiente.posValida(this.bianco.getPosizione()));
		assertNotEquals(new Coordinate(2, 2), this.bianco.getPosizione());
	}

	@Test
	public void testGiallo() {
		this.giallo.setPosizione(new Coordinate(2, 2));
		this.rosso.setPosizione(new Coordinate(3, 3));
		this.bianco.setPosizione(new Coordinate(1, 1));
		this.giallo.mossa();
		assertEquals(this.bianco, this.giallo.getObbiettivo());
		assertEquals(new Coordinate(1, 2), this.giallo.getPosizione());
	}

	@Test
	public void testRosso() {
		this.rosso.setPosizione(new Coordinate(2, 2));
		this.bianco.setPosizione(new Coordinate(1, 1));
		this.verde.setPosizione(new Coordinate(1, 2));
		this.rosso.mossa();
		assertTrue(this.ambiente.posValida(this.rosso.getPosizione()));
		assertNotEquals(new Coordinate(2, 2), this.rosso.getPosizione());
		assertEquals(this.bianco, this.rosso.getObbiettivo());
		assertEquals(this.bianco.getPosizione(), this.rosso.getPosizione());
	}

	@Test
	public void testVerde() {
		this.verde.setPosizione(new Coordinate(2, 2));
		this.bianco.setPosizione(new Coordinate(3, 3));
		this.rosso.setPosizione(new Coordinate(1, 1));
		this.verde.mossa();
		assertEquals(this.rosso, this.verde.getObbiettivo());
		assertEquals(new Coordinate(2, 1), this.verde.getPosizione());
	}

}
