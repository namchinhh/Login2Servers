/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Nguyen Thanh Nam
 */
public interface ServerService extends Remote{
    public boolean login(Account a) throws RemoteException;
    public String signUp(Account a) throws RemoteException;
}
