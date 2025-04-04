package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	//Fixture
	private Partita partita;
	private Giocatore giocatore;
	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
		giocatore = partita.getGiocatore();
		borsa = giocatore.getBorsa();
		attrezzo = new Attrezzo("attrezzo", 0);
	}
	
	// Test addAttrezzo
	@Test
	public void testAddAttrezzoBase() {
		assertTrue(borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testAddAttrezzoPesoMax() {
		Attrezzo attrezzo2 = new Attrezzo("attrezzo2", 11);
		assertFalse(borsa.addAttrezzo(attrezzo2));
	}
	
	@Test
	public void testAddAttrezzoNumeroMax() {
		for(int i = 0; i < 10; i++) {
			Attrezzo attrezzo2 = new Attrezzo("attrezzo"+i, 0);
			borsa.addAttrezzo(attrezzo2);
		}
		assertFalse(borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testAddAttrezzoNull() {
		assertFalse(borsa.addAttrezzo(null));
	}
}
