/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Product;
import edusex.services.ServiceProduct;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FXMLController implements Initializable {

    @FXML
    private Button submit;
    @FXML
    private TextField labelTF;
    @FXML
    private TextField descTF;
    @FXML
    private TextField quentTF;
    @FXML
    private TextField imageTF;
    
   Product prod = new Product();
  ServiceProduct ps = new ServiceProduct();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // getEvents();
        // TODO
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
      /*  Product p = new Product();
           
           
               if ((labelTF.getText().length() == 0) || (descTF.getText().length() == 0)|| (imageTF.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
               else {
        p.setLibelle(labelTF.getText());
        p.setDescription(descTF.getText());
         p.setQuantite(Integer.parseInt((String) quentTF.getText()));
         p.setImage(imageTF.getText());
        

               //   addcombox((ActionEvent) CatFid.getItems());
               ps.addProduct(p);
    }
   */ }
    
}
