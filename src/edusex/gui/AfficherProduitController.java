/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edusex.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import edusex.entities.Product;
import edusex.services.ServiceProduct;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private GridPane gridProduit;

    
    ServiceProduct ev = new ServiceProduct();
    @FXML
    private Button favBTN;
    @FXML
    private Button trierBtn;
    
    boolean trier=false;
    @FXML
    private Button noTrieBtn;
    @FXML
    private Button mesReservationBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        noTrieBtn.setVisible(false);
        
                try {
            List<Product> evenements = ev.showProduct();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Produit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                ProduitController controller = loader.getController();
                controller.setProduit(evenements.get(i));
                
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
    private void goToFav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherFavorits.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void trieer(ActionEvent event) {
        
            gridProduit.getChildren().clear();
            try {
            List<Product> evenements = ev.showProductTrier();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Produit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                ProduitController controller = loader.getController();
                controller.setProduit(evenements.get(i));
                
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
        trierBtn.setVisible(false);
        noTrieBtn.setVisible(true);
    }
    
    

    @FXML
    private void noTrie(ActionEvent event) {
        gridProduit.getChildren().clear();
            try {
            List<Product> evenements = ev.showProduct();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Produit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                ProduitController controller = loader.getController();
                controller.setProduit(evenements.get(i));
                
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
            trierBtn.setVisible(true);
        noTrieBtn.setVisible(false);
    }

    @FXML
    private void mesReservations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReservation.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    
    
}
