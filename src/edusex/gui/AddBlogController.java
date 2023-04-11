/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.blog;
import edusex.entities.categorie;
import edusex.services.blog_Service;
import edusex.services.categorie_service;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddBlogController implements Initializable {

    @FXML
    private Button BtnBlog;
    @FXML
    private Button Btncategorie;
    @FXML
    private TextField libelleTF;
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
    private TableView<blog> tab;
    @FXML
    private TableColumn<blog, Integer> idCOL;
    @FXML
    private TableColumn<blog, String> labelCOL;
    @FXML
    private TableColumn<blog, String> categorieCOL;
    @FXML
    private TableColumn<blog, String> desCOL;
    @FXML
    private TableColumn<blog, Date> dateCOL;
    @FXML
    private TableColumn<blog, String> AUTRCOLL;
    @FXML
    private TableColumn<blog, String> imagecoll;
    @FXML
    private TextField auteurTF;
    @FXML
    private Button suppBTN;
    @FXML
    private Button editBTN;
    @FXML
    private DatePicker date_blog;
    @FXML
    private ChoiceBox<categorie> cat_box;
    
    blog prod =new blog();
    blog_Service ps = new blog_Service();
    @FXML
    private ImageView imageVIEW;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         getEvents();
        try {
            ComboxClient();
        } catch (SQLException ex) {
            Logger.getLogger(AddBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    
    
    
    
    public void getEvents() {  

    // TODO
    
    List<blog> evenements = ps.showBlog();
    ObservableList<blog> olp = FXCollections.observableArrayList(evenements);
    tab.setItems(olp);
    idCOL.setCellValueFactory(new PropertyValueFactory("id"));
    labelCOL.setCellValueFactory(new PropertyValueFactory("libelle"));
    desCOL.setCellValueFactory(new PropertyValueFactory("description"));
    AUTRCOLL.setCellValueFactory(new PropertyValueFactory("auteur"));
    imagecoll.setCellValueFactory(new PropertyValueFactory("image"));
   
    categorieCOL.setCellValueFactory(cellData-> new SimpleStringProperty(ps.getClientById(cellData.getValue().getIdCategorie()).getLibelle()));
    categorieCOL.setCellFactory(column->new TableCell<blog,String>(){
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
    dateCOL.setCellValueFactory(new PropertyValueFactory<>("date"));
    dateCOL.setCellFactory(column->{
        return new TableCell<blog,Date>(){
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
    
    
    
    // this.delete();

    }
    
    
    
    private void ComboxClient() throws SQLException {
        categorie_service P = new categorie_service();
     
      ObservableList <categorie> list = FXCollections.observableArrayList(P.getAll()) ;
      
      cat_box.setItems(list);
      
      
        
    }
    
    
    
    
    
    
    

    @FXML
    private void BtnBlog(ActionEvent event) {
    }

    @FXML
    private void Btncategorie(ActionEvent event) {
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
        String DBPath = "C:\\\\Users\\\\USER\\\\EdusexProject\\\\public\\\\images\\\\"  + x + ".jpg";
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
    private void ajouter_blog(ActionEvent event) {
        blog p = new blog();
           
           
            if ( (libelleTF.getText().length() == 0)||(descTF.getText().length() == 0)||(auteurTF.getText().length() == 0)||(imageTF.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
               else {
      p.setLibelle(libelleTF.getText());
        p.setDescription(descTF.getText());
        //p.setDateInteger.parseInt((String) quentTF.getText()));
        
        categorie C = cat_box.getSelectionModel().getSelectedItem();
        p.setIdCategorie(C.getId());
        p.setDate(Date.valueOf(date_blog.getValue()));
        p.setAuteur(auteurTF.getText());
        p.setImage(imageTF.getText());
     

               //   addcombox((ActionEvent) CatFid.getItems());
               ps.addBlog(p);
               getEvents();
               reset();
        }
    }

    @FXML
    private void reset() {
        libelleTF.setText("");
        descTF.setText("");
        auteurTF.setText("");
        imageTF.setText("");
        
        cat_box.setValue(null);
    
        date_blog.setValue(null);
    }

    @FXML
    private void choisirBlog(MouseEvent event) throws SQLException {
        blog e = tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
        ComboxClient();
       // idmodifierField.setText(String.valueOf(e.getId()));
        //idLabel.setText(String.valueOf(e.getId_event()));
        libelleTF.setText(String.valueOf(e.getLibelle()));
        descTF.setText(String.valueOf(e.getDescription()));
        auteurTF.setText(String.valueOf(e.getAuteur()));
        imageTF.setText(String.valueOf(e.getImage()));
        categorie prod = ps.getClientById(e.getIdCategorie());
       cat_box.setValue(prod);
       String path = "C:\\\\Users\\\\USER\\\\EdusexProject\\\\public\\\\images\\\\"+e.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageVIEW.setImage(img);
       
       date_blog.setValue(e.getDate().toLocalDate());
    }

    @FXML
    private void delete_blog(ActionEvent event) {
        blog e = (blog) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
      
        ps.removeBlog(e);   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        getEvents();
    }

    @FXML
    private void modifier_blog(ActionEvent event) {
        
        blog p = tab.getItems().get(tab.getSelectionModel().getSelectedIndex()); 
           
           
            if ( (libelleTF.getText().length() == 0)||(descTF.getText().length() == 0)||(auteurTF.getText().length() == 0)||(imageTF.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
               else {
      p.setLibelle(libelleTF.getText());
        p.setDescription(descTF.getText());
        //p.setDateInteger.parseInt((String) quentTF.getText()));
        
        categorie C = cat_box.getSelectionModel().getSelectedItem();
        p.setIdCategorie(C.getId());
        p.setDate(Date.valueOf(date_blog.getValue()));
        p.setAuteur(auteurTF.getText());
        p.setImage(imageTF.getText());
                 
        ps.updateBlog(p);
        getEvents();
        reset();
    }
    }
    @FXML
    private void goToBlog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addBlog.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void goToCategorie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addCategorie.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
}

