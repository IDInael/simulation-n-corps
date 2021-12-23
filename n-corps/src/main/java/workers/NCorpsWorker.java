/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workers;

import interfaces.CorpsFactory;
import interfaces.NCorpsInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.Corps;

/**
 *
 * @author dinael
 */
public class NCorpsWorker 
{
    public static void main(String args[])
    {
                NCorpsInterface s=null;
                try
                {
                    Scanner sc= new Scanner(System.in);
                    System.out.println("IP server : ");
                    String ip = sc.nextLine();
                    s=(NCorpsInterface)Naming.lookup("rmi://"+ip+":1099/ncorps");
                }
                catch(Exception e)
                {
                    Logger.getLogger(NCorpsWorker.class.getName()).log(Level.SEVERE, null, e);
                }

                if(s!=null)
                {
                    try
                    {
                        /**
                        * on effectue le calcul tant le temps n'est pas atteint
                        */
                        int i;
                        s.connect();
                        
                        while((i=s.hasNext())!=-1)
                        {
                            System.out.println("Compute value of body in "+i);
                            s.compute(i);
                            //CorpsFactory c=(CorpsFactory) s.get(i);
                            //System.out.println(c+" \n");
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(NCorpsWorker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
}
