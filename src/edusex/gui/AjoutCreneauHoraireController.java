/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.CreneauHoraire;
import edusex.entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import edusex.services.ServiceCreneauHoraire;

/**
 * FXML Controller class
 *
 * @author Chebbi_Mariem
 */
public class AjoutCreneauHoraireController implements Initializable {
    
    @FXML
    private ChoiceBox cbHeureDebut, cbHeureFin, cbJour;
    @FXML
    private RadioButton rbEtat;
    @FXML
    private Label msg;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void btnAjouter(ActionEvent event) throws IOException {
        User user = new User(1);
        if (cbJour.getSelectionModel().getSelectedIndex() == -1 || cbHeureDebut.getSelectionModel().getSelectedIndex() == -1 || cbHeureFin.getSelectionModel().getSelectedIndex() == -1) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Vérification");
            a.setContentText("Veuillez remplir les champs de formulaire");
            a.show();
        } else if ((Integer.parseInt(cbHeureDebut.getValue().toString()) >= Integer.parseInt(cbHeureFin.getValue().toString()))) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Vérification");
            a.setContentText("L'heure fin doit être supérieure à l'heure début");
            a.show();
        } else {
            ServiceCreneauHoraire service = new ServiceCreneauHoraire();
            CreneauHoraire crn = service.getCreneauHoraire(cbJour.getValue().toString(), user.getId());
            if (crn.getId() == null) {
                CreneauHoraire cr = new CreneauHoraire(cbJour.getValue().toString(), Integer.parseInt(cbHeureDebut.getValue().toString()), Integer.parseInt(cbHeureFin.getValue().toString()), rbEtat.isSelected(), user);
                service.ajouter(cr);
                this.goToList(event);
            } else {
                CreneauHoraire cr = new CreneauHoraire(crn.getId(), cbJour.getValue().toString(), Integer.parseInt(cbHeureDebut.getValue().toString()), Integer.parseInt(cbHeureFin.getValue().toString()), rbEtat.isSelected(), user);
                service.modifier(cr);
                this.goToList(event);
            }
           
        }

    }

    @FXML
    private void goToList(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("ListCreneauHoraire.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }

    @FXML
    private void goToAjout(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("AjoutCreneauHoraire.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }

    @FXML
    private void goToAjoutRDV(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("AjoutRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    @FXML
    private void goToListRDV(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("ListRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    @FXML
    private void goToHistorique(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("HistoriqueRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }
    
    @FXML
    private void goToStatistique(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i <= 23; i++) {
            this.cbHeureDebut.getItems().addAll(i);
            this.cbHeureFin.getItems().addAll(i);
        }
        this.cbJour.getItems().addAll("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche");

    }

}
