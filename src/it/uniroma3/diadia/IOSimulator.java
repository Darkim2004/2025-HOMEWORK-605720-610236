package it.uniroma3.diadia;

import java.util.LinkedList;

public class IOSimulator implements IO {

    private LinkedList<String> righeDaLeggere;
    private LinkedList<String> messaggiScritti;
    private int indiceLettura;
    private int indiceScrittura;

    public IOSimulator(LinkedList<String> righeDaLeggere) {
        this.righeDaLeggere = righeDaLeggere;
        this.messaggiScritti = new LinkedList<>();
        this.indiceLettura = 0;
        this.indiceScrittura = 0;
    }

	// Output
    @Override
    public void mostraMessaggio(String messaggio) {
    	messaggiScritti.add(messaggio);
    }
    
    // Input
    @Override
    public String leggiRiga() {
        if (indiceLettura < righeDaLeggere.size()) {
            return righeDaLeggere.get(indiceLettura++);
        }
        else
            return "fine";
    }

    public LinkedList<String> getMessaggiScritti() {
        return this.messaggiScritti;
    }

    public int getNumeroMessaggiScritti() {
        return this.indiceScrittura;
    }
}

