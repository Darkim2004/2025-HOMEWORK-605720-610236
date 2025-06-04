package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class IOSimulatorTest {
	
	
	
	@Test
	public void testFine() throws Exception {
		LinkedList<String> istruzioni = new LinkedList<>();
		istruzioni.add("fine");
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		LinkedList<String> messaggi = io.getMessaggiScritti();
		
		assertEquals("Grazie di aver giocato!", messaggi.getLast());
	}
	
	@Test
	public void testVaiNord() throws Exception {
		LinkedList<String> istruzioni = new LinkedList<>();
		istruzioni.add("vai nord");
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		LinkedList<String> messaggi = io.getMessaggiScritti();
		
		assertEquals("Hai vinto!", messaggi.get(2));
	}
	
	@Test
	public void testVaiEst() throws Exception {
		LinkedList<String> istruzioni = new LinkedList<>();
		istruzioni.add("vai est");
		IOSimulator io = new IOSimulator(istruzioni);
		LabirintoBuilder labirintoBuilder = new LabirintoBuilder();
		Labirinto labirinto = labirintoBuilder
				.addStanzaIniziale("Atrio")
				.addStanza("Aula N11")
				.addAdiacenza("Atrio", "Aula N11", "est")
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		LinkedList<String> messaggi = io.getMessaggiScritti();
		
		assertEquals("Aula N11", messaggi.get(1));
	}
	
	@Test
	public void testPrendiLanterna() throws Exception {
		LinkedList<String> istruzioni = new LinkedList<>();
		istruzioni.add("vai sud");
		istruzioni.add("prendi lanterna");
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Aula N11")
				.addAdiacenza("Atrio", "Aula N11", "sud")
				.addAttrezzo("lanterna", 0)
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		LinkedList<String> messaggi = io.getMessaggiScritti();
		
		assertEquals("Aula N11", messaggi.get(1));
		assertEquals("L'oggetto lanterna è ora nella tua borsa", messaggi.get(2));
	}
	
	@Test
	public void testPrendiEPosaOsso() throws Exception {
		LinkedList<String> istruzioni = new LinkedList<>();
		istruzioni.add("prendi osso");
		istruzioni.add("posa osso");
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 0)
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		LinkedList<String> messaggi = io.getMessaggiScritti();
		
		assertEquals("L'oggetto osso è ora nella tua borsa", messaggi.get(1));
		assertEquals("Hai lasciato l'oggetto osso", messaggi.get(2));
	}
}
