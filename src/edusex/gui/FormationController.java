/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edusex.gui;

import edusex.entities.Certification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import edusex.entities.Formation;
import edusex.entities.Inscription;
import edusex.services.ServiceCertification;
import edusex.services.ServiceFormation;
import edusex.services.ServiceInscription;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class FormationController implements Initializable {

    @FXML
    private Label nomProduirLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button participerEventButton;
    @FXML
    private ImageView imageview;

    private boolean isAlreadyInscribed = false;
    @FXML
    private TextField idUser;
    @FXML
    private TextField idFormation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    
      public void setFormation(Formation e) throws SQLException {
        
        nomProduirLabel.setText(e.getLibelle());
        //QunatiteLabel.setText(String.valueOf(e.getQteStock()));
        descriptionLabel.setText(e.getDescription());
        
        idFormation.setText(String.valueOf(e.getId())); 
       // prixlabel.setText(String.valueOf(e.getPrixUnite()));
         String path = "D:\\\\EdusexProjectSymfonyFinal\\\\public\\\\images\\\\"+e.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
               // System.out.println(e.getId());
                ServiceCertification sc = new ServiceCertification();
         // System.out.println(sc.showCertification(2, e.getId()));
    }

    
      
    @FXML
    private void handleInscription(MouseEvent event) {
         ServiceInscription serviceInscription = new ServiceInscription();
         
              
//    Inscription inscriptionfind = serviceInscription.showInscription().stream()
//                .filter(i -> i.getIdPersonnel() == 2 && i.getIdFormation() == Integer.parseInt(idFormation.getText()))
//                .findFirst()
//                .orElse(null);  
//     if (inscriptionfind != null) {
//     participerEventButton.setText("Annuler inscription"); 
//     }else {participerEventButton.setText("S'inscrire");};
//        
    if (!isAlreadyInscribed) {
        Inscription inscription = new Inscription();

        inscription.setIdPersonnel(2);
        inscription.setIdFormation(Integer.parseInt(idFormation.getText()));
        inscription.setPresent("Absent");    
        
        
        serviceInscription.addInscription(inscription);

        participerEventButton.setText("Annuler inscription");        
        isAlreadyInscribed = true;
    } else {
       Inscription inscriptionToRemove = serviceInscription.showInscription().stream()
                .filter(i -> i.getIdPersonnel() == 2 && i.getIdFormation() == Integer.parseInt(idFormation.getText()))
                .findFirst()
                .orElse(null);
        if (inscriptionToRemove != null) {
                        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information ");
                alert.setHeaderText("Event inscrit");
                alert.setContentText("Voulez vous supprimer votre inscription dans "+inscriptionToRemove.getIdFormation());
                alert.showAndWait();
                
            serviceInscription.removeInscription(inscriptionToRemove);
            
            participerEventButton.setText("S'inscrire");
            isAlreadyInscribed = false;
        }
    }
    }
    
}
