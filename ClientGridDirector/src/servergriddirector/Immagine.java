/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servergriddirector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class Immagine extends JPanel {

    Image immagine = null;

    public Immagine() {

    }

    public void setImmagine(Image imm) {
        immagine = imm;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        if (immagine == null) {
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            g.drawImage(immagine, 0, 0, this);
        }
    }
}
