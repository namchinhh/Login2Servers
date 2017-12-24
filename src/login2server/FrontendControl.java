/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Nguyen Thanh Nam
 */
public class FrontendControl extends UnicastRemoteObject implements ServerService{
    private int FrontendPort = 1999;
    private int BackendPort = 1995;
    private Registry registry;
    private FrontendView frontendView;
    private String rmiServiceName = "rmiService";
    private Socket mySocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    public FrontendControl(FrontendView frontendView) throws RemoteException{
        this.frontendView = frontendView;
        frontendView.showMessage("FrontendServer is running...");
        try {
            registry = LocateRegistry.createRegistry(FrontendPort);
            registry.rebind(rmiServiceName, this);
            mySocket = new Socket("localhost",BackendPort);
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String validate(Account a){
        String username = a.getUsername();
        String password = a.getPassword();
        if(username.length() <= 8 || password.length() <= 8)
            return "Username and Password must be greater than 8.";
        if(username.contains(" ") || password.contains(" "))
            return "Username and Password must not contain space.";
        if(!username.matches(".*\\d+.*") || !password.matches(".*\\d+.*"))
            return "Username and Password must contain at least one digit.";
        if(!username.matches(".*[a-zA-Z]+.*") || !password.matches(".*[a-zA-Z]+.*"))
            return "Username and Password must contain at least one alphabet.";
        return "";
    }
    @Override
    public boolean login(Account a) {
        
        try {
            oos.writeObject(2);
            oos.writeObject(a);
            return (Boolean)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String signUp(Account a) {
        if(!validate(a).equals(""))
            return validate(a);
        try {
            oos.writeObject(1);
            oos.writeObject(a);
            if((Boolean)ois.readObject())
                return "Register Success.";
            else
                return "Username is already exist.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
