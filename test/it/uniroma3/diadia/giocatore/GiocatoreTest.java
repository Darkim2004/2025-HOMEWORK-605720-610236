package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

public class GiocatoreTest {
	// Fixture
	public Partita partita;
	public Giocatore giocatore;
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
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
