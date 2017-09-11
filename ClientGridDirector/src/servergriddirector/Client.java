/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servergriddirector;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class Client extends Thread {

    InputStream in;
    OutputStream out;
    String ip;
    Socket client;
    BufferedImage imm;
    ArrayList<Point> punti;
    ArrayList vettPunti;
    Immagine panel;
    PrintWriter doubleout;
    double ris;

    public void setPanel(Immagine pan) {
        panel = pan;
    }

    public Client(String IP) throws IOException {
        imm = null;
        punti = new ArrayList<Point>();
        ip = IP;
    }

    public double calcolaDensità() {
        System.out.println("Calcolo Densità");
        for(int i=0;i<imm.getWidth();i++){
            for(int j=0;j<imm.getHeight();j++){
                if(imm.getRGB(i, j)==Color.WHITE.getRGB()){
                    punti.add(new Point(i,j));
                }
            }
        }
        double somma=0;
        int c=0;
        for(int i=0;i<punti.size()-1;i++){
            for(int j=(i+1);j<punti.size();j++){
                somma += Math.pow(Math.pow((punti.get(i).x-punti.get(j).x),2)+Math.pow((punti.get(i).y-punti.get(j).y),2),(double)1/2);
                c++;
            }
        }
        return (double)(somma/c);

    }

    @Override
    public void run() {
        if (imm == null) {
            try {
                System.out.println("Client creato");
                client = new Socket(ip, 5000);
                imm = ImageIO.read(client.getInputStream());
                panel.setImmagine(imm);
                if (imm != null) {
                    System.out.println("Immagine ricevuta->X:" + imm.getWidth() + " Y:" + imm.getHeight());
                    System.out.println("RIUSCITO");
                    ris = calcolaDensità();
                    System.out.println(ris);
                    doubleout = new PrintWriter(client.getOutputStream());
                    doubleout.print(ris);
                    doubleout.flush();
                } else {
                    System.out.println("FALLITO");
                }

            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        panel.repaint();

        
    }
}
            //System.out.println("");

