/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chebbi_Mariem
 */
public class AccueilController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView RDV;
   
    private void goToAjout(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("AjoutCreneauHoraire.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }
    
    private void goToListRDV(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("ListRendezVousPersonnel.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }
    
    private void goToFormation (ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("AfficherFormation.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goToListRDV(MouseEvent event) {
    }

}
