package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StanzaBloccataTest {
	// Fixture
	private StanzaBloccata stanza;
	private Stanza stanzaInaccessibile;
	private Stanza stanzaAccessibile;
	private String attrezzoSbloccante;
	
	@BeforeEach
	public void setUp() {
		attrezzoSbloccante = "chiave";
		stanza = new StanzaBloccata("bloccata", Direzione.nord, attrezzoSbloccante);
		stanzaInaccessibile = new Stanza("stanza");
		stanzaAccessibile = new Stanza("stanza");
		stanza.impostaStanzaAdiacente(Direzione.nord, stanzaInaccessibile);
		stanza.impostaStanzaAdiacente(Direzione.sud, stanzaAccessibile);
	}
	
	// Test getStanzaAdiacente
	@Test
	public void testGetStanzaAdiacenteSbloccata() {
		assertEquals(stanzaAccessibile,stanza.getStanzaAdiacente(Direzione.sud));
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccata() {
		assertEquals(stanza,stanza.getStanzaAdiacente(Direzione.nord));
	}
}
