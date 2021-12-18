/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serveur;

import interfaces.NCorpsInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinael
 */
public class NCorps extends UnicastRemoteObject implements NCorpsInterface 
{
    private int t; //current time for compute
    private int dt;//temps entre 2 calcul successive
    private final int ot;//overtime :  temps de fin des calculs
    private int ci; //current index to compute
    private ArrayList<Corps> systeme; //liste de corps formant un systeme
    private String name;
    private int nbWorker; //nombre de noeud connecté
    
    public NCorps(String name) throws RemoteException
    {
        this.name=name;
        this.dt=360;
        this.ot=31536000;
        this.ci=0;
        this.systeme=new ArrayList();
        this.t=0;
        this.nbWorker=0;
    }
    
    @Override
    public void connect() throws RemoteException
    {
        this.nbWorker++;
    }

    public int getNbWorker() {
        return nbWorker;
    }

    public void add(Corps c)
    {
        this.systeme.add(c);
    }
    
    public ArrayList<Corps> getSysteme()
    {
        return this.systeme;
    }
    
    private void calculeNextAcc(int i) throws RemoteException
    {
        double G=6.6742E-11;
        double axi=0,ayi=0; //acceleration subi par le corps i
        Corps ci=this.systeme.get(i);
        
        for(int j=0;j<this.systeme.size();j++)
        {
            if(i!=j)
            {
                Corps c=this.systeme.get(j);
                double d=ci.getDistance(c);//calcule de la distance entre ci et c
                double d3=d*d*d;//d^3

                axi-=G*c.getM()/d3*(ci.getX()-c.getX());
                ayi-=G*c.getM()/d3*(ci.getY()-c.getY());
            }
        }
        ci.updateAcc(axi, ayi);
    }
    
    

    @Override
    public void compute(int i) throws RemoteException 
    {
        
        Corps ci=this.systeme.get(i);
        
        //on calcule l'acceleration du corps
        this.calculeNextAcc(i);
        double ax=ci.getAx();
        double ay=ci.getAy();
        
        //on met a jours la position du corps
        this.calculNextPos(ci, t);
        
        //on calcul sa prochaine acceleration
        this.calculeNextAcc(i);
        //on met a jour la vitesse
        this.calculNextV(ci, t, ax, ay);
    }

    @Override
    public synchronized int hasNext() throws RemoteException 
    {
        if(this.t>=this.ot)
            return -1;
        else
        {
            int res=ci;
            //on met a jour l'indice courant
            ci++;
            
            //si on a parcouru tous les corps
            // on passe au temps t=t+dt
            if(ci==this.systeme.size())
            {
                ci=0;
                t+=dt;
                
                try
                {
                    Thread.currentThread().sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NCorps.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return res;
        }
    }
    
    @Override
    public String toString()
    {
        String res=this.name+" à l'instant  t : " +this.t+" worker : "+this.nbWorker+"\n" ;
        int i=1;
        
        for(Corps c:this.systeme)
        {
            res+=i+". "+c+"\n";
            i++;
        }
        return res;
    }
    
    public void runSimulation()
    {
        try
        {
            int i;
            while((i=this.hasNext())!=-1)
            {
                this.compute(i);
                
            }
        } catch (RemoteException ex) {
            Logger.getLogger(NCorps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Corps get(int i) throws RemoteException
    {
        return this.systeme.get(i);
    }

    @Override
    public int getTaille() throws RemoteException
    {
        return this.systeme.size();
    }
        
    /**
     * calcule la position suivante du corps i
     * @param dt : delta t 
     */
    private void calculNextPos(Corps ci, double  dt)
    {
        try
        {
            double xi=ci.getX()+dt*ci.getVx()+dt*dt*ci.getAx()/2;
            double yi=ci.getY()+dt*ci.getVy()+dt*dt*ci.getAy()/2;

            ci.updatePos(xi,yi);   
        } catch (RemoteException ex) {
            Logger.getLogger(NCorps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * mise a jour de la vitesse v a l'instant t+1
     * @param ci corps soumis au calcul
     * @param dt 
     * @param ax acc sur l'axe x
     * @param ay acc sur l'axe y
     */
    private void calculNextV(Corps ci, double dt, double ax, double ay)
    {
        double vx=ci.getVx()+dt*(ax+ci.getAx())/2;
        double vy=ci.getVy()+dt*(ay+ci.getAy())/2;
        
        ci.updateV(vx, vy);
    }
    
}
