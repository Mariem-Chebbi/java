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
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import edusex.services.ServiceCreneauHoraire;

/**
 * FXML Controller class
 *
 * @author Chebbi_Mariem
 */
public class ListCreneauHoraireController implements Initializable {

    @FXML
    TableView<CreneauHoraire> tv = new TableView<>();
    ObservableList<CreneauHoraire> observableList;
    @FXML
    TableColumn<CreneauHoraire, String> colJour;
    @FXML
    TableColumn<CreneauHoraire, Integer> colHeureDebut;
    @FXML
    TableColumn<CreneauHoraire, Integer> ColHeureFin;
    @FXML
    TableColumn<CreneauHoraire, Boolean> colEtat;
    @FXML
    private AnchorPane root;
    @FXML
    private ChoiceBox cbHeureDebut;

    @FXML
    private ChoiceBox cbHeureFin;

    @FXML
    private RadioButton rbEtat;
    @FXML
    private Label label;
    @FXML
    private Button btnEdit;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfJour;

    /**
     * Initializes the controller class.
     */
    private void remiseAzéro() {
        tfId.clear();
        tfJour.clear();
        rbEtat.setSelected(false);
        cbHeureDebut.setValue(0);
        cbHeureFin.setValue(0);
        label.setText("");
    }

    public void lister() {
        User user = new User(1);
        tv.getItems().clear();
        ServiceCreneauHoraire service = new ServiceCreneauHoraire();
        List<CreneauHoraire> list = service.afficher(user.getId());
        observableList.addAll(list);
        tv.setItems(observableList);
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
    private void btnEditData(ActionEvent event) {

        label.setText("Editer les horaire de " + getSelectedItem().getJour());
        cbHeureDebut.setValue(getSelectedItem().getHeure_debut());
        cbHeureFin.setValue(getSelectedItem().getHeure_fin());
        rbEtat.setSelected(getSelectedItem().isEtat());
        tfId.setText(getSelectedItem().getId().toString());
        tfJour.setText(getSelectedItem().getJour());
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

    @FXML
    private void btnValider(ActionEvent event) {
        if (cbHeureDebut.getSelectionModel().getSelectedIndex() == -1 || cbHeureFin.getSelectionModel().getSelectedIndex() == -1) {
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
            User user = new User(1);
            System.out.println(cbHeureDebut.getValue().toString());
            service.modifier(new CreneauHoraire(Long.parseLong(tfId.getText()), tfJour.getText(), Integer.parseInt(cbHeureDebut.getValue().toString()), Integer.parseInt(cbHeureFin.getValue().toString()), rbEtat.isSelected(), user));
            lister();
            this.remiseAzéro();
        }
    }

    private CreneauHoraire getSelectedItem() {
        return this.tv.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        observableList = FXCollections.observableArrayList();
        colJour.setCellValueFactory(new PropertyValueFactory<>("jour"));
        colHeureDebut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
        ColHeureFin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        lister();
        for (int i = 0; i <= 23; i++) {
            this.cbHeureDebut.getItems().addAll(i);
            this.cbHeureFin.getItems().addAll(i);
        }
        btnEdit.setVisible(true);
        tfId.setVisible(false);
        tfJour.setVisible(false);
    }

}
