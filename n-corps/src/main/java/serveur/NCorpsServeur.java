/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serveur;

import gui.Fenetre;
import java.awt.Color;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinael
 */
public class NCorpsServeur 
{
    public static void main(String args[])
    {
        try
        {
            /**
             * initialisation de la classe NCorps avec les données du système solaire
             */
            NCorps s= new NCorps("Systeme solaire");
            s.add(new Corps(3.3E23, 0, 4.7E10, 5.9E4, 0, new Color(241, 203, 131),40, "Mercure"));
            s.add(new Corps(4.9E24, 0, 1.1E11, 3.5E4, 0, new Color(243, 223, 107),55, "Venus"));
            s.add(new Corps(6.0E24, 0, 1.5E11, 3.0E4, 0, new Color(173, 231, 247),100, "Terre"));
            s.add(new Corps(6.4E23, 0, 2.1E11, 2.6E4, 0, new Color(223, 120, 036),50, "Mars"));
            s.add(new Corps(1.9E27, 0, 7.4E11, 1.3E4, 0, new Color(243, 131, 239),725, "Jupiter"));
            s.add(new Corps(5.6E26, 0, 1.3E12, 1.0E4, 0, new Color(118, 064, 045),600, "Saturne"));
            s.add(new Corps(8.7E25, 0, 2.7E12, 7.1E3, 0, new Color(157, 221, 250),175, "Uranus"));
            s.add(new Corps(1.0E26, 0, 4.4E12, 5.5E3, 0, new Color(045, 86, 148),300, "Neptune"));

            s.add(new Corps(1.989*Math.pow(10,30), 0, 0, 0,    0, new Color(246, 244, 129),1000, "Soleil"));
            
            /**
             * enregistrement dans l'annuaire
             */
            Registry registry= LocateRegistry.createRegistry(1099);
            
            InetAddress ip= InetAddress.getLocalHost();
            
            //Registry registry= LocateRegistry.getRegistry();
            System.setProperty("java.rmi.server.hostname",ip+"");
            
            Naming.rebind("rmi://"+ip.getHostAddress()+":1099/ncorps", s);
            System.out.println("Serveur "+ip.getHostAddress()+" en attente de calcul...");
            
            Fenetre f=new Fenetre(s,ip.getHostAddress());
            f.pack();
            f.setVisible(true);
            
            Thread t1=new Thread(new Runnable()
            {
                public void run()
                {
                    s.runSimulation();
                }
            });
           // t1.start();
            /**
             * gestion affichage
             */
            Thread th=new Thread(new Runnable()
            {
                public void run()
                {
                    while(true)
                    {
                        f.repaint();
                        try
                        {
                            Thread.currentThread().sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(NCorpsServeur.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
            });
            th.start();
            
            
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            Logger.getLogger(NCorpsServeur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(NCorpsServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
