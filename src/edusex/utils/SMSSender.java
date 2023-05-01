/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Chebbi_Mariem
 */
public class SMSSender {

    public static final String ACCOUNT_SID = "AC987a79775fba4b2f96703bcad50c2781";
    public static final String AUTH_TOKEN = "827cc2c013e2e8480e483c95fc735dc0";

    public static void sendSMS(String msg, String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+216" + number),
                new PhoneNumber("+16813523908"),msg).create();

        System.out.println(message.getSid());
    }

}
