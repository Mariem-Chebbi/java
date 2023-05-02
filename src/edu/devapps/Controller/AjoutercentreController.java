/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.Controller;

import edu.devapps.entity.Centre;
import edu.devapps.entity.Reclamation;
import edu.devapps.services.CentreService;
import edu.devapps.services.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ala20
 */
public class AjoutercentreController implements Initializable {

   @FXML
   private AnchorPane anchorme;
   @FXML
   private TextField nom_social;
   @FXML
   private TextField ville;
   @FXML
   private TextField aderesse;
   @FXML
   private TextField logo;
   @FXML
   private TextField tel2;
   @FXML
   private TextField tel1;
   @FXML
   private TextField description;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }   

   @FXML
   private void handlebadword(KeyEvent event) {
   }

   @FXML
   private void annulercentre(ActionEvent event) throws IOException {

         anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/centreview.fxml"));
                           Parent root =load.load();
                           CentreviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage s= new Stage();
                           s=(Stage)((Node)event.getSource()).getScene().getWindow();
                           s.setScene(ss);
                           s.show();
   }

   @FXML
   private void ajoutercentre(ActionEvent event) throws Exception {
        CentreService c = new CentreService();
            if(nom_social.getText().equals(""))
            {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "nom_social cant be null");
                a.show();  
            }
            else if (ville.getText().equals(""))
            {
                       Alert a = new Alert(Alert.AlertType.INFORMATION, "ville cant be null");
                a.show();  
            }
            else if (aderesse.getText().equals(""))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "aderesse cant be null");
                a.show(); 
            }
            else if (logo.getText().equals(""))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "logo cant be null");
                a.show();
            }
              else if (tel2.getText().equals(""))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "tel2 cant be null");
                a.show();
            }
              
                  else if (tel1.getText().equals(""))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "tel1 cant be null");
                a.show();
            }
                  
                         else if (description.getText().equals(""))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "description cant be null");
                a.show();
            }
            else
            {
                  c.ajouterReclamation(new Centre(1,nom_social.getText(), aderesse.getText(), ville.getText(), logo.getText(),tel1.getText(),tel2.getText(),description.getText()));
                Alert a = new Alert(Alert.AlertType.INFORMATION, "centre ajouter avec  success");
                a.show();
                            anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/centreview.fxml"));
                           Parent root =load.load();
                          CentreviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage s= new Stage();
                           s=(Stage)((Node)event.getSource()).getScene().getWindow();
                           s.setScene(ss);
                           s.show();
            }
   }
   
}
