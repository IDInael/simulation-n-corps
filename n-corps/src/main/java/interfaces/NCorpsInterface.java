/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import serveur.Corps;

/**
 *
 * @author dinael
 */
public interface NCorpsInterface extends Remote
{
    public void compute(int i) throws RemoteException; 
    public int hasNext() throws RemoteException;
    public void connect() throws RemoteException;
    public Corps get(int i) throws RemoteException;
    public int getTaille()throws RemoteException;
}
