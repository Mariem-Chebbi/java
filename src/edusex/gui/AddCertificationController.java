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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddCertificationController implements Initializable {

    @FXML
    private Button backBTN;
    @FXML
    private TableView<Certification> tab;
    @FXML
    private TableColumn<Certification, Integer> idCOL;
    @FXML
    private Button submitBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private Button imageBTN;
    @FXML
    private TextField imageTF;
    @FXML
    private TableColumn<Certification, String> formationCOL;
    @FXML
    private TableColumn<Certification, String> imageCOL;
    @FXML
   private ChoiceBox<Formation> CertificationDBX;
    Certification prod =new Certification();
    ServiceCertification ps = new ServiceCertification();
    @FXML
    private ImageView img;
    @FXML
    private Button certificationBTN;
    @FXML
    private Button formationBTN;
    @FXML
    private Button inscriptionBTN;
    @FXML
    private Button prodBTN;
    @FXML
    private Button reservationBTN;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getEvents();
        ComboxClient();
    }    

    @FXML
    private void choisirCertification(MouseEvent event) {
         Certification e = (Certification) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
       // idmodifierField.setText(String.valueOf(e.getId()));
        //idLabel.setText(String.valueOf(e.getId_event()));
        //labelTF.setText(String.valueOf(e.getLibelle()));
        //descTF.setText(String.valueOf(e.getDescription()));
        //quentTF.setText(String.valueOf(e.getQuantite()));
        imageTF.setText((e.getImage()));

        String path = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"  +e.getImage();
               File file=new File(path);
              Image imge = new Image(file.toURI().toString());
              img.setImage(imge);
    }

    
    @FXML
    private void ajouterCertification(ActionEvent event) {
           Certification p = new Certification();
           
           
            if ( (imageTF.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
               else {
//        p.setLibelle(labelTF.getText());
//        p.setDescription(descTF.getText());
        //p.setDateInteger.parseInt((String) quentTF.getText()));
         p.setImage(imageTF.getText());
        Formation C = CertificationDBX.getSelectionModel().getSelectedItem();
        p.setFormationId(C.getId());

               //   addcombox((ActionEvent) CatFid.getItems());
               ps.addCertification(p);
               getEvents();
               reset();
        }
    }

    @FXML
    private void reset() {
//         labelTF.setText("");
//        descTF.setText("");
        //quentTF.setText("");
        imageTF.setText("");
    }

    @FXML
    private void supCertification(ActionEvent event) {
         Certification e = (Certification) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
      
        ps.removeCertification(e);   
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        getEvents();
    }

    @FXML
    private void modifierCertification(ActionEvent event) {
         Certification e = (Certification) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());        
//Product e = new Product();
       // e.setId(Integer.valueOf(idmodifierField.getText()));
        
        e.setImage(imageTF.getText());
        //e.setDateFormatiob(Integer.parseInt(dateTF.getText()));
                 
        ps.updateCertification(e);
        getEvents();
    }

    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {
           Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"  + x + ".jpg";
         String Path =  x + ".jpg";
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

    private void getEvents() {
         List<Certification> evenements = ps.showCertification();
    ObservableList<Certification> olp = FXCollections.observableArrayList(evenements);
    tab.setItems(olp);
    idCOL.setCellValueFactory(new PropertyValueFactory("id"));
    
    formationCOL.setCellValueFactory(cellData-> new SimpleStringProperty(ps.getClientById(cellData.getValue().getFormationId()).getLibelle()));
    formationCOL.setCellFactory(column->new TableCell<Certification,String>(){
    @Override
    protected void updateItem(String formationId,boolean empty){
    super.updateItem(formationId, empty);
    if(empty){
    setText("");
    }
    else{
    setText(formationId);}
    }
    
    });
    
    imageCOL.setCellValueFactory(new PropertyValueFactory("image"));
    
    }
    
    private void ComboxClient() {
        ServiceFormation P = new ServiceFormation();
     
      ObservableList <Formation> list = FXCollections.observableArrayList(P.showFormation()) ;
      
      CertificationDBX.setItems(list);
        
    }

    private void addCertification(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AddCertification.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void addFormation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddFormation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void afficherInscription(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherInscription.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
    
    
}
