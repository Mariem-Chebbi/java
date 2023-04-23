/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Formation;
import edusex.services.ServiceFormation;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherFormationInscritController implements Initializable {

    @FXML
    private GridPane gridProduit;
    @FXML
    private AnchorPane navBar;
    @FXML
    private Button certification;
    @FXML
    private ComboBox<String> formation;
    
    ServiceFormation ev = new ServiceFormation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            List<String> choix = Arrays.asList("Formations", "Inscriptions");
            formation.getItems().addAll(choix);        
            try {
            List<Formation> evenements = ev.showFormation();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Formation.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                FormationController controller = loader.getController();
                controller.setFormation(evenements.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                
              gridProduit.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void AfficherCertification(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AfficherCertification.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void afficherFormation(ActionEvent event) throws IOException {
        String selectedPage = (String) formation.getValue();
    if (selectedPage.equals("Formations")) {
       Parent root = FXMLLoader.load(getClass().getResource("AfficherFormation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } else if (selectedPage.equals("Inscriptions")) {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherFormationInscrit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    }
    
}
