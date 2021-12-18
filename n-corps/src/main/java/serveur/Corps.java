/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serveur;

import interfaces.CorpsFactory;
import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author dinael
 */
public class Corps extends UnicastRemoteObject implements CorpsFactory 
{
    private final double m; //masse de l'objet
    private double ax,ay; //acceleration
    private double vx,vy; //vitesse
    private double x,y; //position
    private String name; //nom du corps
    private final int r; //rayon du corps
    private final Color c;//couleur associé au corps
    
    public Corps(double m, double x0, double y0, double vx0, double vy0, Color c,int r, String name) throws RemoteException
    {
        this.m=m;
        this.x=x0; this.y=y0;
        this.vx=vx0; this.vy=vy0;
        this.c=c;
        this.name=name;
        this.r=r;
        this.ax=0; this.ay=0;
    }

    public double getAx() {
        return ax;
    }

    public double getAy() {
        return ay;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public int getR() {
        return r;
    }

    public Color getC() {
        return c;
    }
    
    

    @Override
    public double getM() throws RemoteException {
        return this.m;
    }

    @Override
    public double getX() throws RemoteException {
        return this.x;
    }

    @Override
    public double getY() throws RemoteException {
        return this.y;
    }
    
    public double getDistance(Corps p)
    {
        double xi=this.x;
        double yi= this.y;
        try
        {
             xi-=p.getX();
             yi-=p.getY();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
        return Math.sqrt(xi*xi+yi*yi);
    }
    
    /**
     * mise a jour de la vitesse
     */
    public void updateV(double vtx, double vty)
    {
        this.vx=vtx;
        this.vy=vty;
    }
    
    /**
     * mise a jour de l'acceleration
     * @param atx : acceleration sur l'axe x a l'instat t+dt
     * @param aty : acceleration sur l'axe y a l'instat t+dt
     */
    public void updateAcc(double atx, double aty)
    {
        this.ax=atx;
        this.ay=aty;
    }
    
    /**
     * mise a jour de la position a l'instant t+dt
     * @param x1 : position sur l'axe x 
     * @param y1 : position sur l'axe y
     */
    public void updatePos(double x1, double y1)
    {
        this.x=x1;
        this.y=y1;
    }
    
    @Override
    public String toString()
    {
        return this.name + " {m: "+this.m+", v: ("+this.vx+","+this.vy+"), a: ("+this.ax+","+this.ay+"), pos: ("+this.x+","+this.y+") }";
    }
    
}
