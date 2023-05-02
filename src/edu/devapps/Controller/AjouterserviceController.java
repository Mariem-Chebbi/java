/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.Controller;

import edu.devapps.entity.Reclamation;
import edu.devapps.entity.Service;
import edu.devapps.services.Mail;
import edu.devapps.services.ReclamationService;
import edu.devapps.services.ServiceService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ala20
 */
public class AjouterserviceController implements Initializable {

   @FXML
   private AnchorPane anchorme;
   @FXML
   private TextField libelle;
   @FXML
   private TextField description;
   @FXML
   private TextField icone;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }   

   @FXML
   private void annulerservice(ActionEvent event) throws IOException {
         anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/serviceview.fxml"));
                           Parent root =load.load();
                           serviceviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage s= new Stage();
                           s=(Stage)((Node)event.getSource()).getScene().getWindow();
                           s.setScene(ss);
                           s.show();
   }

   @FXML
   private void ajouterservice(ActionEvent event) throws Exception {
       ServiceService c = new ServiceService();

            
            if(libelle.getText().equals(""))
            {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "libelle cant be null");
                a.show();  
            }
            else if (description.getText().equals(""))
            {
                       Alert a = new Alert(Alert.AlertType.INFORMATION, "description cant be null");
                a.show();  
            }
            else if (icone.getText().equals(""))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "icone cant be null");
                a.show(); 
            }
           
            else
            {
                
            
            
            c.ajouterservice(new Service(1,libelle.getText(), description.getText(), icone.getText()));
             
                Alert a = new Alert(Alert.AlertType.INFORMATION, "service ajouter avec  success");
                a.show();
                            anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/serviceview.fxml"));
                           Parent root =load.load();
                          serviceviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage s= new Stage();
                           s=(Stage)((Node)event.getSource()).getScene().getWindow();
                           s.setScene(ss);
                           s.show();
    }
   }
   
}
