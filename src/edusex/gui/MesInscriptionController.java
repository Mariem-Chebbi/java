/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Formation;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MesInscriptionController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private Label nomProduirLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button participerEventButton;
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
      public void setFormation(Formation e) {
        
        nomProduirLabel.setText(e.getLibelle());
        //QunatiteLabel.setText(String.valueOf(e.getQteStock()));
        descriptionLabel.setText(e.getDescription());
        
        idFormation.setText(String.valueOf(e.getId())); 
       // prixlabel.setText(String.valueOf(e.getPrixUnite()));
         String path = "D:\\\\EdusexProjectSymfonyFinal\\\\public\\\\images\\\\"+e.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
       

    }
    @FXML
    private void handleInscription(MouseEvent event) {
    }
    
}
