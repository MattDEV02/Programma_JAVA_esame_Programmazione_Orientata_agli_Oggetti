package simulazione.statistiche;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import simulazione.esemplare.Bianco;
import simulazione.esemplare.Esemplare;
import simulazione.esemplare.Giallo;
import simulazione.esemplare.Rosso;
import simulazione.esemplare.Verde;
import simulazione.modello.Ambiente;
import simulazione.modello.Contatto;
import simulazione.modello.Coordinate;

public class StatisticheTest {

	private Statistiche stats;
	private Bianco bianco;
	private Giallo giallo;
	private Verde verde;
	private Rosso rosso;
	private Ambiente ambiente;

	@Before
	public void setUp() {
		this.stats = new Statistiche();
		this.ambiente = new Ambiente();
		this.bianco = new Bianco(this.ambiente);
		this.giallo = new Giallo(this.ambiente);
		this.verde = new Verde(this.ambiente);
		this.rosso = new Rosso(this.ambiente);
		this.ambiente.add(this.bianco);
		this.ambiente.add(this.giallo);
		this.ambiente.add(this.verde);
		this.ambiente.add(this.rosso);
	}

	@Test
	public void testProduciStatistica() {
		Collection<Contatto> contatti = new ArrayList<>();
		Contatto contattobg = new Contatto(1, new HashSet<Esemplare>(Arrays.asList(bianco, giallo)),
				new Coordinate(0, 0));
		Contatto contattovr = new Contatto(1, new HashSet<Esemplare>(Arrays.asList(verde, rosso)),
				new Coordinate(1, 0));
		Contatto contattovr2 = new Contatto(2, new HashSet<Esemplare>(Arrays.asList(verde, rosso)),
				new Coordinate(1, 0));
		contatti.add(contattobg);
		contatti.add(contattovr);
		contatti.add(contattovr2);
		Map<Class<? extends Esemplare>, SortedSet<Contatto>> produciStatistiche = this.stats
				.produciStatistiche(contatti);
		assertEquals(new HashSet<Class<?>>(Arrays.asList(Bianco.class, Giallo.class, Verde.class, Rosso.class)),
				produciStatistiche.keySet());
		assertEquals(new TreeSet<Contatto>(Arrays.asList(contattobg)), produciStatistiche.get(Bianco.class));
		assertEquals(new TreeSet<Contatto>(Arrays.asList(contattobg)), produciStatistiche.get(Giallo.class));
		assertEquals(new TreeSet<Contatto>(Arrays.asList(contattovr, contattovr2)),
				produciStatistiche.get(Verde.class));
		assertEquals(new TreeSet<Contatto>(Arrays.asList(contattovr, contattovr2)),
				produciStatistiche.get(Rosso.class));
	}

}
