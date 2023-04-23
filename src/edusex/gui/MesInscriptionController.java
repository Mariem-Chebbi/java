/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    private void handleInscription(MouseEvent event) {
    }
    
}
