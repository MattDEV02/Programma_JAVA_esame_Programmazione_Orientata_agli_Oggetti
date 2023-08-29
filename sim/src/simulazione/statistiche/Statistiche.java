package simulazione.statistiche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import simulazione.esemplare.Esemplare;
import simulazione.modello.Contatto;
import simulazione.modello.Simulatore;

/**
 * <B>DA COMPLETARE (VEDI DOMANDA 3)</B>
 */
public class Statistiche {

	synchronized public void stampaFinale(Simulatore simulatore) {
		final List<Contatto> contatti = simulatore.getContatti();

		System.out.println(contatti.size() + " contatti rilevati.");
		System.out.println(simulatore.getContatti());
		System.out.println();

		final Map<?, ?> mappa = produciStatistiche(simulatore.getContatti());
		System.out.println("Contatti per esemplare:");
		stampaStatistiche(mappa);
		System.out.println();
	}

	public Map<Class<? extends Esemplare>, SortedSet<Contatto>> produciStatistiche(Collection<Contatto> contatti) {
		Map<Class<? extends Esemplare>, SortedSet<Contatto>> produciStatistiche = new HashMap<>();
		SortedSet<Contatto> tmp = null;
		List<Esemplare> coinvolti = null;
		Class<? extends Esemplare> esemplareClass = null;
		for (Contatto c : contatti) {
			coinvolti = new ArrayList<>(c.getCoinvolti());
			if (coinvolti != null) {
				for (Esemplare e : coinvolti) {
					esemplareClass = e.getClass();
					if (esemplareClass != null) {
						if (produciStatistiche.get(esemplareClass) == null)
							tmp = new TreeSet<Contatto>();
						tmp.add(c);
						produciStatistiche.put(esemplareClass, tmp);
					}
				}
			}
		}
		return produciStatistiche;
	}

	/**
	 * <EM>N.B. UTILE PER STAMPARE E QUINDI CONTROLLARE EVENTUALI RISULTATI DELLA
	 * DOMANDA 3</EM>
	 */
	private void stampaStatistiche(final Map<?, ?> mappa) {
		for (Object k : mappa.keySet()) {
			final Collection<?> c = (Collection<?>) mappa.get(k);
			System.out.print(k + "  :");
			for (Object v : c)
				System.out.print(v.toString() + " ");
			System.out.println();
		}
	}
}
