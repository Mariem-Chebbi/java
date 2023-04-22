/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import utils.SMSSender;

/**
 *
 * @author Chebbi_Mariem
 */
public class TestSMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SMSSender.sendSMS("test api sms", "20744656");
    }
    
}
