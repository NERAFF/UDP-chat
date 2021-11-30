package chat;

import java.net.UnknownHostException;

/**
 *
 * @author Franceluca
 */
public class gestioneEventi {

    Condivisa c;

    public gestioneEventi() throws UnknownHostException {
        c = new Condivisa();
    }

    public gestioneEventi(Condivisa c) {
        this.c = c;
    }

    public void connetti() {
        c.aggiungiPacchettoI("c" + c.getNicknameMittente());
    }

    public void disconnetti() {
        c.connected = false;
    }

    public void invia(String messaggio) {
        c.aggiungiPacchettoI("m" + messaggio);
    }

    public boolean accetta() {
        return true;
    }

    public void aggiungiNickFrame(String name) {
        c.frame.SetlabelNome("CHAT CON:"+name);
    }
    public void aggiungiChatVis(String m) {
        c.frame.SetlabelChat(m);
    }
}
