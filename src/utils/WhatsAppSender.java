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
            connection.setRequestProperty("Authorization", "Bearer EAAIYzeadInkBAMHo6Xctd23NTPlatJd331v4a5ZCf7XO1x1FaZBhziZAcEOYygkwJy5DpGdNqdyIZCZBRkRVf4CunDaYh8yFpdgFS9mdms9UEJZA8v6F2gvpGoWrkwgHizxIOx9IaLUayn9E8yu8rSZCQiaxv4nwEiohbo0Ayo5qFByBZCABQorMrvNnw75mFQFLeGZBonNbb2FRTbJzCQxxyhMdugw6IvNsZD");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            String body = "{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"21620744656\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"" + msg + "\" } }";
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
