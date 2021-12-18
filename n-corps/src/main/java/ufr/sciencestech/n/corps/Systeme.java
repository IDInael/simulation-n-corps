/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufr.sciencestech.n.corps;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinael
 */
public class Systeme 
{
    private ArrayList<Corps> systeme;
    private String name;
    public final double G=6.6742E-11;
    
    public Systeme()
    {
        this.systeme= new ArrayList<Corps>();
        this.name="systeme";
    }
    
    public Systeme(String name)
    {
        this.systeme= new ArrayList<Corps>();
        this.name=name;   
    }

    public ArrayList<Corps> getSysteme() {
        return systeme;
    }

    public void setSysteme(ArrayList<Corps> systeme) {
        this.systeme = systeme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public void add(Corps c)
    {
        if(c!=null)
            this.systeme.add(c);
    }
    
    public ArrayList<Corps> getAllVoisins(int i)
    {
        ArrayList<Corps> v=(ArrayList<Corps>) this.systeme.clone();
        v.remove(i);
        
        return v;
    }
    
    public String toString()
    {
        String res=this.name+": \n";
        int i=1;
        
        for(Corps c:this.systeme)
        {
            res+=i+". "+c+"\n";
            i++;
        }
        return res;
    }
    
    /**
     * calcule l'acceleration au temps t du corps à la position i
     * @param i 
     */
    private void calculeNextAcc(int i)
    {
        ArrayList<Corps> v=this.getAllVoisins(i); //recupere tous les autres corps sauf le corps i
        double axi=0,ayi=0; //acceleration subi par le corps i
        Corps ci=this.systeme.get(i);
        
        for(Corps c:v)
        {
            double d=ci.getPos().distance(c.getPos());//calcule de la distance entre ci et c
            double d3=d*d*d;//d^3
            
            axi-=G*c.getMasse()/d3*(ci.getPos().getX()-c.getPos().getX());
            ayi-=G*c.getMasse()/d3*(ci.getPos().getY()-c.getPos().getY());
        }
        
        ci.updateAcc(axi, ayi);
    }
    
    /**
     * calcule la position suivante du corps i
     * @param dt : delta t 
     */
    private void calculNextPos(Corps ci, double  dt)
    {        
        double xi=ci.getPos().getX()+dt*ci.getVx()+dt*dt*ci.getAx()/2;
        double yi=ci.getPos().getY()+dt*ci.getVy()+dt*dt*ci.getAy()/2;
        
        ci.setPos(new Position(xi,yi));         
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
    
    public void eulerMethod()
    {
        for(int dt=0;dt<31536000;dt+=360)
        {
            for(int i=0;i<this.systeme.size();i++)
            {
                Corps ci=this.systeme.get(i);
                
                double rx=ci.getPos().getX()+dt*ci.getVx();
                double ry=ci.getPos().getY()+dt*ci.getVy();
                ci.setPos(new Position(rx,ry));
                
                double vx=ci.getVx()+dt*ci.getAx();
                double vy=ci.getVy()+dt*ci.getAy();
                ci.updateV(vx, vy);
                
                double ax=0;
                double ay=0;
                for(int j=0;j<this.systeme.size();j++)
                {   
                    if(i!=j)
                    {
                        double dx=ci.getPos().getX()-systeme.get(j).getPos().getX();
                        double dy=ci.getPos().getY()-systeme.get(j).getPos().getY();
                        double d=Math.sqrt(dx*dx+dy*dy);
                        
                        if(d < 10e3)
                           d = 10e3;
                        
                        double d3=d*d*d;
                        
                        ax-=G*systeme.get(j).getMasse()*dx/d3;
                        ay-=G*systeme.get(j).getMasse()*dy/d3;
                        
                       /* if(ax<-10E6)
                            ax=-10E6;
                        else
                        {
                            if(ax>10E6)
                                ax=10E6;
                        }
                        
                        if(ay<-10E6)
                            ay=-10E6;
                        else
                        {
                            if(ay>10E6)
                                ay=10E6;
                        }
                        */
                        
                        ci.setAx(ax);
                        ci.setAy(ay);
                    }
                }
            }
            //System.out.println(dt);
            
            
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void update(int i, double dt)
    {
        Corps ci=this.systeme.get(i);
        
        //on calcule l'acceleration du corps
        this.calculeNextAcc(i);
        double ax=ci.getAx();
        double ay=ci.getAy();
        
        //on met a jours la position du corps
        this.calculNextPos(ci, dt);
        
        //on calcul sa prochaine acceleration
        this.calculeNextAcc(i);
        //on met a jour la vitesse
        this.calculNextV(ci, dt, ax, ay);
    }
    
    public void runSimulation()
    {
        System.out.println("Simulation starting...");
        for(int dt=1;dt<31536000;dt=dt+360)
        {
            //a chaque intervalle de temps
            for(int i=0;i<this.systeme.size();i++)
            {
                //pour chaque objets
                this.update(i, dt);
            }
            
            //System.out.println(dt);
            System.out.println("instant t = "+dt);
           // System.out.println(this.toString());
            
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
