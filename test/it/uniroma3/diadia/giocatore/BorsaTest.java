package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class BorsaTest {
	//Fixture
	private Partita partita;
	private Giocatore giocatore;
	private Borsa borsa;
	private Attrezzo attrezzo;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
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
		borsa = giocatore.getBorsa();
		attrezzo = new Attrezzo("attrezzo", 0);
		attrezzo2 = new Attrezzo("attrezzo2", 1);
		attrezzo3 = new Attrezzo("attrezzo3", 0);
	}
	
	// Test addAttrezzo
	@Test
	public void testAddAttrezzoBase() {
		assertTrue(borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testAddAttrezzoPesoMax() {
		attrezzo2 = new Attrezzo("attrezzo2", 11);
		assertFalse(borsa.addAttrezzo(attrezzo2));
	}
	
	@Test
	public void testAddAttrezzoNull() {
		assertFalse(borsa.addAttrezzo(null));
	}
	
	//Test removeAttrezzo
	@Test
	public void testRemoveAttrezzoBase() {
		borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo,borsa.removeAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		borsa.addAttrezzo(attrezzo);
		assertNotEquals(attrezzo,borsa.removeAttrezzo("Nome a caso"));
	}
	
	@Test
	public void testRemoveAttrezzoNomeNull() {
		borsa.addAttrezzo(attrezzo);
		assertEquals(null,borsa.removeAttrezzo(null));
	}
	
	// Test getSortedSetOrdinatoPerPeso
	@Test
	public void testGetSortedSetOrdinatoPerPesoStessoPeso() {
		Set<Attrezzo> risultato = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		risultato.add(attrezzo3);
		risultato.add(attrezzo);
		borsa.addAttrezzo(attrezzo3);
		borsa.addAttrezzo(attrezzo);
		assertEquals(risultato, borsa.getSortedSetOrdinatoPerPeso());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPesoDiversoPeso() {
		Set<Attrezzo> risultato = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		risultato.add(attrezzo2);
		risultato.add(attrezzo);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo);
		assertEquals(risultato, borsa.getSortedSetOrdinatoPerPeso());
	}
	
	// Test getContenutoOrdinatoPerPeso
	@Test
	public void testGetContenutoOrdinatoPerPesoBase() {
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(attrezzo2);
		List<Attrezzo> risultato = new LinkedList<>();
		risultato.add(attrezzo);
		risultato.add(attrezzo2);
		assertEquals(risultato, borsa.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoStessoPeso() {
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(attrezzo3);
		List<Attrezzo> risultato = new LinkedList<>();
		risultato.add(attrezzo);
		risultato.add(attrezzo3);
		assertEquals(risultato, borsa.getContenutoOrdinatoPerPeso());
	}
	
	// Test getContenutoOrdinatoPerNome
	@Test
	public void testGetContenutoOrdinatoPerNomeBase() {
		attrezzo = new Attrezzo("AAAAA", 0);
		attrezzo2 = new Attrezzo("BBBBB", 0);
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(attrezzo2);
		SortedSet<Attrezzo> risultato = new TreeSet<>();
		risultato.add(attrezzo2);
		risultato.add(attrezzo);
		assertEquals(risultato, borsa.getContenutoOrdinatoPerNome());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNomeStessoNome() {
		attrezzo = new Attrezzo("AAAAA", 0);
		attrezzo2 = new Attrezzo("AAAAA", 1);
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(attrezzo2);
		SortedSet<Attrezzo> risultato = new TreeSet<>();
		risultato.add(attrezzo);
		assertEquals(risultato, borsa.getContenutoOrdinatoPerNome());
	}
	
	// Test getContenutoRaggruppatoPerPeso
	@Test
	public void testGetContenutoRaggruppatoPerPesoBase() {
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(attrezzo2);
		Set<Attrezzo> set1 = new HashSet<>();
		set1.add(attrezzo);
		Set<Attrezzo> set2 = new HashSet<>();
		set2.add(attrezzo2);
		Map<Integer,Set<Attrezzo>> risultato = new TreeMap<>();
		risultato.put(attrezzo.getPeso(), set1);
		risultato.put(attrezzo2.getPeso(), set2);
		assertEquals(risultato, borsa.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoStessoPeso() {
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(attrezzo3);
		Set<Attrezzo> set1 = new HashSet<>();
		set1.add(attrezzo);
		set1.add(attrezzo3);
		Map<Integer,Set<Attrezzo>> risultato = new TreeMap<>();
		risultato.put(attrezzo.getPeso(), set1);
		assertEquals(risultato, borsa.getContenutoRaggruppatoPerPeso());
	}
}
