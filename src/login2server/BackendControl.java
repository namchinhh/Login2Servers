/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Thanh Nam
 */
public class BackendControl {
    private BackendView backendView;
    private int BackendPort =1995;
    private ServerSocket myServer;
    private Socket frontendSocket;

    public BackendControl(BackendView backendView) {
        this.backendView = backendView;
        openBackendServer(BackendPort);
        backendView.showMessage("Backend Server is running...");
        while (true) {            
            listenning();
        }
    }
    
    private void openBackendServer(int port){
        try {
            myServer = new ServerSocket(port);
        } catch (Exception e) {
            backendView.showMessage(e.getMessage());
        }
    }
    
    private void writeFile(ArrayList list){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Account.dat"));
            out.writeObject(list);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private ArrayList<Account> readFile(){
        ArrayList<Account> all = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Account.dat"));
            all = (ArrayList<Account>) in.readObject();
            
        }catch(NullPointerException nullPoint){
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }
    private boolean signUp(Account a){
        String username = a.getUsername();
        ArrayList<Account> list = readFile();
        try {
            for (Account account : list) {
                if(account.getUsername().equals(username))
                    return false;
            }
            list.add(a);
            writeFile(list);
        }catch(NullPointerException nullPointerException){
            list = new ArrayList();
            list.add(a);
            writeFile(list);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    private boolean login(Account a){
        
        String username = a.getUsername();
        String password = a.getPassword();
        ArrayList<Account> list = readFile();
        try {
            for (Account account : list) {
                if(account.getUsername().equals(username) && account.getPassword().equals(password))
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private void listenning(){
        try {
            frontendSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(frontendSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(frontendSocket.getOutputStream());
            while(true){
                Object choose = ois.readObject();
                if(choose instanceof Integer){
                    if((Integer) choose ==1) //sign up
                        oos.writeObject(signUp((Account)ois.readObject()));
                    else { //login
                        oos.writeObject(login((Account)ois.readObject()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
