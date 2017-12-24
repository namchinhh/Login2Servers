/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Nguyen Thanh Nam
 */
public class ClientControl {
    private String serverHost = "localhost";
    private int serverPort = 1999;
    private ServerService rmiService;
    private Registry registry;
    private String rmiServiceName = "rmiService";

    public ClientControl() {
        try {
            registry = LocateRegistry.getRegistry(serverHost, serverPort);
            rmiService = (ServerService) registry.lookup(rmiServiceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public String signUp(Account a) {
        try {
            return rmiService.signUp(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean login(Account b) {
        try {
            return rmiService.login(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
