/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author dinael
 */
public interface CorpsFactory extends Remote
{
    public double getM() throws RemoteException;
    public double getX() throws RemoteException;
    public double getY() throws RemoteException;
}
