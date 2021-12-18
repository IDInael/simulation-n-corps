/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufr.sciencestech.n.corps;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 *
 * @author dinael
 */
public class Simulation
{
    public static void main(String args[])
    {
       Systeme s=new Systeme("Systeme solaire");
        
       Corps soleil,mercure, venus, terre, mars, jupiter, saturne, uranus, neptune, pluton;
       
       mercure=new Corps("mercure",3.3E24,new Position(0,4.7E10),new Color(241,203,131));
       mercure.updateV(5.9E4, 0);
       s.add(mercure);
       
       venus=new Corps("venus",4.9E24,new Position(0,1.1E11),new Color(243,223,107));
       venus.updateV(3.5E4, 0);
       s.add(venus);
       
       terre= new Corps("terre",6.0E24,new Position(0,1.5E11),new Color(173,231,247));
       terre.updateV(3.0E4, 0);
       terre.setR(250);
       s.add(terre);
       
       mars= new Corps("mars",6.4E23,new Position(0,2.1E11),new Color(223,120,36));
       mars.updateV(2.6E4, 0);
       s.add(mars);
       
       jupiter= new Corps("jupiter",1.9E27,new Position(0,7.4E11),new Color(243,131,239));
       jupiter.updateV(1.3E4, 0);
       jupiter.setR(1000);
       s.add(jupiter);
       
       saturne= new Corps("saturne",5.6E26,new Position(0,1.3E12),new Color(118,64,45));
       saturne.updateV(1.0E4, 0);
       saturne.setR(400);
       s.add(saturne);
       
       uranus= new Corps("uranus",8.7E25,new Position(0,2.7E12),new Color(157,221,250));
       uranus.updateV(7.1E3, 0);
       uranus.setR(300);
       s.add(uranus);
       
       neptune= new Corps("neptune",1.0E26,new Position(0,4.4E12),new Color(45,86,148));
       neptune.updateV(5.5E3, 0);
       neptune.setR(200);
       s.add(neptune);
       
       soleil=new Corps("soleil",1.989*Math.pow(10,30),new Position(),new Color(246,244,129));
       soleil.setR(1500);
       s.add(soleil);
      
      /**
       * gestion affichage
       */
      Repere r=new Repere(s.getSysteme());
      r.setBackground(Color.black);
      r.setPreferredSize(new Dimension(1800,900));
      
      JFrame fenetre=new JFrame();
      WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
      
      fenetre.addWindowListener(wa);
      fenetre.getContentPane().add(r);
      fenetre.pack();
      fenetre.setVisible(true);
      
      Thread th1=new Thread(new Runnable()
      {
         public void run()
         {
            // s.eulerMethod();
             s.runSimulation();
         }
      });
      th1.start();
      Thread th= new Thread(new Runnable()
      {
          public void run()
          {
              while(true)
              {
                  r.revalidate();
                  r.repaint();
                try
                {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
              }
          }
      });
      th.start();
      
    }
    
}
