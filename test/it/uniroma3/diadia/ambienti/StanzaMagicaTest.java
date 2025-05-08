package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	//Fixture
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private StanzaMagica stanza;
	
	@BeforeEach
	public void setUp() {
		attrezzo1 = new Attrezzo("Martello", 1);
		attrezzo2 = new Attrezzo("Cacciavite", 1);
		attrezzo3 = new Attrezzo("Sciabola", 1);
		stanza = new StanzaMagica("Aula DS3", 1);
	}
	
	// Test addAttrezzo
	@Test
	public void testaddAttrezzo1Posato() {
		stanza.addAttrezzo(attrezzo1);
		assertEquals("Martello", stanza.getAttrezzo("Martello").getNome());
	}
	
	@Test
	public void testaddAttrezzo2Posati() {
		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);
		assertEquals("etivaiccaC", stanza.getAttrezzo("etivaiccaC").getNome());
	}
	
	@Test
	public void testaddAttrezzo3Posati() {
		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);
		stanza.addAttrezzo(attrezzo3);
		assertEquals("etivaiccaC", stanza.getAttrezzo("etivaiccaC").getNome());
		assertEquals("alobaicS", stanza.getAttrezzo("alobaicS").getNome());
	}
	
	@Test
	public void testaddAttrezzo11Posati() {
		for(int i = 0; i<10; i++) {
			stanza.addAttrezzo(attrezzo1);
		}
		stanza.addAttrezzo(attrezzo2);
		assertNull(stanza.getAttrezzo("etivaiccaC"));
	}
}
