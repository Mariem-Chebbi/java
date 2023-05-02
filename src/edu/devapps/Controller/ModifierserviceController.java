/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.Controller;

import edu.devapps.entity.Centre;
import edu.devapps.entity.Service;
import edu.devapps.services.CentreService;
import edu.devapps.services.ServiceService;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ala20
 */
public class ModifierserviceController implements Initializable {

   @FXML
   private AnchorPane anchorme;
   @FXML
   private TextField libelle;
   @FXML
   private TextField description;
   @FXML
   private TextField icone;
   Service thisservice;
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
   private void modifierservice(ActionEvent event) throws IOException {
      ServiceService s = new ServiceService();
               
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
            else
            { 
                 
                      s.modifierservice(new Service(thisservice.getId(),libelle.getText(), description.getText(), icone.getText()));
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "votre service est modifier ");
                        a.show();
        
                          anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/serviceview.fxml"));
                           Parent root =load.load();
                           serviceviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
    
            }
   
   }
   
   
    public void  getinfo (Service camp)
    {
        thisservice=camp;
  
        libelle.setText(camp.getLibelle());
        
      
        description.setText(camp.getDescription());
        icone.setText(camp.getIcone());
      
      
        
    }
   
}
