/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package griddirector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author utente
 */
public final class Immagine extends JPanel {

    BufferedImage imm;
    int nPunti;
    ArrayList<Point> punti = new ArrayList();
    Random random = new Random();
    ArrayList<BufferedImage> immagine = new ArrayList();
    int nNodi;
    Server server;

    public Immagine(int point, int w, int h, int nodi) {
        setBounds(0, 0, w, h);
        this.nNodi = nodi;
        nPunti = point;
        creaPunti();
        imm = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < punti.size(); i++) {
            imm.setRGB(punti.get(i).x, punti.get(i).y, Color.WHITE.getRGB());
        }
        for (int i = 0; i < nNodi; i++) {
            immagine.add(imm.getSubimage(0, i * (h / nNodi), w, h / nNodi));
        }
        // <editor-fold defaultstate="collapsed" desc="Controllo immagine"> 
        /*
         int i = 0;
         while (i < nNodi) {
         for (int j = 0; j < w; j++) {
         for (int k = 0; k < h/nNodi; k++) {
         if(immagine.get(i).getRGB(j, k)==Color.WHITE.getRGB()){
         System.out.println(j+" "+k);
         System.out.println("Settore "+i);
         }
         }
         }
         i++;
         }
         */
        //</editor-fold>

        try {
            server = new Server(nNodi);
            server.setImage(immagine);
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(FinestraInizio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Immagine() {
        nPunti = 50000;
    }

    public void creaPunti() {
        int x, y;
        int i = 0;
        while (i < nPunti) {
            x = (int) (Math.random() * getWidth());
            y = (int) (Math.random() * getHeight());
            punti.add(new Point(x, y));
            i++;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(imm, 0, 0, this);
    }
}
