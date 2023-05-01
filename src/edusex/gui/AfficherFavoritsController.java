/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Product;
import edusex.services.ServiceProduct;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherFavoritsController implements Initializable {

    @FXML
    private GridPane gridProduit;

    ServiceProduct ev = new ServiceProduct();
    @FXML
    private Button backBtn;
    private Button trierBtn;
    private Button noTrieBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     try {
            List<Product> evenements = ev.showFavorit(10);
            System.out.println(evenements);
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
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherProduit.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    private void trieer(ActionEvent event) {
        gridProduit.getChildren().clear();
            try {
            List<Product> evenements = ev.showFavoritTrier(10);
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

    private void noTrie(ActionEvent event) {
        gridProduit.getChildren().clear();
            try {
            List<Product> evenements = ev.showFavorit(10);
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
    
}
