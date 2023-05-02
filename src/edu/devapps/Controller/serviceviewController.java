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
import edu.devapps.services.ReponseRecService;
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
public class serviceviewController implements Initializable {

   @FXML
   private VBox chosenFruitCard;
   @FXML
   private Label libelle;
   @FXML
   private ImageView fruitImg;
   @FXML
   private Label description;
   @FXML
   private Label icone;
   @FXML
   private HBox hboxcamping;
   @FXML
   private ScrollPane scroll;
   @FXML
   private GridPane grid;
   @FXML
   private AnchorPane anchorforedit;
   Service currentservice;
     private Parent fxml;
      private List<Service> services = new ArrayList<>();
    private Image image;
        private MyListener myListener;
   @FXML
   private TextField rechercher;

   /**
    * Initializes the controller class.
    */
    
    
       private List<Service> getsearchData(String rechercher) throws SQLException {
      
            List<Service> services = new ArrayList<>();
          ServiceService s = new ServiceService();
        Service rec1;

        for (int i = 0; i < s.rechercher(rechercher).size(); i++) {
            Service get = s.rechercher(rechercher).get(i);
            
            
            rec1 = new Service();
            rec1.setId(get.getId());
            rec1.setLibelle(get.getLibelle());
            rec1.setDescription(get.getDescription());
            rec1.setIcone(get.getIcone());
           
        
           
            
         
            services.add(rec1);
        }
    

      
        return services;
    }
    
    private List<Service> getData() throws SQLException {
      
            List<Service> services = new ArrayList<>();
        ServiceService s = new ServiceService();
        Service rec1;

        for (int i = 0; i < s.afficherservice().size(); i++) {
            Service get = s.afficherservice().get(i);
            
            
          rec1 = new Service();
            rec1.setId(get.getId());
            rec1.setLibelle(get.getLibelle());
            rec1.setDescription(get.getDescription());
            rec1.setIcone(get.getIcone());
        
           
            
         
            services.add(rec1);
        }
    

      
        return services;
    }

    private void setChosenCamping(Service rec) {
        currentservice=rec;
        libelle.setText(rec.getLibelle());
        description.setText(""+ rec.getDescription());
       icone.setText(""+rec.getIcone());
    
      
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
               services.addAll(getData());
        if (services.size() > 0) {
            setChosenCamping(services.get(0));
            myListener = new MyListener() {
           

            

                @Override
                public void onClickListener(Reclamation Reclamation) {
                }

                @Override
                public void onClickListener(Reponse_rec Reponse_rec) {
                
                }

               @Override
               public void onClickListener(Centre centre) {

               }

               @Override
               public void onClickListener(Service Service) {
                                        setChosenCamping(Service);
               }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < services.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/edu/devapps/Interface/oneserviceview.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OneserviceviewController OneserviceviewController = fxmlLoader.getController();
                OneserviceviewController.setData(services.get(i),myListener);

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

    private void ajouterreclamation(ActionEvent event) throws IOException {
            anchorforedit.setVisible(true);
           
                fxml = FXMLLoader.load(getClass().getResource("/edu/devapps/Interface/ajouterreclamation.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/ajouterreclamation.fxml"));
                           Parent root =load.load();
                           AjouterreclamationController c2=  load.getController();
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true);   
    }

   
   

   @FXML
   private void modifierservice(ActionEvent event) throws IOException {
  
         anchorforedit.setVisible(true);
           
                            fxml = FXMLLoader.load(getClass().getResource("/edu/devapps/Interface/modifierservice.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/modifierservice.fxml"));
                           Parent root =load.load();
                           ModifierserviceController c2=  load.getController();
                           c2.getinfo(currentservice);
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true);   
   }

   @FXML
   private void supprimerservice(ActionEvent event) throws IOException {
 
    ServiceService s = new ServiceService();
        
        Service r = new Service();
        r.setId(currentservice.getId());
        s.supprimerservice(r);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "your service has been deleted");
                a.show();
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/serviceview.fxml"));
                           Parent root =load.load();
                           serviceviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
   }

   @FXML
   private void ajouterservice(ActionEvent event) throws IOException {
        anchorforedit.setVisible(true);
           
                fxml = FXMLLoader.load(getClass().getResource("/edu/devapps/Interface/ajouterservice.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/ajouterservice.fxml"));
                           Parent root =load.load();
                           AjouterserviceController c2=  load.getController();
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true);   
   }

   @FXML
   private void gototransport(MouseEvent event) throws IOException {
   
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
   private void rechercher(KeyEvent event) throws SQLException {
      services.clear();
      grid.getChildren().clear();
      
             services.addAll(getsearchData(rechercher.getText()));
        if (services.size() > 0) {
            setChosenCamping(services.get(0));
            myListener = new MyListener() {
           

            

                @Override
                public void onClickListener(Reclamation Reclamation) {
                }

                @Override
                public void onClickListener(Reponse_rec Reponse_rec) {
                
                }

               @Override
               public void onClickListener(Centre centre) {

               }

               @Override
               public void onClickListener(Service Service) {
                                        setChosenCamping(Service);
               }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < services.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/edu/devapps/Interface/oneserviceview.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OneserviceviewController OneserviceviewController = fxmlLoader.getController();
                OneserviceviewController.setData(services.get(i),myListener);

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
   
}
