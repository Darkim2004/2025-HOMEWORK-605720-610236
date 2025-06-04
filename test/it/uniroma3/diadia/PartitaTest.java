package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class PartitaTest {

	// Fixture
	private Partita partita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private LabirintoBuilder labirintoBuilder;

	//Setup
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirintoBuilder = new LabirintoBuilder();  // inizializzazione corretta

		labirinto = labirintoBuilder
				.addStanzaIniziale("atrio")
				.addStanzaVincente("biblioteca")
				.getLabirinto();  // costruzione del labirinto

		partita = new Partita(labirinto);  // inizializzazione partita
		giocatore = partita.getGiocatore();  // riferimento utile
	}


	// Test isFinita
	@Test
	public void testIsFinitaInizio() {
		assertFalse(partita.isFinita());
	}

	@Test
	public void testIsFinitaStanzaVincente() {
		partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinitaZeroCFU() {
		giocatore.setCfu(0);
		assertTrue(partita.isFinita());
	}

	// Test getStanzaCorrente
	@Test 
	public void testGetStanzaCorrenteInizio() {
		assertEquals(labirinto.getStanzaIniziale(), partita.getStanzaCorrente());
	}

	@Test
	public void testGetStanzaCorrenteNull() {
		partita.setStanzaCorrente(null);
		assertNull(partita.getStanzaCorrente());
	}

	@Test
	public void testGetStanzaCorrenteStanzaVincente() {
		partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertEquals(labirinto.getStanzaVincente(), partita.getStanzaCorrente());
	}

	// Test vinta
	@Test
	public void testVintaInizio() {
		assertFalse(partita.vinta());
	}

	@Test
	public void testVintaStanzaNonVincente() {
		Stanza stanza1 = new Stanza("stanza 1");
		partita.setStanzaCorrente(stanza1);
		assertFalse(partita.vinta());
	}

	@Test 
	public void testVintaStanzaVincente() {
		partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(partita.vinta());
	}

	//Test setFinita
	@Test
	public void testSetFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}

	//Test getGiocatore
	@Test
	public void testGetGiocatore() {
		assertEquals(giocatore, partita.getGiocatore());
	}

	//Test getLabirinto
	@Test
	public void testGetLabirinto() {
		assertEquals(labirinto, partita.getLabirinto());
	}

}
