/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2server;

import java.util.Scanner;

/**
 *
 * @author Nguyen Thanh Nam
 */
public class ClientView {

    void showMenu() {
        ClientControl control = new ClientControl();
        int chon;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("------Menu------");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("----------------");
            System.out.println("Please choose: ");
            chon = Integer.parseInt(in.nextLine());
            switch (chon) {
                case 1: //signup
                        Account a = new Account();
                        System.out.println("Username: ");
                        a.setUsername(in.nextLine());
                        System.out.println("Password: ");
                        a.setPassword(in.nextLine());
                        System.out.println(control.signUp(a));
                    break;
                case 2: //login
                    Account b = new Account();
                    System.out.println("Username: ");
                    b.setUsername(in.nextLine());
                    System.out.println("Password: ");
                    b.setPassword(in.nextLine());
                    if (control.login(b)) {
                        System.out.println("Login Success.");
                    } else {
                        System.out.println("Wrong username or password.");
                    }
                    break;
                default:
                    System.out.println("Wrong choose.");
                    break;
            }
        }
    }
}
