package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class IOSimulatorTest {
	
	@Test
	public void testFine() {
		String[] istruzioni = {"fine"};
		IOSimulator io = new IOSimulator(istruzioni);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] messaggi = io.getMessaggiScritti();
		
		assertEquals("Grazie di aver giocato!", messaggi[1]);
	}
	
	@Test
	public void testVaiNord() {
		String[] istruzioni = {"vai nord"};
		IOSimulator io = new IOSimulator(istruzioni);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] messaggi = io.getMessaggiScritti();
		
		assertEquals("Hai vinto!", messaggi[2]);
	}
	
	@Test
	public void testVaiEst() {
		String[] istruzioni = {"vai est"};
		IOSimulator io = new IOSimulator(istruzioni);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] messaggi = io.getMessaggiScritti();
		
		assertEquals("Aula N11", messaggi[1]);
	}
	
	@Test
	public void testPrendiLanterna() {
		String[] istruzioni = {"vai sud","prendi lanterna"};
		IOSimulator io = new IOSimulator(istruzioni);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] messaggi = io.getMessaggiScritti();
		
		assertEquals("Aula N10", messaggi[1]);
		assertEquals("L'oggetto lanterna è ora nella tua borsa", messaggi[2]);
	}
	
	@Test
	public void testPrendiEPosaOsso() {
		String[] istruzioni = {"prendi osso","posa osso"};
		IOSimulator io = new IOSimulator(istruzioni);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] messaggi = io.getMessaggiScritti();
		
		assertEquals("L'oggetto osso è ora nella tua borsa", messaggi[1]);
		assertEquals("Hai lasciato l'oggetto osso", messaggi[2]);
	}
}
