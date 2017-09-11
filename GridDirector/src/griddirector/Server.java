/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package griddirector;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Server extends Thread {

    ServerSocket server;
    Socket client = new Socket();
    InputStreamReader in;
    OutputStreamWriter out;
    BufferedReader doublein;
    ArrayList<BufferedImage> immagine = new ArrayList<BufferedImage>();
    ArrayList<Double> ris;
    int nNodi;

    public Server(int nodi) throws IOException {
        server = new ServerSocket(5000);
        nNodi = nodi;
    }

    public void setImage(ArrayList<BufferedImage> imm) {
        immagine = imm;
    }

    @Override
    public void run() {
        try {
            System.out.println("Attesa connessione su-> " + server.getLocalPort() + "...");
            //assegno al client l'indirizzo del server
            int i = 0;
            while (i < nNodi) {
                client = server.accept();
                if (client.isConnected()) {
                    System.out.println("Connessione avvenuta con-> " + client.getRemoteSocketAddress());
                    ImageIO.write(immagine.get(i), "png", client.getOutputStream());
                    doublein = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    System.out.println("densita: "+doublein.readLine());
                    
                }
                i++;
            }
            i=0;
            while (i < nNodi) {
                client = server.accept();
                if (client.isConnected()) {
                    
                }
                i++;
            }
            
            server.close();
        } catch (SocketTimeoutException s) {
            System.out.println("Connessoine non avvenuta: ERRORE!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
