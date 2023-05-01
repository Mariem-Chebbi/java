/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edusex.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import edusex.entities.Product;
import edusex.entities.Reservation;
import edusex.services.ServiceProduct;
import edusex.services.ServiceReservation;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class ProduitController implements Initializable {

    @FXML
    private Label nomProduirLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button participerEventButton;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField idProduct;
    @FXML
    private Label rating;

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
    
      public void setProduit(Product e) {
          ServiceProduct sp=new ServiceProduct();
          idProduct.setText(String.valueOf(e.getId()));
        nomProduirLabel.setText(e.getLibelle());
        //QunatiteLabel.setText(String.valueOf(e.getQteStock()));
        descriptionLabel.setText(e.getDescription());
       // prixlabel.setText(String.valueOf(e.getPrixUnite()));
         String path = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"+e.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
                if(sp.showRating(e).size()<1)
                {
                   float moy=0;
                   rating.setText("Rating : "+moy);
                }
                else
                {
                    float moy=0;
                    for(int i=0;i<sp.showRating(e).size();i++)
                    {
                        moy+=sp.showRating(e).get(i);
                    }
                    moy=moy/sp.showRating(e).size();
                    rating.setText("Rating : "+moy);
                }

    }

    @FXML
    private void showDetails(ActionEvent event) throws IOException, SQLException {
        ServiceReservation sr = new ServiceReservation();
        Product p = sr.getClientById(Integer.parseInt((String) idProduct.getText()));
        
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
