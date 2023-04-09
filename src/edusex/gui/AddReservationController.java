/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddReservationController implements Initializable {

    @FXML
    private Button BtnBlog;
    @FXML
    private Button Btncategorie;
    @FXML
    private Button backBTN;
    @FXML
    private TableView<?> tab;
    @FXML
    private TableColumn<?, ?> idCOL;
    @FXML
    private TableColumn<?, ?> clientCOL;
    @FXML
    private Button submitBTN;
    @FXML
    private Button cancelBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BtnBlog(ActionEvent event) {
    }

    @FXML
    private void Btncategorie(ActionEvent event) {
    }

    @FXML
    private void choisirProduit(MouseEvent event) {
    }

    @FXML
    private void ajouterReservation(ActionEvent event) {
    }

    @FXML
    private void reset(ActionEvent event) {
    }

    @FXML
    private void supReservation(ActionEvent event) {
    }

    @FXML
    private void modifierReservation(ActionEvent event) {
    }
    
}
