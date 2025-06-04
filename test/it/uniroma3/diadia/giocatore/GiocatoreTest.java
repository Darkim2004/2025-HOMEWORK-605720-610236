package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class GiocatoreTest {
	// Fixture
	public Partita partita;
	public Giocatore giocatore;
	private Labirinto labirinto;
	private LabirintoBuilder labirintoBuilder;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirintoBuilder = new LabirintoBuilder();
		labirinto = labirintoBuilder
				.addStanzaIniziale("stanza")
				.getLabirinto();
		partita = new Partita(labirinto);
		giocatore = partita.getGiocatore();
	}
	
	@Test
	public void testGetCFUBase() {
		assertEquals(20, giocatore.getCfu());
	}
	
	@Test
	public void testSetCFU() {
		giocatore.setCfu(10);
		assertEquals(10, giocatore.getCfu());
	}
	
	@Test
	public void testgGetBorsaBase() {
		assertNotNull(giocatore.getBorsa());
	}
}
