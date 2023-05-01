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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DetailsProductController implements Initializable {

    @FXML
    private ImageView imgProd;
    @FXML
    private Label labelProd;
    @FXML
    private Label descProd;
    @FXML
    private Button favorisBtn;
    @FXML
    private Button reserverBtn;
    @FXML
    private TextField idProduct;
    @FXML
    private Button backBtn;
    @FXML
    private DatePicker datePICK;
    @FXML
    private TextField quentTF;
    @FXML
    private Button confirmerReservationBtn;
    @FXML
    private Button favorisBtnRM;
    @FXML
    private Rating rating;
    @FXML
    private Label ratingAVG;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rating.setRating(0);
        
    }    
     public void setDetProduit(Product e) throws SQLException {
        
          idProduct.setText(String.valueOf(e.getId()));
        labelProd.setText(e.getLibelle());
        
        descProd.setText(e.getDescription());
       
         String path = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"+e.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imgProd.setImage(img);
                ServiceProduct sp = new ServiceProduct();
                //System.out.println(sp.isFavorit(e, 10));
                
                rating.setRating(sp.getRatingUserProduct(e, 12));
//                System.out.println(sp.showRating(e).get(1));
                if(sp.showRating(e).size()<1)
                {
                   float moy=0;
                   ratingAVG.setText("Rating : "+moy);
                }
                else
                {
                    float moy=0;
                    for(int i=0;i<sp.showRating(e).size();i++)
                    {
                        moy+=sp.showRating(e).get(i);
                    }
                    moy=moy/sp.showRating(e).size();
                    ratingAVG.setText("Rating : "+moy);
                }
                
                rating.ratingProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue);
                sp.addRating(e, 12, (int) Math.floor((double) newValue));
            }
        
        });
                
                
                
                
                
                
                
                try {
            // TODO
            
            ServiceReservation sr = new ServiceReservation();
            //Product p = sr.getClientById(Integer.parseInt((String) idProduct.getText()));
            if(sp.isFavorit(e, 10))
            {
                favorisBtn.setVisible(false);
                favorisBtnRM.setVisible(true);
            }
            else {
                favorisBtn.setVisible(true);
                favorisBtnRM.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailsProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @FXML
    private void addFavoris(ActionEvent event) {
        ServiceProduct sp = new ServiceProduct();
        ServiceReservation sr = new ServiceReservation();
        Product p = sr.getClientById(Integer.parseInt((String) idProduct.getText()));
        sp.addToFavorite(p, 10);
        favorisBtn.setVisible(false);
        favorisBtnRM.setVisible(true);
        
        String txt;
        txt = "Le produit "+p.getLibelle()+" est ajouter a votre list de favoris";
        
        Notifications no;
              
               Image img = new Image("/edusex/gui/ok.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Produit ajouter !")
                        .text(txt)
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
    }

    @FXML
    private void reserver(ActionEvent event) {
        datePICK.setOpacity(1);
        datePICK.setVisible(true);
        quentTF.setOpacity(1);
        quentTF.setVisible(true);
        confirmerReservationBtn.setOpacity(1);
        confirmerReservationBtn.setVisible(true);
        favorisBtn.setOpacity(0);
        favorisBtn.setVisible(false);
        favorisBtnRM.setOpacity(0);
        favorisBtnRM.setVisible(false);
        reserverBtn.setOpacity(0);
        reserverBtn.setVisible(false);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherProduit.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void confirmerReservation(ActionEvent event) throws IOException {
        Reservation r = new Reservation();
        ServiceReservation sr = new ServiceReservation();
        Product p = new Product();
        ServiceProduct sp = new ServiceProduct();
        LocalDate today = LocalDate.now();
        LocalDate selected = datePICK.getValue();
        
        
        
        
        if ( (quentTF.getText().length() == 0)) 
        {
            Notifications no;
               Image img = new Image("/edusex/gui/error.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Champs vide !")
                        .text("Veiller remplire tout les champs")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
        }
        
        else if (!quentTF.getText().matches("[0-9]+"))
        {
            Notifications no;
               Image img = new Image("/edusex/gui/error.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Quantité invalide !")
                        .text("La quantiter doit etre un entier")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
        }
        
        else {
            
            r.setEtat("en cours");
        r.setIdProduit(Integer.parseInt((String) idProduct.getText()));
        r.setQuantite(Integer.parseInt((String) quentTF.getText()));
        r.setDateReservation(Date.valueOf(datePICK.getValue()));
        p=sr.getClientById(r.getIdProduit());
        

                        

         if (r.getQuantite()>=p.getQuantite())
        {
            Notifications no;
               Image img = new Image("/edusex/gui/error.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Quantité non disponible !")
                        .text("La quantiter demander n'est pas disponible en stock")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();

        }
        
        
        else if(selected.isBefore(today))
        {
            Notifications no;
               Image img = new Image("/edusex/gui/error.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Date invalide !")
                        .text("La date de reservation doit etre supperieur a la date d'aujourdhui")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
        
        }
        else{               
                sr.addReservation(r);
                p.setQuantite(p.getQuantite()-r.getQuantite());
                sp.updateProduct(p);

                String txt;
                txt = "Votre reservation de "+r.getQuantite()+" "+p.getLibelle()+" est enregistrer et en cours de traitement !";
                
                Notifications no;
              
               Image img = new Image("/edusex/gui/ok.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Reservation effectuer avec succee !")
                        .text(txt)
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();


                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherProduit.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();  
            }
            
            
            
            
            
            
        
        
        
        }
    }

    @FXML
    private void removeFavoris(ActionEvent event) {
        ServiceProduct sp = new ServiceProduct();
        ServiceReservation sr = new ServiceReservation();
        Product p = sr.getClientById(Integer.parseInt((String) idProduct.getText()));
        sp.removeFromFavorite(p, 10);
        favorisBtn.setVisible(true);
        favorisBtnRM.setVisible(false);
        
        String txt;
        txt = "Le produit "+p.getLibelle()+" est retirer de votre list de favoris";
        
        Notifications no;
              
               Image img = new Image("/edusex/gui/ok.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Produit retirer !")
                        .text(txt)
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
    }
    
    
    
}
