/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CreneauHoraire;
import entities.RendezVous;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceCreneauHoraire;
import services.ServiceRendezVous;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Chebbi_Mariem
 */
public class AjoutRendezVousController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane root;
    @FXML
    private DatePicker dpDate;
    @FXML
    private ChoiceBox cbHeure;
    @FXML
    private Button btnValider;
    @FXML
    private ChoiceBox cbPsy;

    @FXML
    public void getCrByDayName(ActionEvent event) {
        this.cbHeure.getItems().clear();
        User user = new User(1L);
        LocalDate date = dpDate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("fr"));
        String nom = date.format(formatter);
        //System.out.println(nom);
        ServiceCreneauHoraire servCr = new ServiceCreneauHoraire();
        CreneauHoraire cr = servCr.getCreneauHoraire(nom, user.getId());
        System.out.println(cr);
        if (cr.getId() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Oops!");
            a.setContentText("Le psychologue n'est pas disponible pour cette date. \n veuillez choisir une autre date");
            a.show();
        } else {
            for (int i = cr.getHeure_debut(); i <= cr.getHeure_fin(); i++) {
                this.cbHeure.getItems().addAll(i);
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

    private List<User> getPsys() {
        ServiceUser service = new ServiceUser();
        return service.getPersonnels();
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        if (cbPsy.getSelectionModel().getSelectedIndex() == -1 || cbHeure.getSelectionModel().getSelectedIndex() == -1 || dpDate.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Vérification");
            a.setContentText("Veuillez remplir les champs de formulaire");
            a.show();
        } else {
            User user = new User(2L);
            ServiceRendezVous service = new ServiceRendezVous();
            for (User u : this.getPsys()) {
                if ((u.getPrenom() + " " + u.getNom()).equals(cbPsy.getValue())) {
                    service.ajouter(new RendezVous(dpDate.getValue(), Integer.parseInt(cbHeure.getValue().toString()), u, user));
                }
            }
            this.goToListRDV(event);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (User u : this.getPsys()) {
            cbPsy.getItems().add(u.getPrenom() + " " + u.getNom());
        }
        
        dpDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                }
            }
        });

    }

}
