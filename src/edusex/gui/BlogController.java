/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edusex.gui;

import com.itextpdf.text.BaseColor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import edusex.entities.blog;
import edusex.services.blog_Service;
import edusex.services.categorie_service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edusex.entities.categorie;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
        
/**
 * FXML Controller class
 *
 * @author Monta
 */
public class BlogController implements Initializable {

    private Label nomProduirLabel;
    @FXML
    private Label QunatiteLabel;
    @FXML
    private TextArea descriptionLabel;
    @FXML
    private Label prixlabel;
    @FXML
    private ImageView imageview;
    
    blog_Service sc = new blog_Service();
    @FXML
    private Label nomLabel;
    @FXML
    private Button affdetails;
    @FXML
    private Label id;
    @FXML
    private AnchorPane main;
    
    private blog b;
    @FXML
    private TextField idProduct;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
      public void setBlog(blog e) {
        this.b=e;
        nomLabel.setText(e.getLibelle());
       idProduct.setText(String.valueOf(e.getId()));
        descriptionLabel.setText(e.getDescription());
        prixlabel.setText(e.getAuteur());
        QunatiteLabel.setText(sc.getClientById(e.getIdCategorie()).getLibelle());
        date.setText(e.getDate().toString());
        String path = "C:\\\\Users\\\\LENOVO\\\\EdusexProject\\\\public\\\\images\\\\"+e.getImage();
        File file=new File(path);
        Image img = new Image(file.toURI().toString());
        imageview.setImage(img);
        id.setText(String.valueOf(e.getId()));

    }

//    @FXML
//    private void afficherdetails(ActionEvent event) {
//    }
    @FXML
    private void afficherdetails(ActionEvent event) throws IOException {
        blog_Service sr = new blog_Service();
        System.out.println(idProduct.getText());
        blog p = sr.getBlogById(Integer.parseInt((String) idProduct.getText()));
        System.out.println(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDetailsBlog.fxml"));
        
        
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    
    AfficherDetailsBlogController controller = loader.getController();
        //System.out.println(p);
        //controller.setDetProduit(p);
        controller.setBlog((blog)p);
    
    stage.show();
    }

    @FXML
    private void pdf(ActionEvent event) {
            try {
            genererPDF(sc, main.getScene().getWindow(),b);
        } catch (DocumentException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void genererPDF(blog_Service st, Window parentWindow, blog b) throws DocumentException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer les données");
        File selectedFile = fileChooser.showSaveDialog(parentWindow);

        if (selectedFile != null) {
            try {
                // Créer un document PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();
                
                // Ajouter les éléments de l'interface utilisateur 
                com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.RED);
                Paragraph title = new Paragraph(b.getLibelle(), titleFont);

                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(10f);
                document.add(title);
                
                com.itextpdf.text.Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

                Paragraph produits = new Paragraph(b.getDescription(), regularFont);
                produits.setAlignment(Element.ALIGN_LEFT);
                produits.setSpacingAfter(10f);
                document.add(produits);
                
               
                Paragraph date = new Paragraph("Date: " + LocalDate.now().toString(), regularFont);
                date.setAlignment(Element.ALIGN_LEFT);
                date.setSpacingAfter(10f);
                document.add(date);

                document.close();
            } catch (IOException | DocumentException ex) {
                System.err.println("Erreur lors de l'écriture dans le fichier: " + ex.getMessage());
            }
        } else {
            System.out.println("La sélection de fichier a été annulée");
        }
    }
}
