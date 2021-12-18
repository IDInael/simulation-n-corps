/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufr.sciencestech.n.corps;

/**
 *
 * @author dinael
 */
public class Position 
{
    private double x;
    private double y;
    
    public Position()
    {
        this.x=0;
        this.y=0;
    }
    
    public Position(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    @Override
    public String toString()
    {
        return "("+this.x+","+this.y+")";
    }
    
    public double distance(Position p1)
    {
        double x=p1.getX()-this.x;
        double y=p1.getY()-this.y;
        
        return Math.sqrt(x*x+y*y);
    }
 
 
}
