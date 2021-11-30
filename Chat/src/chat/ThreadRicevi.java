package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franceluca
 */
public class ThreadRicevi extends Thread {

    private DatagramSocket ricevi;
    private Condivisa c;
    private gestioneEventi g;
    String messaggio;

    public ThreadRicevi() throws SocketException, UnknownHostException {
        this.ricevi = new DatagramSocket(2003);
        this.c = new Condivisa();
    }

    public ThreadRicevi(Condivisa c) throws SocketException {
        this.ricevi = new DatagramSocket(2003);
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            byte[] bufferRicevi = c.cancellaPrimoI().getBytes();
            DatagramPacket packet = new DatagramPacket(bufferRicevi, bufferRicevi.length);
            try {
                ricevi.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(ThreadRicevi.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] dataReceived = packet.getData();
            String pacchetto = new String(dataReceived, 0, packet.getLength());
            //split
            String[] parts = pacchetto.split(";");
            String comando = parts[0]; // comando
            messaggio = parts[1]; // messaggio

            if (comando.equals("c") && c.connected == true) {//se c'è una connessione stabilita si nega il tentativo di connessione dell'altro peer
                c.connected = false;//nega connessione
            } 
            
            
            else {//connessione non stabilita

                if (comando.equals("c")) {//se non c'è connessione

                    c.setNicknameDestinatario(messaggio);//setto nick del peer
                    c.aggiungiPacchettoI("y" + c.getNicknameMittente());//invio pacchetto conferma che voglio stabilire connessione
                    c.connected = true;//connessione stabilita

                } else if (comando.equals("y")) {//arriva risposta dal peer che accetta di stabilire connessione
                    if (c.connected == false) {//se non è stabilita connessione
                        if (g.accetta()) {
                            c.setNicknameDestinatario(messaggio);
                            c.aggiungiPacchettoI("y" + c.getNicknameMittente());
                            c.connected = true;
                        } else {
                            c.aggiungiPacchettoI("n");
                        }
                    } else {
                        if (g.accetta()) {
                            c.aggiungiPacchettoI("y");
                            c.connected = true;
                        } else {
                            c.aggiungiPacchettoI("n");
                        }
                    }
                } else if (comando.equals("n")) {//connessione annullata
                    c.connected = false;
                } else if (comando.equals("m")) {//messaggio
                    g.aggiungiNickFrame(c.getNicknameDestinatario());
                    g.aggiungiChatVis(messaggio);

                } else if (comando.equals("e")) {//chiusura
                    c.connected = false;
                } else if (comando.equals("d")) {
                    c.aggiungiPacchettoI("n");
                }
            }
        }
    }
}
