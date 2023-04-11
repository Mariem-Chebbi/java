/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import services.ServiceUser;
import utils.MyDB;

/**
 *
 * @author Chebbi_Mariem
 */
public class testUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyDB.getInstance();
        ServiceUser service = new ServiceUser();
        System.out.println(service.getPersonnels());
    }
    
}
