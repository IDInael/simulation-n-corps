/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import interfaces.NCorpsInterface;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import serveur.Corps;
import serveur.NCorps;

/**
 *
 * @author dinael
 */
public class Plan2D extends JPanel
{
    ArrayList<Corps> lst;
    
    public Plan2D(ArrayList<Corps> nc)
    {
        super();
        this.lst=nc;
    }
    
    private void drawCorps(Graphics g,Corps c)
    {
       try
       {
           //20*10e17
        double e=40*10e7;
        
        double x=c.getX()/e+this.getWidth()/2;
        double y=c.getY()/e+this.getHeight()/4;
        
        Graphics2D g2=(Graphics2D)g;
        
        int r=(int) Math.log(c.getR());
        
        Ellipse2D p = new Ellipse2D.Double(x,y,2*r,2*r);//planete
        g2.setPaint(c.getC());
        g2.fill(p);
        //g2.setColor(Color.black);
       }
       catch(RemoteException e)
       {
           e.printStackTrace();
       }
    }

    @Override
    public void paint(Graphics g)
    {
            super.paint(g);
            for(Corps c: lst)
            {
                this.drawCorps(g, c);
            }
    }
}
