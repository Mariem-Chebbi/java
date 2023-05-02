/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.Controller;

import edu.devapps.entity.Centre;
import edu.devapps.entity.Reclamation;
import edu.devapps.entity.Reponse_rec;
import edu.devapps.entity.Service;
import edu.devapps.services.CentreService;
import edu.devapps.services.ReclamationService;
import edu.devapps.services.ServiceService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yassine.MyListener;

/**
 * FXML Controller class
 *
 * @author ala20
 */
public class CentreviewController implements Initializable {

   @FXML
   private TextField rechercher;
   @FXML
   private VBox chosenFruitCard;
   @FXML
   private Label nom_social;
   @FXML
   private Label aderesse;
   @FXML
   private ImageView fruitImg;
   @FXML
   private Label ville;
   @FXML
   private Label description;
   @FXML
   private Label tel1;
   @FXML
   private HBox hboxcamping;
   @FXML
   private ScrollPane scroll;
   @FXML
   private GridPane grid;
   @FXML
   private AnchorPane anchorforedit;

   /**
    * Initializes the controller class.
    */
   
       
           Centre currentcentre;
     private Parent fxml;
      private List<Centre> centres = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    
    
    
    
    
     private List<Centre> getsearchData(String rechercher) throws SQLException {
      
            List<Centre> centres = new ArrayList<>();
        CentreService s = new CentreService();
        Centre rec1;

        for (int i = 0; i < s.rechercher(rechercher).size(); i++) {
            Centre get = s.rechercher(rechercher).get(i);
            
            
            rec1 = new Centre();
            rec1.setId(get.getId());
            rec1.setNom_social(get.getNom_social());
            rec1.setAderesse(get.getAderesse());
            rec1.setVille(get.getVille());
            rec1.setLogo(get.getLogo());
            rec1.setTel1(get.getTel1());
            rec1.setTel2(get.getTel2());
        
             rec1.setDescription(get.getDescription());
            
         
            centres.add(rec1);
        }
    

      
        return centres;
    }
    
    private List<Centre> getData() throws SQLException {
      
            List<Centre> centres = new ArrayList<>();
        CentreService s = new CentreService();
        Centre rec1;

        for (int i = 0; i < s.afficherCentre().size(); i++) {
            Centre get = s.afficherCentre().get(i);
            
            
            rec1 = new Centre();
            rec1.setId(get.getId());
            rec1.setNom_social(get.getNom_social());
            rec1.setAderesse(get.getAderesse());
            rec1.setVille(get.getVille());
            rec1.setLogo(get.getLogo());
            rec1.setTel1(get.getTel1());
            rec1.setTel2(get.getTel2());
                     rec1.setDescription(get.getDescription());

           
            
         
            centres.add(rec1);
        }
    

      
        return centres;
    }

    private void setChosenCamping(Centre rec) {
        currentcentre=rec;
        nom_social.setText(rec.getNom_social());
        aderesse.setText(""+ rec.getAderesse());
       ville.setText(""+rec.getVille());
       description.setText(rec.getDescription());
       tel1.setText(""+rec.getTel1());
      
        chosenFruitCard.setStyle("-fx-background-color: #FAEBD7;\n" +
                "    -fx-background-radius: 30;");
    }
    
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
         hboxcamping.setVisible(false);
        anchorforedit.setVisible(false);
        try {
            // TODO
            afficher();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }   
   
   
     public void afficher() throws SQLException
    {
               centres.addAll(getData());
        if (centres.size() > 0) {
            setChosenCamping(centres.get(0));
            myListener = new MyListener() {
           

            

                @Override
                public void onClickListener(Centre centre) {
                      setChosenCamping(centre);
                }

        

               @Override
               public void onClickListener(Reclamation Reclamation) {
               }

               @Override
               public void onClickListener(Reponse_rec Reponse_rec) {
               }

               @Override
               public void onClickListener(Service Service) {
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < centres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/edu/devapps/Interface/onecentreview.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OnecentreviewController OnecentreviewController = fxmlLoader.getController();
                OnecentreviewController.setData(centres.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   @FXML
   private void search(KeyEvent event) throws SQLException {
      
         centres.clear();
         grid.getChildren().clear();
             centres.addAll(getsearchData(rechercher.getText()));
        if (centres.size() > 0) {
            setChosenCamping(centres.get(0));
            myListener = new MyListener() {
           

            

                @Override
                public void onClickListener(Centre centre) {
                      setChosenCamping(centre);
                }

        

               @Override
               public void onClickListener(Reclamation Reclamation) {
               }

               @Override
               public void onClickListener(Reponse_rec Reponse_rec) {
               }

               @Override
               public void onClickListener(Service Service) {
               }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < centres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/edu/devapps/Interface/onecentreview.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OnecentreviewController OnecentreviewController = fxmlLoader.getController();
                OnecentreviewController.setData(centres.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

   @FXML
   private void ajoutercentre(ActionEvent event) throws IOException {
  
     anchorforedit.setVisible(true);
           
                fxml = FXMLLoader.load(getClass().getResource("/edu/devapps/Interface/ajoutercentre.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/ajoutercentre.fxml"));
                           Parent root =load.load();
                           AjoutercentreController c2=  load.getController();
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true);   
   }

   @FXML
   private void modifiercentre(ActionEvent event) throws IOException {
         anchorforedit.setVisible(true);
           
                            fxml = FXMLLoader.load(getClass().getResource("/edu/devapps/Interface/modifiercentre.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/modifiercentre.fxml"));
                           Parent root =load.load();
                           ModifiercentreController c2=  load.getController();
                           c2.getinfo(currentcentre);
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true);   
   }

   @FXML
   private void supprimercentre(ActionEvent event) throws IOException {
        CentreService s = new CentreService();
        
        Centre r = new Centre();
        r.setId(currentcentre.getId());
        s.supprimercentre(r);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "your centre has been deleted");
                a.show();
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/centreview.fxml"));
                           Parent root =load.load();
                           CentreviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
   }


   @FXML
   private void gototransport(MouseEvent event) throws IOException {
    FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/serviceview.fxml"));
                           Parent root =load.load();
                           serviceviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
   
   }
   
}
