/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufr.sciencestech.n.corps;

import java.awt.Color;

/**
 *
 * @author dinael
 */
public class Corps 
{
    private double m; //masse du corps
    private Position pos; //position du corps par rapport a l'origine
    private double vx,vy; //vitesse
    private double ax,ay; //acceleration
    private int r; //rayon du corps
    private String name;
    private Color c;//couleur
    
    public Corps()
    {
        this.m=0;
        this.pos=new Position();
        this.ax=0;
        this.ay=0;
        this.vx=0;
        this.vy=0;
        this.r=1;
        this.name = "";
        c=new Color(0,0,0);
    }
    
    public Corps(String name, double m,Position p,Color c)
    {
        this.name=name;
        this.m=m;
        this.pos=p;
        this.ax=0;
        this.ay=0;
        this.vx=0;
        this.vy=0;
        this.r=50;
        this.c=c;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public Color getC() {
        return c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public double getMasse() {
        return m;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getAx() {
        return ax;
    }

    public void setAx(double ax) {
        this.ax = ax;
    }

    public double getAy() {
        return ay;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }

    public void updateAcc(double ax, double ay)
    {
        this.ax=ax;
        this.ay=ay;
    }
    
    public void updateV(double vx, double vy)
    {
        this.vx=vx;
        this.vy=vy;
    }

    public int getRayon() {
        return r;
    }

  /*  public void updatePos(double xi, double yi)
    {
        double x=pos.getX()+xi;
        double y=pos.getY()+yi;
        
        this.pos=new Position(x,y);
    }*/
    
    @Override
    public String toString()
    {
        return this.name + " {m: "+this.m+", v: ("+this.vx+","+this.vy+"), a: ("+this.ax+","+this.ay+"), pos: "+this.pos+" }";
    }
    
}
