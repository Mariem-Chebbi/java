/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Formation;
import edusex.services.ServiceFormation;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddFormationController implements Initializable {

    @FXML
    private TextField labelTF;
    @FXML
    private TextArea descTF;
    @FXML
    private Button imageBTN;
    @FXML
    private TextField imageTF;
    @FXML
    private Button submitBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private Button backBTN;
    @FXML
    private TableView<Formation> tab;
    @FXML
    private TableColumn<Formation, Integer> idCOL;
    @FXML
    private TableColumn<Formation, String> labelCOL;
    @FXML
    private TableColumn<Formation, String> descCOL;
   
    @FXML
    private TableColumn<Formation, String> imageCOL;
    @FXML
    private DatePicker datef;

    Formation prod =new Formation();
    ServiceFormation ps = new ServiceFormation();
    @FXML
    private TableColumn<Formation, Date> dateCol;
    @FXML
    private ImageView img;
    @FXML
    private Button btnExcel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     getEvents();
     //submitBTN.setVisible(true);
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
        String DBPath = "D:\\\\EdusexProjectSymfonyFinal\\\\public\\\\images\\\\"  + x + ".jpg";
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

    @FXML
    private void ajouterFormation(ActionEvent event) {
         Formation p = new Formation();
           
           
               if ((labelTF.getText().length() == 0) || (descTF.getText().length() == 0)|| (imageTF.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
               else {
        p.setLibelle(labelTF.getText());
        p.setDescription(descTF.getText());
        p.setDateFormatiob(Date.valueOf(datef.getValue()));
         p.setImage(imageTF.getText());
        

               //   addcombox((ActionEvent) CatFid.getItems());
               ps.addFormation(p);
               getEvents();
               reset();
        }
    }

        @FXML
    private void reset() {
        labelTF.setText("");
        descTF.setText("");
        //quentTF.setText("");
        imageTF.setText("");
       
    }

    @FXML
    private void choisirFormation(MouseEvent event) {
        Formation e = (Formation) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
       // idmodifierField.setText(String.valueOf(e.getId()));
        //idLabel.setText(String.valueOf(e.getId_event()));
        labelTF.setText(String.valueOf(e.getLibelle()));
        descTF.setText(String.valueOf(e.getDescription()));
        //quentTF.setText(String.valueOf(e.getQuantite()));
        imageTF.setText((e.getImage()));
        
        String path = "D:\\\\EdusexProjectSymfonyFinal\\\\public\\\\images\\\\"  +e.getImage();
               File file=new File(path);
              Image imge = new Image(file.toURI().toString());
            img.setImage(imge);
                          
    }

    @FXML
    private void supFormation(ActionEvent event) {
        Formation e = (Formation) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
      
        ps.removeFormation(e);   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        getEvents();
    }

    @FXML
    private void modifierFormation(ActionEvent event) {
        Formation e = (Formation) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());        
        //Product e = new Product();
       // e.setId(Integer.valueOf(idmodifierField.getText()));
        e.setLibelle(labelTF.getText());
        e.setDescription(descTF.getText());
        e.setImage(imageTF.getText());
        //e.setDateFormatiob(Integer.parseInt(dateTF.getText()));
                 
        ps.updateFormation(e);
        getEvents();
      
    }

    private void getEvents() {
           List<Formation> evenements = ps.showFormation();
    ObservableList<Formation> olp = FXCollections.observableArrayList(evenements);
    tab.setItems(olp);
    idCOL.setCellValueFactory(new PropertyValueFactory("id"));
    labelCOL.setCellValueFactory(new PropertyValueFactory("libelle"));
    descCOL.setCellValueFactory(new PropertyValueFactory("description"));
    //dateCol.setCellValueFactory(new PropertyValueFactory("date_formation));
    
    dateCol.setCellValueFactory(new PropertyValueFactory<>("dateFormatiob"));
    dateCol.setCellFactory(column->{
        return new TableCell<Formation,Date>(){
                @Override
    protected void updateItem(Date item,boolean empty){
    super.updateItem(item, empty);
    if(empty){
    setText("");
    }
    else{
    setText(new SimpleDateFormat("dd/MM/yyyy").format(item));
    }
        }
                };
    });

    imageCOL.setCellValueFactory(new PropertyValueFactory("image"));

    // this.delete();
    }

    @FXML
    private void addCertifications(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddCertification.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addFormation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddFormation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void afficherInscription(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherInscription.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnExcel(ActionEvent event) {
        
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Enregistrer le fichier Excel");
    fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers Excel", "*.xlsx"));
    String fileName = fileChooser.showSaveDialog(null).getPath();
    try {
      // exporter les données vers le fichier Excel
      List<Formation> formations = ps.showFormation();
      ps.exportToExcel(formations, fileName);
      // afficher une confirmation
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Export réussi");
      alert.setHeaderText(null);
      alert.setContentText("Le fichier Excel a été enregistré avec succès !");
      alert.showAndWait();
    } catch (IOException e) {
      // afficher une erreur si l'exportation a échoué
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Erreur d'export");
      alert.setHeaderText(null);
      alert.setContentText("Une erreur est survenue lors de l'exportation : " + e);
    }}


    
    
}
