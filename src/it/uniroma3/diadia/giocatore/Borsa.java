package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class Borsa{
	
	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMax();;
	private Set<Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashSet<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) { // Controllo null
			return false;
		}
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (Attrezzo i : attrezzi) {
		    if (i.getNome().equals(nomeAttrezzo)) {
		    	a = i;
		        break;
		    }
		}
		return a;
	}
	
	public int getPeso() {
		int peso = 0;
		for (Attrezzo i: attrezzi)
			peso += i.getPeso();
		return peso;
	}
	
	public String getDescrizione() {
		return this.toString();
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo==null) {
			return a;
		}
		for(Attrezzo i : attrezzi) {
			if(nomeAttrezzo.equals(i.getNome())) {
				a = i;
				// Rimuove attrezzo
				attrezzi.remove(i);
			}
		}
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo i : attrezzi)
				s.append(i.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> Lista = new LinkedList<Attrezzo>();
		Lista.addAll(attrezzi);
		Collections.sort(Lista, new ComparatoreAttrezziPerPeso());
		return Lista;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(attrezzi);
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<>();
		for(Attrezzo attrezzo : attrezzi) {
			if(mappa.containsKey(attrezzo.getPeso())) {
				mappa.get(attrezzo.getPeso()).add(attrezzo);
			}
			else {
				mappa.put(attrezzo.getPeso(), new HashSet<Attrezzo>());
				mappa.get(attrezzo.getPeso()).add(attrezzo);
			}
		}
		return mappa;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> set = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		set.addAll(attrezzi);
		return set;
	}
}
