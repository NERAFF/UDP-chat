package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franceluca
 */
public class ThreadInvio extends Thread {

    private DatagramSocket invia;
    private Condivisa c;

    public ThreadInvio() throws SocketException, UnknownHostException {
        this.invia = new DatagramSocket(2003);
        this.c = new Condivisa();
    }

    public ThreadInvio(Condivisa c) throws SocketException {
        this.invia = new DatagramSocket(2003);
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            if (!c.isEmptyInvio()) {
                byte[] bufferInvia = new byte[1500];
                DatagramPacket packet = new DatagramPacket(bufferInvia, bufferInvia.length);
                packet.setAddress(c.getIndirizzoDestinatario());
                packet.setPort(2003);
                try {
                    invia.send(packet);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadInvio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
