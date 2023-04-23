/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Certification;
import edusex.entities.Inscription;
import edusex.services.ServiceFormation;
import edusex.services.ServiceInscription;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherInscriptionController implements Initializable {

    @FXML
    private TableView<Inscription> tab;
    @FXML
    private TableColumn<Inscription, Integer> idCOL;
    @FXML
    private TableColumn<Inscription, String> personnelCOL;
    @FXML
    private TableColumn<Inscription, String> formationCOL;
    @FXML
    private TableColumn<Inscription, String> presentCOL;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    ServiceInscription iv = new ServiceInscription();
    @FXML
    private Button certification;
    @FXML
    private Button formation;
    @FXML
    private Button inscription;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getEvents();
    }    

    @FXML
    private void modifierPresence(MouseEvent event) {
          Inscription e = (Inscription) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());        

        if (e.getPresent()=="Present"){e.setPresent("Absent");}else{e.setPresent("Present");};
                
        iv.updateInscription(e);
        getEvents();
    }
    
    private void getEvents() {
    List<Inscription> evenements = iv.showInscription();
    ObservableList<Inscription> olp = FXCollections.observableArrayList(evenements);
    tab.setItems(olp);
    idCOL.setCellValueFactory(new PropertyValueFactory("id"));
    
    personnelCOL.setCellValueFactory(cellData-> new SimpleStringProperty(iv.getUserById(cellData.getValue().getIdPersonnel())));
    personnelCOL.setCellFactory(column->new TableCell<Inscription,String>(){
    @Override
    protected void updateItem(String PersonnelId,boolean empty){
    super.updateItem(PersonnelId, empty);
    if(empty){
    setText("");
    }
    else{
    setText(PersonnelId);}
    }
    
    });
      
    
    formationCOL.setCellValueFactory(cellData-> new SimpleStringProperty(iv.getFormationById(cellData.getValue().getIdFormation()).getLibelle()));
    
    presentCOL.setCellValueFactory(new PropertyValueFactory("Present"));
   
    
    }
 
    @FXML
    private void addFormation(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AddFormation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void afficherInscription(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherInscription.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addCertification(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AddCertification.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
