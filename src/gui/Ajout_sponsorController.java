/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Evenement;
import Entity.Sponsor;
import Service.ServiceEvenement;
import Service.ServiceSponsor;
import java.net.*;
import java.io.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import java.time.LocalDate;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileOutputStream;    
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Ajout_sponsorController implements Initializable {
  
    
   @FXML
    private Button button;
    @FXML
    private VBox vb;
HBox column1 = new HBox();
    private static int c,h;
    private static String a,e,f; 
 List<Sponsor> l1 = null;
    @FXML
    private Label nom_eventt;
    @FXML
    private Label lieuu;
    @FXML
    private Label desc_event;
    @FXML
    private Label df;
    @FXML
    private Label dd;
    @FXML
    private TextField nom_sponsorr;
    @FXML
    private TextField typee;

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        
        
        vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);
        //utilisateur u = new utilisateur(3, "abbes", "benabbes", "24076282", "mail", "+21624076282", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

        List<Evenement> l = new ArrayList<Evenement>();
        ServiceEvenement ser = new ServiceEvenement();
        ServiceSponsor serv = new ServiceSponsor() ; 
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Evenement d : l) {

        
            HBox hbox = new HBox();

            Label label1 = new Label(d.getNom_event());
            Label label2 = new Label(d.getLieu_event());
             Label label3= new Label(d.getDescription());
              Label label4 = new Label(d.getDate_debut().toString());
               Label label5 = new Label(d.getDate_fin().toString());
            
           label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
            
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
        Button button2 = new Button("Ajouter !");
                                     button2.setStyle("-fx-background-color: #596643 ; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
 a = d.getDescription();
 c =    d.getId();
e = d.getNom_event(); 
 f = d.getLieu_event();

       ServiceSponsor servx = new ServiceSponsor();

         String nom_sponsor = nom_sponsorr.getText() ; 
                String type = typee.getText() ; 
                
                if (nom_sponsor.isEmpty() || type.isEmpty()  ) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setContentText("s'il vous plaît remplir tous les champs !");
            alert1.showAndWait();
        } else{
    
                Sponsor  k = new Sponsor(c,nom_sponsor,type) ;
                try {
                    serv.ajouter_sponsor(k);
                    
                    
// l1 =  serv.find_reservation_cov_of_user_rech(d.getId_cov(), u.getId_utilisateur()) ;

/*if(l1.size() > 0) {
Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText(" vous êtes deja inscrit dans ce covoiturage");
alertt.showAndWait(); 

return;
} else

if ( d.getNbrplace() <=	0) { 
Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText("covoiturage complet");

// Add custom CSS stylesheet to change the color of the alert box
DialogPane dialogPane = alertt.getDialogPane();
dialogPane.getStylesheets().add(getClass().getResource("reserver_covoiturage.css").toExternalForm());
dialogPane.getStyleClass().add("my-alert");

alertt.showAndWait();                     return;
}else {

sendSms(d.getTelephone() ,"salut" + d.getNom() +  "vous avez une nouvelle reservation sur votre covoiturage") ;

JOptionPane.showMessageDialog(null, "votre réservation est confirmée"); }
button2.setVisible(false);
                */              } catch (SQLException ex) {
                    Logger.getLogger(Ajout_sponsorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "evenement ajouter avec succes"); }
 
});

       hbox.getChildren().addAll(label1, label2, label3, label4 , label5 , button2);
            hbox.setSpacing(60);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }

    @FXML
    private void sponsers(ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Afficher_sponsor.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gomodif(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Modifier_sponsor.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
 



    
    
       
     

     
     
}
   
        
   

