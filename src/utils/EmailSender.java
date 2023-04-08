/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.mail.smtp.SMTPTransport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Chebbi_Mariem
 */
public class EmailSender {

    public EmailSender() {
    }

    public void sendEmail(Date date) {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp"); //SMTP protocol
        props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtps.auth", "true"); //enable authentication
        Session session = Session.getInstance(props, null);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("commercial.edusex@gmail.com"));////
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse("chebbimariem289@gmail.com"));
            message.setSubject("Annulation d'un rendez-vous");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = dateFormat.format(date);

            String msg = "Bonjour, Votre rendez-vous le " + formattedDate + " a été annulé";

            message.setText(msg);

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");

            st.connect("smtp.gmail.com", 465, "commercial.edusex@gmail.com", "dfcqmpohshjjdorh");
            st.sendMessage(message, message.getAllRecipients());

            Transport.send(message);
        } catch (MessagingException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
