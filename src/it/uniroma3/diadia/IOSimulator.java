package it.uniroma3.diadia;

public class IOSimulator implements IO {

    private String[] righeDaLeggere;
    private String[] messaggiScritti;
    private int indiceLettura;
    private int indiceScrittura;

    public IOSimulator(String[] righeDaLeggere) {
        this.righeDaLeggere = righeDaLeggere;
        this.messaggiScritti = new String[100];
        this.indiceLettura = 0;
        this.indiceScrittura = 0;
    }
    
    // Output
    @Override
    public void mostraMessaggio(String messaggio) {
        if (indiceScrittura < messaggiScritti.length)
            messaggiScritti[indiceScrittura++] = messaggio;
        else
        	System.out.println("Troppi messaggi stampati, array pieno.");
    }
    
    // Input
    @Override
    public String leggiRiga() {
        if (indiceLettura < righeDaLeggere.length)
            return righeDaLeggere[indiceLettura++];
        else
            return "fine";
    }

    public String[] getMessaggiScritti() {
        return this.messaggiScritti;
    }

    public int getNumeroMessaggiScritti() {
        return this.indiceScrittura;
    }
}

