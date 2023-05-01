/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.blog;
import edusex.services.blog_Service;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherDetailsBlogController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private Label nomLabel;
    @FXML
    private Label QunatiteLabel;
    @FXML
    private TextArea descriptionLabel;
    @FXML
    private Label prixlabel;
        blog_Service sc = new blog_Service();
        private blog b;
    @FXML
    private Button backbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setBlog(blog e) {
        
        //blog e =this.b;
        System.out.println(e);
        nomLabel.setText(e.getLibelle());
        System.out.println(e.getLibelle());
       
        descriptionLabel.setText(e.getDescription());
        prixlabel.setText(e.getAuteur());
        QunatiteLabel.setText(sc.getClientById(e.getIdCategorie()).getLibelle());
        String path = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"+e.getImage();
        File file=new File(path);
        Image img = new Image(file.toURI().toString());
        imageview.setImage(img);
        
       

    }

    @FXML
    private void backtoblog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherBlog.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    
}
