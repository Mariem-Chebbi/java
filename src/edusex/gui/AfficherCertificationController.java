/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Certification;
import edusex.entities.Formation;
import edusex.services.ServiceCertification;
import edusex.services.ServiceFormation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherCertificationController implements Initializable {

    private GridPane gridProduit;
    
     ServiceCertification ev = new ServiceCertification();
    @FXML
    private Pagination pagination;
    @FXML
    private ImageView img;
        
    private List<Certification> evenements = ev.showCertification();
    @FXML
    private Button certification;
    @FXML
    private ComboBox<String> formation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> choix = Arrays.asList("Formations", "Inscriptions");
        formation.getItems().addAll(choix);
        
        for (int i = 0; i < evenements.size(); i++) {
            //chargement dynamique d'une interface
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("Certification.fxml"));
            //AnchorPane pane = loader.load();
            
            //passage de parametres
            //CertificationController controller = loader.getController();
            //controller.setCertification(evenements.get(i));
            
            pagination.setPageFactory(new Callback<Integer, Node>(){
                @Override
                public Node call(Integer i){
                    return createPage(i);}
            });
            //  controller.setIdevent(evenements.get(i).getId_event());
              
//                gridProduit.add(pane, column, row);
//                column++;
//                if (column > 1) {
//                    column = 0;
//                    row++;
//                }
        }
    }    
    public VBox createPage(int index) {
 
        ImageView imageView = new ImageView();
 
        Certification c = evenements.get(index);
       
            
            
            String path = "D:\\\\EdusexProjectSymfonyFinal\\\\public\\\\images\\\\"+c.getImage();
               File file=new File(path);
              Image image = new Image(file.toURI().toString());
                img.setImage(image);
       
         
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);        
        
        return pageBox;
    }  

    @FXML
    private void afficherCertification(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AfficherCertification.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void afficherFormation(ActionEvent event) throws IOException {
       String selectedPage = formation.getSelectionModel().getSelectedItem();
    if (selectedPage.equals("Formations")) {
       Parent root = FXMLLoader.load(getClass().getResource("AfficherFormation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } else if (selectedPage.equals("Inscriptions")) {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherFormationInscrit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    }
    
    
}
