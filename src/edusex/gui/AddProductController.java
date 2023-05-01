/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Product;
import edusex.services.ServiceProduct;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField labelTF;
    @FXML
    private TextArea descTF;
    @FXML
    private TextField quentTF;
    @FXML
    private TextField imageTF;
    @FXML
    private Button backBTN;
    @FXML
    private TableView<Product> tab;
    @FXML
    private TableColumn<Product, Integer> idCOL;
    @FXML
    private TableColumn<Product, String> labelCOL;
    @FXML
    private TableColumn<Product, String> descCOL;
    @FXML
    private TableColumn<Product, Integer> quentCOL;
    @FXML
    private TableColumn<Product, String> imageCOL;
    
    Product prod =new Product();
    ServiceProduct ps = new ServiceProduct();
    @FXML
    private Button imageBTN;
    @FXML
    private Button submitBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private Button prodBTN;
    @FXML
    private Button reservationBTN;
    @FXML
    private ImageView imageVIEW;
    @FXML
    private Button suppBtn;
    @FXML
    private Button modifBtn;
    @FXML
    private Button statBtn;
    @FXML
    private Button certificationBTN;
    @FXML
    private Button formationBTN;
    @FXML
    private Button inscriptionBTN;
    @FXML
    private Button blogBTN;
    @FXML
    private Button Categorie;
    @FXML
    private Button UserBTN;
    @FXML
    private Button CentreBtn;
    @FXML
    private Button ServiceBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         
        
        getEvents();
        reset();
        
        
 
    
    
}
    
    
    
    
public void getEvents() {  

    // TODO
    
    List<Product> evenements = ps.showProduct();
    ObservableList<Product> olp = FXCollections.observableArrayList(evenements);
    tab.setItems(olp);
    idCOL.setCellValueFactory(new PropertyValueFactory("id"));
    labelCOL.setCellValueFactory(new PropertyValueFactory("libelle"));
    descCOL.setCellValueFactory(new PropertyValueFactory("description"));
    quentCOL.setCellValueFactory(new PropertyValueFactory("quantite"));
    imageCOL.setCellValueFactory(new PropertyValueFactory("image"));
    
    
    // this.delete();

    }//ge

    @FXML
    private void ajouterProduit(ActionEvent event) throws SQLException {
           Product p = new Product();
           
           
               if ((labelTF.getText().length() == 0) || (descTF.getText().length() == 0)|| (imageTF.getText().length() == 0)|| (quentTF.getText().length() == 0)) {
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
               if (!quentTF.getText().matches("[0-9]+"))
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
        p.setLibelle(labelTF.getText());
        p.setDescription(descTF.getText());
         p.setQuantite(Integer.parseInt((String) quentTF.getText()));
         p.setImage(imageTF.getText());
        

               //   addcombox((ActionEvent) CatFid.getItems());
               ps.addProduct(p);
               getEvents();
               reset();
        }
    }
    @FXML
    private void reset() {
        labelTF.setText("");
        descTF.setText("");
        quentTF.setText("");
        imageTF.setText("");
        imageVIEW.setImage(null);
        submitBTN.setOpacity(1);
        submitBTN.setVisible(true);
                
                modifBtn.setOpacity(0);
                modifBtn.setVisible(false);
                suppBtn.setOpacity(0);
                suppBtn.setVisible(false);
                //Rating ra = null;
                //ra.getRating();

    }
    @FXML
    private void uploadImage(ActionEvent event)throws FileNotFoundException, IOException  {
        
        
        
        
               Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"  + x + ".jpg";
        String Path = x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
           // imageview.setImage(img);
           /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
            imageTF.setText(Path);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

        }
    }

    @FXML
    private void choisirProduit(MouseEvent event) {
        Product e = tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
       // idmodifierField.setText(String.valueOf(e.getId()));
        //idLabel.setText(String.valueOf(e.getId_event()));
        labelTF.setText(String.valueOf(e.getLibelle()));
        descTF.setText(String.valueOf(e.getDescription()));
        quentTF.setText(String.valueOf(e.getQuantite()));
        imageTF.setText((e.getImage()));
        
        String path = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"  + e.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageVIEW.setImage(img);
                
                submitBTN.setOpacity(0);
                submitBTN.setVisible(false);
                
                modifBtn.setOpacity(1);
                modifBtn.setVisible(true);
                suppBtn.setOpacity(1);
                suppBtn.setVisible(true);
                
    }
    
    
    @FXML
    private void modifierProduit(ActionEvent event) throws SQLException {
Product e = tab.getItems().get(tab.getSelectionModel().getSelectedIndex());        
//Product e = new Product();
       // e.setId(Integer.valueOf(idmodifierField.getText()));
       
       
       if ((labelTF.getText().length() == 0) || (descTF.getText().length() == 0)|| (imageTF.getText().length() == 0)|| (quentTF.getText().length() == 0)) {
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
               if (!quentTF.getText().matches("[0-9]+"))
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
       
       
        e.setLibelle(labelTF.getText());
        e.setDescription(descTF.getText());
        e.setImage(imageTF.getText());
        e.setQuantite(Integer.parseInt(quentTF.getText()));
                 
        ps.updateProduct(e);
        getEvents();
        reset();}
      
        
    }

    
    @FXML
    private void supProduit(ActionEvent event) {
        
        
           Product e = tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
      
           ps.removeProduct(e);   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Reservation  supprimer!");
        alert.setContentText("Reservation supprimer avec succee!");
        alert.showAndWait();
        getEvents();
        reset();
        
    }

    @FXML
    private void goToProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addProduct.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void goToReservation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addReservation.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void stat(ActionEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Chart.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("Reservation par Produit");
            primaryStage.setScene(sc);
            Image icon = new Image("/edusexprojectv0/logoEdusex.png");
             primaryStage.getIcons().add(icon);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToCertification(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCertification.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void goToFormation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFormation.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void goToInscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherInscription.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void goToBlog(ActionEvent event) {
    }

    @FXML
    private void goToCategorie(ActionEvent event) {
    }

    @FXML
    private void goToUser(ActionEvent event) {
    }

    @FXML
    private void goToCentre(ActionEvent event) {
    }

    @FXML
    private void goToService(ActionEvent event) {
    }

}
