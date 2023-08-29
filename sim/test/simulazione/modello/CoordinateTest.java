package simulazione.modello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Modificare la classe {@link Coordinate} affinche' questi test abbiano
 * successo <B>(VEDI DOMANDA 1)</B>
 */
public class CoordinateTest {

	@Test
	public void testEquals() {
		assertEquals(new Coordinate(0, 0), new Coordinate(0, 0));
	}

	@Test
	public void testSetDiCoordinate() {
		final Set<Coordinate> insieme = new HashSet<>();
		final Coordinate c = new Coordinate(0, 0);
		final Coordinate e = new Coordinate(0, 0);
		assertNotSame(c, e);
		insieme.add(c);
		insieme.add(e);
		assertEquals("Due oggetti distinti ma con le stesse coordinate devono essere considerati come duplicati!", 1,
				insieme.size());
	}

}
