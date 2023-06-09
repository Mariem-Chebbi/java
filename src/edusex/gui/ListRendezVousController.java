/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.RendezVous;
import edusex.entities.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import edusex.services.ServiceRendezVous;
import edusex.utils.SMSSender;
import edusex.utils.WhatsAppSender;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Chebbi_Mariem
 */
public class ListRendezVousController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane root;
    TableView<RendezVous> tv = new TableView<>();
    ObservableList<RendezVous> observableList;
    private TableColumn<RendezVous, Integer> ColId;
    private TableColumn<RendezVous, User> colPsy;

    private TableColumn<RendezVous, LocalDate> colDate;

    private TableColumn<RendezVous, Integer> ColHeure;

    private TextField tfSearch;

    private Pagination pagination;
    @FXML
    private ImageView RDV;

    public void lister() {
        User user = new User(2);
        tv.getItems().clear();
        observableList.clear();
        ServiceRendezVous service = new ServiceRendezVous();
        List<RendezVous> list = service.afficher(user.getId());
        observableList.addAll(list);
        tv.setItems(observableList);
    }

    private void goToList(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("ListCreneauHoraire.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }

    private void goToAjout(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("AjoutCreneauHoraire.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }

    private void goToAjoutRDV(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("AjoutRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    private void goToListRDV(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("ListRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    private void goToHistorique(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("HistoriqueRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    private void goToStatistique(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    private RendezVous getSelectedItem() {
        return this.tv.getSelectionModel().getSelectedItem();
    }

    private void supprimer(ActionEvent event) {
        String msg = "";
        ServiceRendezVous service = new ServiceRendezVous();
        System.out.println(getSelectedItem());
        service.supprimer(getSelectedItem());
        msg = "Bonjour,\n"
                + "Nous sommes désolés de vous informer que nous avons dû annuler notre rendez-vous prévu pour le " + getSelectedItem().getDate_rdv();
        //SMSSender.sendSMS(msg, "20744656");
        //WhatsAppSender.sendWhatsApp("Nous sommes désolés de vous informer que nous avons dû annuler notre rendez-vous prévu pour le " + getSelectedItem().getDate_rdv());
        lister();
    }

    private void recherche_avance(KeyEvent event) {
        FilteredList<RendezVous> filtereddata = new FilteredList<>(observableList, b -> true);
        System.out.println(tfSearch.getText());
        tfSearch.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(rdv -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (String.valueOf(rdv.getId()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(rdv.getPersonnel_id().getPrenom() + " " + rdv.getPersonnel_id().getNom()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(rdv.getHeure()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(rdv.getDate_rdv()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<RendezVous> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tv.comparatorProperty());
        tv.setItems(filtereddata);
    }

    public void triAsc(ActionEvent event) {
        ObservableList<RendezVous> listASC = FXCollections.observableArrayList();
        User user = new User(2);
        //tv.getItems().clear();
        ServiceRendezVous service = new ServiceRendezVous();
        List<RendezVous> list = service.triASC(user.getId());
        listASC.addAll(list);
        tv.setItems(listASC);
    }
    
    public void triDesc(ActionEvent event) {
        ObservableList<RendezVous> listASC = FXCollections.observableArrayList();;
        User user = new User(2);
        //tv.getItems().clear();
        ServiceRendezVous service = new ServiceRendezVous();
        List<RendezVous> list = service.triDESC(user.getId());
        listASC.addAll(list);
        tv.setItems(listASC);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        observableList = FXCollections.observableArrayList();
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPsy.setCellValueFactory(new PropertyValueFactory<>("personnel_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
        ColHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        lister();

        int itemsPerPage = 5;
        pagination.setPageCount((int) Math.ceil((double) observableList.size() / itemsPerPage));
        pagination.setPageFactory(pageIndex -> {
            int fromIndex = pageIndex * itemsPerPage;
            int toIndex = Math.min(fromIndex + itemsPerPage, observableList.size());
            tv.setItems(FXCollections.observableArrayList(observableList.subList(fromIndex, toIndex)));
            return tv;
        });
    }

}
