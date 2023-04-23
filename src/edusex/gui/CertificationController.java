/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Certification;
import edusex.entities.Formation;
import edusex.services.ServiceFormation;
import edusex.services.ServiceInscription;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CertificationController implements Initializable {

    @FXML
    private Label nomFormationLabel;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setCertification(Certification c) {
        
       ServiceFormation serviceFormation = new ServiceFormation();
       
        List<Formation>  formations = serviceFormation.showFormation();
         for (int i = 0; i < formations.size(); i++) {
             if (formations.get(i).getId() == c.getFormationId()) {
             nomFormationLabel.setText(formations.get(i).getLibelle());    
                } 
            }
     
         String path = "D:\\\\EdusexProjectSymfonyFinal\\\\public\\\\images\\\\"+c.getImage();
               File file=new File(path);
              Image image = new Image(file.toURI().toString());
                img.setImage(image);
    }
    
}
