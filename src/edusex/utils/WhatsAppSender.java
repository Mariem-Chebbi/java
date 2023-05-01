/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.utils;

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
            connection.setRequestProperty("Authorization", "Bearer EAAIYzeadInkBAFv1hT6omBPSnZCZBpdqdhMLPqcvjDj1b0PTP9vWZAt3uX3g9N1OMJVSBbWjg662dOm6RnGKxbmdcFvYHS3Fh8uNNbJgm7rmGMlGG8mGNL2Kzdv8iU9Fcg6LPpJPCKpG8WV8i9qvN9C9hN0imykgthnzw5d9xjbB12PiIPR7pglVJ6tP3RgrU7ZAKsleJnzO6FGmJ76a8XEEF4TLrcwZD");
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
