/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import java.net.URL;
import edusex.entities.categorie;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import edusex.services.categorie_service;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddCategorieController implements Initializable {

    @FXML
    private Button BtnBlog;
    @FXML
    private Button Btncategorie;
    @FXML
    private Button backBTN;
    @FXML
    private TableView<categorie> tab;
    @FXML
    private TableColumn<categorie, Integer> idCOL;
    @FXML
    private Button submitBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private TableColumn<categorie, String> libelleCOL;
    @FXML
    private TextField libelle;
categorie_service ps = new categorie_service();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            getEvents();
        } catch (SQLException ex) {
            Logger.getLogger(AddCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  
    
    public void getEvents() throws SQLException {  

    // TODO
    try {
    List<categorie> categories = ps.getAll();
            ObservableList<categorie> olp = FXCollections.observableArrayList(categories);
            tab.setItems(olp);
            idCOL.setCellValueFactory(new PropertyValueFactory("id"));
            libelleCOL.setCellValueFactory(new PropertyValueFactory("libelle"));
            
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    
    // this.delete();

    }

    @FXML
    private void BtnBlog(ActionEvent event) {
    }

    @FXML
    private void Btncategorie(ActionEvent event) {
    }

    @FXML
    private void choisirProduit(MouseEvent event) {
        categorie e = tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
       // idmodifierField.setText(String.valueOf(e.getId()));
        //idLabel.setText(String.valueOf(e.getId_event()));
       libelle.setText(String.valueOf(e.getLibelle()));
        
    }


    @FXML
    private void reset() {
        libelle.setText("");
    }

    @FXML
    private void ajouterCategorie(ActionEvent event)throws SQLException {
    
categorie p = new categorie();
           
           
            if (libelle.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
               else {
        p.setLibelle(libelle.getText());
        
        
        

               //   addcombox((ActionEvent) CatFid.getItems());
               ps.ajoutercategorie(p);
               getEvents();
               reset();
        }
    }
    @FXML
    private void supCategorie(ActionEvent event) throws SQLException {
          categorie e = tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
      
           ps.suprimecategorie(e);   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Categorie delete");
        alert.setContentText("Categorie deleted successfully!");
        alert.showAndWait();
        getEvents();
        reset();
        
    }

    @FXML
    private void modifierCategorie(ActionEvent event) throws SQLException {
        
        categorie e = tab.getItems().get(tab.getSelectionModel().getSelectedIndex());        
//Product e = new Product();
       // e.setId(Integer.valueOf(idmodifierField.getText()));
        e.setLibelle(libelle.getText());
        
                 
        ps.updatecategorie(e);
        getEvents();
        reset();
    }
    @FXML
    private void goToBlog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addBlog.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void goToCategorie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addCategorie.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
