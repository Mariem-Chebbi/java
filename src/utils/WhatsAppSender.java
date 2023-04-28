/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Chebbi_Mariem
 */
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class WhatsAppSender {

    public static void sendWhatsApp(String msg) {
        try {
            URI uri = new URI("https://graph.facebook.com/v13.0/112984521774770/messages");
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer EAAIYzeadInkBAN1CD8YVEMUXSO1fvvuDeMZBmGLMMhDg9ZC1101lUbalIuHIT7yWgiaV9PxyMLbAlT1pdCGHel6WCOoo37gL36PW5Dcn1ACjEczy0GK7W9hcQqDEObsMLIa2O43t7ZAWTCXQmahdNoZC6mHtbZBzkr1tBeYA37luwjN4QTBWwgVE6jlsL4AKZB6mD1R7mEU5NXYTlwz1t2W2Xa6EcjTfkZD");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            //String body = "{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"21620744656\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"" + msg + "\" } }";
            String body = "{ \"messaging_product\": \"whatsapp\", \"to\": \"21620744656\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }";

            writer.write(body);
            writer.flush();
            writer.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Request was successful
                System.out.println("Request was successful.");
            } else {
                // Request failed
                System.out.println("Request failed: " + responseCode);
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
