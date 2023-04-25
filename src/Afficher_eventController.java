/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.List;
import Entity.Evenement;
import Service.ServiceEvenement;
import com.google.zxing.qrcode.QRCodeWriter;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Afficher_eventController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private VBox vb;
HBox column1 = new HBox();
    @FXML
 private TextField rech;
    @FXML
    private Button idqr;
    @FXML
    private Button idqr1;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       rech.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            filterEvenement(newValue);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    });
        
     //    vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);


        List<Evenement> l = new ArrayList<Evenement>();
        ServiceEvenement ser = new ServiceEvenement();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Evenement d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getNom_event());
            Label label2 = new Label(d.getLieu_event());
             Label label3 = new Label(d.getDescription());
             Label label4 = new Label(d.getDate_debut().toString());
             Label label5 = new Label(d.getDate_fin().toString());
             
            

       //     label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
          //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

         //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
       //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, label2, label3, label4 , label5);
            hbox.setSpacing(120);
            hbox.setAlignment(Pos.CENTER_LEFT);
          //  hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }

     @FXML
            private void trier(ActionEvent event) {
              vb.getChildren().clear();
        //column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
          //       new Label("date:"));
            //   vb.getChildren().add(column1); 
              //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
         //column1.setSpacing(100);
           //         column1.setAlignment(Pos.CENTER_LEFT);


                List<Evenement> l = new ArrayList<Evenement>();
                ServiceEvenement ser = new ServiceEvenement();
                try {
                    l = ser.sortbydate();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                ///////////////////////////////////////////////user
                for (Evenement d : l) {


                    HBox hbox = new HBox();
                    Label label1 = new Label(d.getNom_event());
                    Label label2 = new Label(d.getLieu_event());
                    Label label3 = new Label(d.getDescription());
                     Label label4 = new Label(d.getDate_debut().toString());
                      Label label5 = new Label(d.getDate_fin().toString());

                   hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
                  //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                 //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                   //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                    //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

                 //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
               //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                    hbox.getChildren().addAll(label1, label2, label3, label4 , label5);
                    hbox.setSpacing(120);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                  //  hbox.setPadding(new Insets(10));

                    vb.getChildren().add(hbox);
                }

            }



    @FXML
    private void actualiser(ActionEvent event) {
        vb.getChildren().clear();
  

        List<Evenement> l = new ArrayList<Evenement>();
        ServiceEvenement ser = new ServiceEvenement();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Evenement d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getNom_event());
            Label label2 = new Label(d.getLieu_event());
            Label label3 = new Label(d.getDescription());
            Label label4 = new Label(d.getDate_debut().toString());
            Label label5 = new Label(d.getDate_fin().toString());
         
       //     label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
            
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
          //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

         //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
       //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, label2, label3, label4 , label5);
            hbox.setSpacing(120);
            hbox.setAlignment(Pos.CENTER_LEFT);
          //  hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }
    
    private void filterEvenement(String searchValue) throws SQLException {
    vb.getChildren().clear(); // Clear the VBox to remove all previous entries

    ServiceEvenement ser = new ServiceEvenement();
    List<Evenement> evenements = ser.readAll();

    for (Evenement evenement : evenements) {
        if (evenement.getNom_event().toLowerCase().contains(searchValue.toLowerCase())
                || evenement.getLieu_event().toLowerCase().contains(searchValue.toLowerCase())) {
            HBox hbox = new HBox();
            Label label1 = new Label(evenement.getNom_event());
            Label label2 = new Label(evenement.getLieu_event());
            Label label3 = new Label(evenement.getDescription());
             Label label4 = new Label(evenement.getDate_debut().toString());
            Label label5 = new Label(evenement.getDate_fin().toString());

            hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");

            hbox.getChildren().addAll(label1, label2, label3, label4 , label5);
            hbox.setSpacing(120);
            hbox.setAlignment(Pos.CENTER_LEFT);

            vb.getChildren().add(hbox); // Add the HBox to the VBox
        }
    }
}


    
     @FXML
    private void goajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Ajout_event.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*private void gosupp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/Supprimer_Covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }*/
     @FXML
    private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui/modifier_event.fxml")) ; 
        Scene scene = new Scene(root);
      
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
  private void  CreatePDF() {
  
      try {
         // Récupération de la liste des événements
         
         
         // Création du document PDF
    Document document = new Document();
         PdfWriter.getInstance(document, new FileOutputStream("liste_des_evenements.pdf"));
         document.addTitle("Liste des événements");
         document.addAuthor("Mon application");
         document.addSubject("Liste des événements enregistrés");
         document.addKeywords("événements, enregistrement, liste");
         
         // Set custom formatting for the PDF document
         document.setMargins(50, 50, 50, 50);
         Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
         Font subtitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);
         
         document.open();
         
         // Add content to the PDF document
         Paragraph title = new Paragraph("Liste des événements", titleFont);
         title.setAlignment(Paragraph.ALIGN_CENTER);
         document.add(title);
         
         document.add(new Paragraph(""));
         document.add(new Paragraph("Voici la liste des événements enregistrés dans l'application : ", subtitleFont));
         document.add(new Paragraph(""));
         
         // Call the readall() method of your ServiceEvenement to retrieve the list of events
         ServiceEvenement service = new ServiceEvenement();
         List<Evenement> events = service.readAll();
         
         // Create a table with three columns
         PdfPTable table = new PdfPTable(3);
         table.setWidthPercentage(100);
         
         // Add column headers to the table
         PdfPCell cell1 = new PdfPCell(new Phrase("nom_event"));
         PdfPCell cell2 = new PdfPCell(new Phrase("Lieu"));
         PdfPCell cell3 = new PdfPCell(new Phrase("Description"));
         PdfPCell cell4 = new PdfPCell(new Phrase("Date_debut"));
         PdfPCell cell5 = new PdfPCell(new Phrase("Date_fin"));
         
         table.addCell(cell1);
         table.addCell(cell2);
         table.addCell(cell3);
         
         // Add event data to the table
         for (Evenement event : events) {
            table.addCell(event.getNom_event());
            table.addCell(event.getLieu_event());
              table.addCell(event.getDescription());
            table.addCell(event.getDate_debut().toString());
            table.addCell(event.getDate_fin().toString());
         }
         
         // Add the table to the document
         document.add(table);
         
         // Close the document
         document.close();
         
         System.out.println("Document créé avec succès!");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
  @FXML
   private void generateQR(ActionEvent event) {
    // Création de l'objet QRCodeWriter
    
    
    QRCodeWriter qrCodeWriter = new QRCodeWriter();

    // Paramètres de l'encodeur
    int width = 300;
    int height = 300;
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Enregistrer le QR code");
    File file = fileChooser.showSaveDialog(null);

    // Configuration des paramètres de l'encodeur
    Map<EncodeHintType, Object> hintMap = new HashMap<>();
    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

    try {
        String data = "https://youtu.be/nZ1JwKRxiLs";
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hintMap);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        String fileName = file.getName().concat(".png");
        try (OutputStream out = new FileOutputStream(new File(file.getParent(), fileName))) {
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } catch (WriterException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void PDF(ActionEvent event) {
        CreatePDF();
    }

 
    
}




// problem dans ll affichage de tri 
// chnager vbox to tableview