/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufr.sciencestech.n.corps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author dinael
 */
public class Repere extends JPanel 
{
    ArrayList<Corps> liste;
    
    public Repere(ArrayList<Corps> ls)
    {
        super();
        this.liste=ls;
    }
    
    private void drawCorps(Graphics g,Corps c)
    {
       // long x=(int)(Math.log10(c.getPos().getX())*10+this.getWidth()/2);
        //long y=(int)(Math.log10(c.getPos().getY())*10+this.getHeight()/2);
        double e=20*10e7;
        
        double x=c.getPos().getX()/e+this.getWidth()/2;
        double y=c.getPos().getY()/e+this.getHeight()/4;
        
        Graphics2D g2=(Graphics2D)g;
        
        int r=(int) Math.log(c.getRayon());
        
        Ellipse2D p = new Ellipse2D.Double(x,y,2*r,2*r);//planete
        //g2.setColor(c.getC());
        //g2.draw(new Ellipse2D.Double(x,y,2*r,2*r));
        g2.setPaint(c.getC());
        g2.fill(p);
        g2.setColor(Color.black);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for(Corps c:liste)
        {
            this.drawCorps(g, c);
        }
       // System.out.println("repaint");
    }
    
    
}
