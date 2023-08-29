package simulazione.esemplare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simulazione.modello.Ambiente;

/**
 * <B>(VEDI DOMANDA 2)</B>
 */
public class EsemplareTest {

	private Ambiente ambiente;

	@Before
	public void setUp() throws Exception {
		this.ambiente = new Ambiente();
		Bianco.setProgId(0);
		Giallo.setProgId(0);
		Rosso.setProgId(0);
		Verde.setProgId(0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIdProgressiviStessoTipo() {
		// DA RIVEDERE E COMPLETARE VEDI DOMANDA 2
		assertEquals(0, new Bianco(this.ambiente).getId());
		assertEquals(1, new Bianco(this.ambiente).getId());
		assertEquals(2, new Bianco(this.ambiente).getId());
	}

	@Test
	public void testIdProgressiviTipoDiverso() {
		// DA RIVEDERE E COMPLETARE VEDI DOMANDA 2
		assertEquals(0, new Bianco(this.ambiente).getId());
		assertEquals(0, new Giallo(this.ambiente).getId());
		assertEquals(0, new Rosso(this.ambiente).getId());
		assertEquals(0, new Verde(this.ambiente).getId());
		assertEquals(1, new Bianco(this.ambiente).getId());
		assertEquals(1, new Giallo(this.ambiente).getId());
		assertEquals(1, new Rosso(this.ambiente).getId());
		assertEquals(1, new Verde(this.ambiente).getId());
		assertEquals(2, new Bianco(this.ambiente).getId());
		assertEquals(2, new Giallo(this.ambiente).getId());
		assertEquals(2, new Rosso(this.ambiente).getId());
		assertEquals(2, new Verde(this.ambiente).getId());
	}

}
