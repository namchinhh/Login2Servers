
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
public class FrontendRun {
    public static void main(String[] args) {
        try {
            FrontendView view = new FrontendView();
        FrontendControl control = new FrontendControl(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
