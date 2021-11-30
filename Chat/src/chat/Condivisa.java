package chat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Franceluca
 */
public class Condivisa {
    chatFrame frame;   
    List<String> pacchettiDaInviare;
    InetAddress indirizzoMittente;
    InetAddress indirizzoDestinatario;
    String nicknameMittente;
    String nicknameDestinatario;
    boolean connected;//connessione stabilita
  
    
    public Condivisa() throws UnknownHostException{
        frame = null;
        pacchettiDaInviare = new ArrayList();
        indirizzoMittente=null;
        indirizzoDestinatario = null;
        nicknameMittente="Luca Lauria";
        nicknameDestinatario="";
        connected = false;

    }
    
    public Condivisa(chatFrame frame){
        this.frame=frame;
        pacchettiDaInviare = new ArrayList();
        indirizzoMittente=null;
        indirizzoDestinatario=null;
        nicknameMittente="";
        nicknameDestinatario="";
        connected = false;

    }
    
    
    public void aggiungiPacchettoI(String p){
        pacchettiDaInviare.add(p);
    }
    
    public void cancellaPacchettoI(int i){
        pacchettiDaInviare.remove(i);
    }
    
    public String cancellaPrimoI(){
        return pacchettiDaInviare.remove(0);
    }

    public InetAddress getIndirizzoMittente() {
        return indirizzoMittente;
    }

    public void setIndirizzoMittente(InetAddress indirizzoMittente) {
        this.indirizzoMittente = indirizzoMittente;
    }

    public InetAddress getIndirizzoDestinatario() {
        return indirizzoDestinatario;
    }
    public void setIndirizzoDestinatario(InetAddress indirizzoDestinatario) {
        this.indirizzoDestinatario = indirizzoDestinatario;
    }
    
    public String getNicknameMittente() {
        return nicknameMittente;
    }

    public void setNicknameMittente(String nicknameMittente) {
        this.nicknameMittente = nicknameMittente;
    }

    public String getNicknameDestinatario() {
        return nicknameDestinatario;
    }

    public void setNicknameDestinatario(String nicknameDestinatario) {
        this.nicknameDestinatario = nicknameDestinatario;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    
    public boolean isEmptyInvio(){
        return pacchettiDaInviare.isEmpty();
    }
 
}
