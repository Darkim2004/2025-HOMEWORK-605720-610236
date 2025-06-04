package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	// Fixture
	private Stanza stanza;
	private Attrezzo attrezzo1;
	
	// Faccio il setup
	@BeforeEach
	public void setUp() {
		stanza = new Stanza("stanza");
		attrezzo1 = new Attrezzo("Attrezzo1", 1);
	}
	
	// Test addAttrezzo
	@Test
	public void testAddAttrezzoBase() {
		assertTrue(stanza.addAttrezzo(attrezzo1));
	}
	
	@Test
	public void testAddAttrezzoStanzaPiena() {
		for(int i = 0; i<10; i++) {
			stanza.addAttrezzo(new Attrezzo("Attrezzo"+i,1));
		}
		assertFalse(stanza.addAttrezzo(attrezzo1));
	}
	
	@Test
    public void testAddAttrezzoNull() {
        assertFalse(stanza.addAttrezzo(null));
    }
	
	// Test hasAttrezzo
	@Test
	public void testHasAttrezzoBase(){
		stanza.addAttrezzo(attrezzo1);
		assertTrue(stanza.hasAttrezzo("Attrezzo1"));
	}
	
	@Test
	public void testHasAttrezzoNonEsiste() {
		stanza.addAttrezzo(attrezzo1);
		assertFalse(stanza.hasAttrezzo("NonEsiste"));
	}
	
	@Test
	public void testHasAttrezzoNull() {
		stanza.addAttrezzo(attrezzo1);
		assertFalse(stanza.hasAttrezzo("NonEsiste"));
	}
	
	// Test per getAttrezzi
	@Test
	public void testgetAttrezzoBase() {
		stanza.addAttrezzo(attrezzo1);
		assertEquals(attrezzo1, stanza.getAttrezzo("Attrezzo1"));
	}
	
	@Test
	public void testgetAttrezzoStanzaVuota() {
		assertNull(stanza.getAttrezzo("Attrezzo1"));
	}
	
	@Test
	public void testgetAttrezzoNonPresente() {
		stanza.addAttrezzo(attrezzo1);
		assertNull(stanza.getAttrezzo("Attrezzo2"));
	}
	
	// Test per getStanzaAdiacente
	@Test
	public void testgetStanzaAdiacenteBase() {
		Stanza stanza1 = new Stanza("stanza 1");
		stanza.impostaStanzaAdiacente(Direzione.sud, stanza1);
		assertEquals(stanza1, stanza.getStanzaAdiacente(Direzione.sud));
	}
	
	@Test
	public void testgetStanzaAdiacenteDirezioneNull() {
		assertNull(stanza.getStanzaAdiacente(null));
	}
	
	@Test
	public void testgetStanzaAdiacenteNull() {
		assertNull(stanza.getStanzaAdiacente(Direzione.sud));
	}
	
	//Test removeAttrezzo
	@Test
	public void testRemoveAttrezzoBase() {
		stanza.addAttrezzo(attrezzo1);
		assertTrue(stanza.removeAttrezzo(attrezzo1));
	}
	
	@Test
	public void testRemoveAttrezzoStanzaVuota() {
		assertFalse(stanza.removeAttrezzo(attrezzo1));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		Attrezzo attrezzo2 = new Attrezzo("Attrezzo2", 1);
		stanza.addAttrezzo(attrezzo2);
		assertFalse(stanza.removeAttrezzo(attrezzo1));
	}
	
	@Test
	public void testRemoveAttrezzoNull() {
		assertFalse(stanza.removeAttrezzo(null));
	}
}
