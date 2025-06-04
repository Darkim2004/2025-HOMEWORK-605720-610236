package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/* prefisso di una singola riga contenente il nome della stanza magica */
	private static final String STANZE_MAGICHE_MARKER = "Magica:";

	/* prefisso di una singola riga contenente il nome della stanza buia */
	private static final String STANZE_BUIE_MARKER = "Buia:";

	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccata:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeMago> <presentazione> <attrezzo> */
	private static final String PERSONAGGI_MARKER_MAGO = "Mago:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeStrega> <presentazione> */
	private static final String PERSONAGGI_MARKER_STREGA = "Strega:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeCane> <presentazione> */
	private static final String PERSONAGGI_MARKER_CANE = "Cane:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)
	 *  
		Stanze:biblioteca,N10,N11,N12
		Magica:biblioteca
		Buia: N10 lanterna
		Bloccata:  
		Inizio:biblioteca
		Vincente:N11
		Mago: N10 Merlino Shiauuuuu bacchetta 4
		Cane: N12 Rex Bau Collare 1
		Strega: biblioteca Varana AHIUUUU
		Attrezzi:martello 10 biblioteca, lanterna 4 biblioteca
		Uscite:biblioteca nord N10,biblioteca sud N11, N10 sud biblioteca, N11 nord biblioteca, biblioteca est N12, N12 ovest biblioteca

	 */
	private BufferedReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECreaMaghi();
			this.leggiECreaCani();
			this.leggiECreaStreghe();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String presentazione = null;

			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La stanza "+specificaPersonaggio+" non esiste"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nel nome della strega"));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nella presentazione della strega"+ nomePersonaggio));
				presentazione = scannerLinea.next();
			}

			AbstractPersonaggio personaggio = new Strega(nomePersonaggio, presentazione);
			this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
		}
	}

	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String presentazione = null;
			String nomeAttrezzo = null;
			int pesoAttrezzo;

			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La stanza "+specificaPersonaggio+" non esiste"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nel nome del cane"));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nella presentazione del cane"+ nomePersonaggio));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nella assegnazione del nome dell'attrezzo del cane"+ nomePersonaggio));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNextInt(),msgTerminazionePrecoce("problemi nella assegnazione del peso dell'attrezzo del cane"+ nomePersonaggio));
				pesoAttrezzo = scannerLinea.nextInt();
			}

			AbstractPersonaggio personaggio = new Cane(nomePersonaggio, presentazione, new Attrezzo(nomeAttrezzo, pesoAttrezzo));
			this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
		}
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String presentazione = null;
			String nomeAttrezzo = null;
			int pesoAttrezzo;

			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La stanza "+specificaPersonaggio+" non esiste"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nel nome del mago"));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nella presentazione del mago"+ nomePersonaggio));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problemi nella assegnazione del nome dell'attrezzo del mago"+ nomePersonaggio));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNextInt(),msgTerminazionePrecoce("problemi nella assegnazione del peso dell'attrezzo del mago"+ nomePersonaggio));
				pesoAttrezzo = scannerLinea.nextInt();
			}

			AbstractPersonaggio personaggio = new Mago(nomePersonaggio, presentazione, new Attrezzo(nomeAttrezzo, pesoAttrezzo));
			this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
		}		
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);

		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			Direzione direzione = null;
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				while(scannerLinea.hasNext()) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("La stanza "+specificaStanza+" non esiste"));
					nomeStanza = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la  direzione della stanza"+ nomeStanza+" non esiste\n"));
					direzione = Direzione.valueOf(scannerLinea.next());
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("problema nel creare l'attrezzo sbloccante della stanza "+ nomeStanza));
					nomeAttrezzo = scannerLinea.next();
				}
			}				
			Stanza stanza = new StanzaBloccata(nomeStanza, direzione, nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, stanza);
		}		
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);

		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La stanza "+specificaStanza+" non esiste"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("problema nel creare l'attrezzo illuminante della stanza "+ nomeStanza));
				nomeAttrezzo = scannerLinea.next();
			}				
			Stanza stanza = new StanzaBuia(nomeStanza, nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, stanza);
		}		
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		try (Scanner scanner = new Scanner(string)) {
			scanner.useDelimiter(",");
			while(scanner.hasNext()) {
				result.add(scanner.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specifiche : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specifiche)) 	{	
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(Direzione.valueOf(dir), arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + ((LineNumberReader) this.reader).getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}