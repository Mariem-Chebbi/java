/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.Controller;

import edu.devapps.entity.Centre;
import edu.devapps.entity.Reclamation;
import edu.devapps.entity.Reponse_rec;
import edu.devapps.entity.Service;
import edu.devapps.services.CentreService;
import edu.devapps.services.ReponseRecService;
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
public class ModifiercentreController implements Initializable {

   @FXML
   private AnchorPane anchorme;
   @FXML
   private TextField nom_social;
   @FXML
   private TextField aderesse;
   @FXML
   private TextField ville;
   @FXML
   private TextField description;
   Centre thisservice;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
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
   private void modifiercentre(ActionEvent event) throws IOException {
  
       CentreService s = new CentreService();
               
                       if(nom_social.getText().equals(""))
            {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "nom_social cant be null");
                a.show();  
            }
            
           
            else if (description.getText().equals(""))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "description cant be null");
                a.show();
            }
            else
            { 
                 
                      s.modifiercentre(new Centre(thisservice.getId(),nom_social.getText(), aderesse.getText(), ville.getText(),thisservice.getLogo(),thisservice.getTel1(),thisservice.getTel2(),description.getText()));
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "votre reponse est modifier ");
                        a.show();
        
                          anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/centreview.fxml"));
                           Parent root =load.load();
                           CentreviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
    
            }
   }
   
   
   
     public void  getinfo (Centre camp)
    {
        thisservice=camp;
  
        nom_social.setText(camp.getNom_social());
        
      
        aderesse.setText(camp.getAderesse());
        ville.setText(camp.getVille());
        description.setText(""+camp.getDescription());
      
      
        
    }
}
