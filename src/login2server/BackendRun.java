/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2server;

/**
 *
 * @author Nguyen Thanh Nam
 */
public class BackendRun {
    public static void main(String[] args) {
        BackendView view = new BackendView();
        BackendControl control = new BackendControl(view);
    }
}
