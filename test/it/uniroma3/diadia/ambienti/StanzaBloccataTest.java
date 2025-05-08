package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	// Fixture
	private StanzaBloccata stanza;
	private Stanza stanzaInaccessibile;
	private Stanza stanzaAccessibile;
	private Attrezzo attrezzoSbloccante;
	
	@BeforeEach
	public void setUp() {
		attrezzoSbloccante = new Attrezzo("chiave", 1);
		stanza = new StanzaBloccata("bloccata", "nord", attrezzoSbloccante);
		stanzaInaccessibile = new Stanza("stanza");
		stanzaAccessibile = new Stanza("stanza");
		stanza.impostaStanzaAdiacente("nord", stanzaInaccessibile);
		stanza.impostaStanzaAdiacente("sud", stanzaAccessibile);
	}
	
	// Test getStanzaAdiacente
	@Test
	public void testGetStanzaAdiacenteSbloccata() {
		assertEquals(stanzaAccessibile,stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccata() {
		assertEquals(stanza,stanza.getStanzaAdiacente("nord"));
	}
}
