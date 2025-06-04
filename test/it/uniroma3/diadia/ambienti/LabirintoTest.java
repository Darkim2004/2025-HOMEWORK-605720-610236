package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class LabirintoTest {
	// Fixture
	private Labirinto labirinto;
	private Stanza iniziale;
	private Stanza vittoria;
	private Stanza qualunque;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addStanza("Qualunque")
				.getLabirinto();
		iniziale = new Stanza("Atrio");
		vittoria = new Stanza("Biblioteca");
		qualunque = new Stanza("Qualunque");
	}
	
	// Test getStanzaIniziale
	@Test
	public void testGetStanzaIniziale() {
		assertEquals(iniziale.getNome(), labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testGetStanzaInizialeNoAtrio() {
		assertNotEquals(qualunque.getNome(), labirinto.getStanzaIniziale().getNome());
	}
	
	// Test getStanzaVincente
	@Test
	public void testGetStanzaVincente() {
		assertEquals(vittoria.getNome(), labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testGetStanzaVincenteNoBiblioteca() {
		assertNotEquals(qualunque.getNome(), labirinto.getStanzaIniziale().getNome());
	}
}
