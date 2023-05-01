/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Product;
import edusex.entities.Reservation;
import edusex.services.ServiceProduct;
import edusex.services.ServiceReservation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservationController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private Label nomProduirLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button participerEventButton;
    @FXML
    private TextField idProduct;
    @FXML
    private Label rating;
    @FXML
    private Label qtLab;
    @FXML
    private TextField idid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void participerEvent(MouseEvent event) {
    }
    
        public void setReservation(Reservation e) {
          ServiceReservation sr=new ServiceReservation();
          ServiceProduct sp=new ServiceProduct();
          idProduct.setText(String.valueOf(e.getId()));
        nomProduirLabel.setText(sr.getClientById(e.getIdProduit()).getLibelle());
        //QunatiteLabel.setText(String.valueOf(e.getQteStock()));
        descriptionLabel.setText(e.getDateReservation().toString());
       // prixlabel.setText(String.valueOf(e.getPrixUnite()));
         String path = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"+sr.getClientById(e.getIdProduit()).getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
                rating.setText(e.getEtat());
                qtLab.setText("Quantite : "+String.valueOf(e.getQuantite()));
                idid.setText(String.valueOf(e.getIdProduit()));
   
}

    @FXML
    private void showDetails(ActionEvent event) throws IOException, SQLException {
        ServiceReservation sr = new ServiceReservation();
        Product p = sr.getClientById(Integer.parseInt((String) idid.getText()));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsProduct.fxml"));
        
        
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    
    DetailsProductController controller = loader.getController();
        //System.out.println(p);
        //controller.setDetProduit(p);
        controller.setDetProduit((Product)p);
    
    stage.show();
    }
    
}

