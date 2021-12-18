/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import serveur.NCorps;

/**
 *
 * @author dinael
 */
public class Fenetre extends JFrame
{
    JPanel pan; //panneau principal
    Plan2D repere;
    JTextArea affichage;
    private String ip;
    NCorps nc;
    
    public Fenetre(NCorps nc,String ip)
    {
        super();
        this.nc=nc;
        repere=new Plan2D(this.nc.getSysteme());
        affichage=new JTextArea();
        this.ip=ip;
        
        init();
    }
    
    private void init()
    {
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        
        int l=(int)size.getWidth();
        int h=(int)size.getHeight()-100;
        repere.setBackground(Color.black);
        //repere.setPreferredSize(new Dimension(l,h));
        repere.setMaximumSize(new Dimension(900,900));
        
        this.setPreferredSize(size);
        this.getContentPane().add(this.repere,java.awt.BorderLayout.CENTER);
        this.setBackground(Color.BLACK);
        
        affichage.enable(false);
        affichage.setBackground(Color.black);
        
        this.getContentPane().add(this.affichage,java.awt.BorderLayout.SOUTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        String info="IP serveur : "+this.ip+"\n"+this.nc;
        this.affichage.setText(info);
        this.repere.paint(g);
    }
}
