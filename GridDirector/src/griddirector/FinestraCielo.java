/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddirector;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Juri
 */
public class FinestraCielo extends JFrame {
    
    int nNodi;
    int nStelle;
    Immagine imm;
    ArrayList<JPanel> arraypannelli = new ArrayList<>();
    
    public FinestraCielo(int l, int a, int stelle,int nNodi) {
        setResizable(true);
        this.nNodi=nNodi;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(0,0,l,a);
        this.setVisible(true);
        imm = new Immagine(stelle, getWidth(), getHeight(),nNodi);
        add(imm);
        imm.repaint();
        this.setSize(l+100, a+38);
        for(int i=0;i<nNodi;i++){
            arraypannelli.add(new JPanel());
            arraypannelli.get(i).setBounds(l,(int)(i*(double)(a/nNodi)), 100, (int)(a/nNodi));
            arraypannelli.get(i).setVisible(true);
            arraypannelli.get(i).setBackground(new Color((int)((i+1)*(Math.random()*255))%255,0,0));
            add(arraypannelli.get(i));
        }
    }
}
