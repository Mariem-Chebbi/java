/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.sun.mail.smtp.SMTPTransport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import utils.EmailSender;

/**
 *
 * @author Chebbi_Mariem
 */
public class TestEmailSender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EmailSender es = new EmailSender();
        es.sendEmail(java.sql.Date.valueOf(LocalDate.now()));
    }

}
