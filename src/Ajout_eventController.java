

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import Entity.Evenement;
import Service.ServiceEvenement;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Ajout_eventController implements Initializable {

    @FXML
    private ImageView Image;
    private TextField tfdepart;
    private TextField tfdestination;
    private TextField tfprix;
    private TextField tfnbr;
    private DatePicker tfdate;
    @FXML
    private TextField nom_eventt;
    @FXML
    private TextField desc_event;
    @FXML
    private TextField lieu_eventt;
    @FXML
    private DatePicker df_event;
    @FXML
    private DatePicker dd_event;
    @FXML
    private ImageView Image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        ServiceEvenement ser = new ServiceEvenement();

         String nom_event = nom_eventt.getText() ; 
                String lieu_event = lieu_eventt.getText() ; 
                 String description = desc_event.getText() ;
                  
        LocalDate date1 = dd_event.getValue() ; 
         LocalDate date2 = df_event.getValue() ; 

            //    int nbrplace =Integer.parseInt(tfnbr.getText()) ; 
              //  int prix =Integer.parseInt(tfprix.getText()) ; 

        
   

        if (nom_event.isEmpty() || lieu_event.isEmpty() || description.isEmpty()  || date1 == null  || date2 == null   ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("s'il vous plaît remplir tous les champs !");
            alert.showAndWait();
        }
        else if ( date1.isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez saisir une date valide.");
        alert.showAndWait();
        
   
        
    }else if ( date2.isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez saisir une date valide.");
        alert.showAndWait();
        
   
        
    }else {
            Evenement p = new Evenement( nom_event, lieu_event,description, java.sql.Date.valueOf(date1), java.sql.Date.valueOf(date2));

            try {
                ser.ajouter(p);
        String subject = "";
              String body = "" ; 
           //     String body = "Hello " + u.getNom() + ",\n\nYour carpool from " + depart + " to " + destination + " on " + date.toString() + " has been successfully added to the system.\n\nThank you for using our service!\n\nBest regards,\nThe Carpool Team";
              
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            JOptionPane.showMessageDialog(null, "evenement ajouter avec succes");
            nom_eventt.clear();
                        lieu_eventt.clear();
            desc_event.clear();
          dd_event.setValue(null) ; 
df_event.setValue(null) ; 


 
        }
        } 

        


       
        
         
   
    
    @FXML
     private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui/modifier_event.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    
    private void goaffi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Afficher_event.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sponsers(ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Afficher_sponsor.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();}

private void sendEmail() {
    // Paramètres SMTP
      String host = "smtp.gmail.com";
      String port = "587";
      String username = "chebilidali22@gmail.com";
      String password = "ka3boura";
      
      // Propriétés de la session
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", port);
      
      // Création de la session
      Session session = Session.getInstance(props, new Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });
      
      try {
         // Création du message
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dalichebili22@gmail.com"));
         message.setSubject("Sujet du message");
         message.setText("Corps du message");
         
         // Envoi du message
         Transport.send(message);
         
         System.out.println("Message envoyé avec succès!");
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}





   /* private void goaffi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void gosupp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/Supprimer_Covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

   

    private void goreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/reserver_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void mesreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/mes_reservation.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public static boolean isNumberWithinLengthLimit(String input) {
    try {
        if (input == null || input.isEmpty()) {
        return false; // input cannot be empty or null
    }
        Integer.parseInt(input); // check if input can be parsed as an integer
        return true; // input is a valid number within the length limit
    } catch (NumberFormatException e) {
        return false; // input is not a valid number
    }
}
*/
 
    
 
  

    
 
