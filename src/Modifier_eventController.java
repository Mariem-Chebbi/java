/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Evenement;
import Service.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Modifier_eventController implements Initializable {

    @FXML
    private ImageView Image;
    @FXML
    private VBox vb;
    private TextField tfdepart;
    private TextField tfdestination;
    private TextField tfprix;
    private TextField tfnbr;
    private DatePicker tfdate;
    private static int a, b;
    @FXML
    private TextField nom_eventt;
    @FXML
    private TextField lieu_eventt;
    @FXML
    private TextField desc_event;
    @FXML
    private DatePicker df_event;
    @FXML
    private DatePicker dd_event;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
    /*    tfprix.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) { // Only allow digits
            tfprix.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digits
        }
    });   
    
    tfnbr.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) { // Only allow digits
            tfnbr.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digits
        }
    });*/
        
        
       vb.getChildren().clear();
        List<Evenement> l = new ArrayList<Evenement>();
        ServiceEvenement ser = new ServiceEvenement();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (Evenement d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getNom_event());
            Label label2 = new Label(d.getLieu_event());
             Label label3 = new Label(d.getDescription());
             Label label4 = new Label(d.getDate_debut().toString());
             Label label5 = new Label(d.getDate_fin().toString());
            

            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
  
               hbox.setStyle("  -fx-background-color: #BDECB6;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 10px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                      Button button = new Button("modifier !");
                                     button.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");

  button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);

                 

   nom_eventt.setText(d.getNom_event());
   desc_event.setText(d.getDescription());
   lieu_eventt.setText(d.getLieu_event());

    dd_event.setValue(d.getDate_debut().toLocalDate());
    df_event.setValue(d.getDate_fin().toLocalDate());

   


                 });
  
  
    Button button2 = new Button("Supprimer !");
                                     button2.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");

button2.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprime(d.getId());
                     JOptionPane.showMessageDialog(null, "evenement supprimé");
                 } catch (SQLException ex) {
                    Logger.getLogger(Afficher_eventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
                
            hbox.getChildren().addAll(label1, label2, label3, label4 , label5, button , button2);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
               vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

    

    } 

    @FXML
    private void modifier(ActionEvent event) {
   
        ServiceEvenement ser = new ServiceEvenement();

        String nom_event = (String) nom_eventt.getText();
        String lieu_event = (String) lieu_eventt.getText();
        String description = (String) desc_event.getText();
       
        LocalDate date1 = dd_event.getValue() ; 
          LocalDate date2 = df_event.getValue() ; 
        


String idText = Integer.toString(a) ; 
if (nom_event.isEmpty() || lieu_event.isEmpty() || description.isEmpty()  ||  date1 == null||  date2 == null  ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("s'il vous plaît remplir tous les champs ! ");
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
            Evenement p = new Evenement(nom_event, lieu_event,description, java.sql.Date.valueOf(date1), java.sql.Date.valueOf(date2));

            try {
                ser.update(p);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        Image.setImage(null);

            Actualiser();
            JOptionPane.showMessageDialog(null, "event modifie");
            nom_eventt.clear();
                        lieu_eventt.clear();
            desc_event.clear();
            dd_event.setValue(null) ; 
df_event.setValue(null) ; 
    }
        }

     private void Actualiser() {
        vb.getChildren().clear();
        List<Evenement> l = new ArrayList<Evenement>();
        ServiceEvenement ser = new ServiceEvenement();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (Evenement d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getNom_event());
            Label label2 = new Label(d.getLieu_event());
            Label label3 = new Label(d.getDescription());
             Label label4= new Label(d.getDate_debut().toString());
              Label label5 = new Label(d.getDate_fin().toString());
           

               hbox.setStyle("  -fx-background-color: #BDECB6;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                      Button button = new Button("modifier !");
                                     button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

  button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


   nom_eventt.setText(d.getNom_event());

    lieu_eventt.setText(d.getLieu_event());
        desc_event.setText(d.getDescription());
    dd_event.setValue(d.getDate_debut().toLocalDate());
       df_event.setValue(d.getDate_fin().toLocalDate());


                 });
  
  
    Button button2 = new Button("Supprimer !");
                                     button2.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprime(d.getId());
                     JOptionPane.showMessageDialog(null, "evenement supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(Afficher_eventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
                
            hbox.getChildren().addAll(label1, label2, label3, label4 , label5, button , button2);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
               vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");


    } 

    @FXML
   private void goajou(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Ajout_event.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } 

    @FXML
    private void liste(ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Afficher_event.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } 
}

/*
    private void goaffi(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    private void goajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_covoiturage.fxml")) ; 
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

    private void Actualiser() {
        vb.getChildren().clear();
        List<Covoiturage> l = new ArrayList<Covoiturage>();
        ServiceCovoiturage ser = new ServiceCovoiturage();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (Covoiturage d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
            Label label3 = new Label(String.valueOf(d.getNbrplace()));
Label label4 = new Label(String.valueOf(d.getPrix()));
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
            Label label5 = new Label(d.getDate_covoiturage().toString());
               hbox.setStyle("  -fx-background-color: #BDECB6;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                      Button button = new Button("modifier !");
                                     button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

  button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
 a = d.getId_cov();
                 b = d.getId_utilisateur();

   tfdepart.setText(d.getDepart());

    tfdestination.setText(d.getDestination());
    tfdate.setValue(d.getDate_covoiturage().toLocalDate());
tfprix.setText(String.valueOf(d.getPrix()));
tfnbr.setText(String.valueOf(d.getNbrplace()));

                 });
  
  
    Button button2 = new Button("Supprimer !");
                                     button2.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprime(d.getId_cov());
                     JOptionPane.showMessageDialog(null, "covoiturage supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_covoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
                
            hbox.getChildren().addAll(label1, label2, label3, label4 , label5, button , button2);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
               vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");


    } 

    private void goreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/reserver_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
*/