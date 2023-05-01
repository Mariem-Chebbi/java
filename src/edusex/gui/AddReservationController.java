/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Product;
import edusex.entities.Reservation;
import edusex.services.ServiceProduct;
import edusex.services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddReservationController implements Initializable {

    @FXML
    private Button backBTN;
    @FXML
    private TableView<Reservation> tab;
    @FXML
    private TableColumn<Reservation, Integer> idCOL;
    @FXML
    private TableColumn<Reservation, Integer> quentCOL;
    @FXML
    private Button submitBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private TableColumn<Reservation, String> clientCOL;
    @FXML
    private TableColumn<Reservation, String> prodCOL;
    @FXML
    private TableColumn<Reservation, Date> dateCOL;
    @FXML
    private TableColumn<Reservation, String> etatCOL;
    @FXML
    private ChoiceBox<Product> produitDBX;
    @FXML
    private DatePicker datePICK;
    @FXML
    private ChoiceBox<String> etatDBX;
    @FXML
    private TextField quentTF;
     Reservation prod =new Reservation();
    ServiceReservation ps = new ServiceReservation();
    @FXML
    private Button prodBTN;
    @FXML
    private Button reservationBTN;
    @FXML
    private Button suppbtn;
    @FXML
    private Button modifbtn;
    @FXML
    private Button statBtn;
    @FXML
    private Button certificationBTN;
    @FXML
    private Button formationBTN;
    @FXML
    private Button inscriptionBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getEvents();
        ComboxClient();
        reset();
        
    }    
    public void getEvents() {  

    // TODO
    
    List<Reservation> evenements = ps.showReservation();
    ObservableList<Reservation> olp = FXCollections.observableArrayList(evenements);
    tab.setItems(olp);
    idCOL.setCellValueFactory(new PropertyValueFactory("id"));
    quentCOL.setCellValueFactory(new PropertyValueFactory("quantite"));
   // clientCOL.setCellValueFactory(new PropertyValueFactory("description"));
   clientCOL.setCellValueFactory(cellData-> new SimpleStringProperty(ps.getUserById(cellData.getValue().getUserId())));
    clientCOL.setCellFactory(column->new TableCell<Reservation,String>(){
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
    prodCOL.setCellValueFactory(cellData-> new SimpleStringProperty(ps.getClientById(cellData.getValue().getIdProduit()).getLibelle()));
    prodCOL.setCellFactory(column->new TableCell<Reservation,String>(){
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
    dateCOL.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
    dateCOL.setCellFactory(column->{
        return new TableCell<Reservation,Date>(){
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
    etatCOL.setCellValueFactory(new PropertyValueFactory("etat"));
    
    
    // this.delete();

    }//ge

    @FXML
    private void choisirProduit(MouseEvent event) {
        Reservation e = tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
        ComboxClient();
       // idmodifierField.setText(String.valueOf(e.getId()));
        //idLabel.setText(String.valueOf(e.getId_event()));
        quentTF.setText(String.valueOf(e.getQuantite()));
        Product prod = ps.getClientById(e.getIdProduit());
       produitDBX.setValue(prod);
       etatDBX.setValue(e.getEtat());
       datePICK.setValue(e.getDateReservation().toLocalDate());
       
       submitBTN.setOpacity(0);
       submitBTN.setVisible(false);
                
                modifbtn.setOpacity(1);
                modifbtn.setVisible(true);
                suppbtn.setOpacity(1);
                suppbtn.setVisible(true);
    }


    @FXML
    private void reset() {
        quentTF.setText("");
        produitDBX.setValue(null);
        etatDBX.setValue(null);
        datePICK.setValue(null);
        submitBTN.setOpacity(1);
        submitBTN.setVisible(true);
                
                modifbtn.setOpacity(0);
                modifbtn.setVisible(false);
                suppbtn.setOpacity(0);
                suppbtn.setVisible(false);
        
    }
    
    private void ComboxClient() {
        ServiceProduct P = new ServiceProduct();
     
      ObservableList <Product> list = FXCollections.observableArrayList(P.showProduct()) ;
      
      produitDBX.setItems(list);
      
      ObservableList <String> etats = FXCollections.observableArrayList("en cours","traiter","refuser");
      etatDBX.setItems(etats);
        
    }

    @FXML
    private void ajouterReservation(ActionEvent event) {
        Reservation p = new Reservation();
        ServiceProduct sp = new ServiceProduct();
        LocalDate today = LocalDate.now();
        LocalDate selected = datePICK.getValue();
        
        
        
        
           
           
            if ( (quentTF.getText().length() == 0)) 
            {
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
            else if (!quentTF.getText().matches("[0-9]+"))
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
                
            p.setQuantite(Integer.parseInt((String) quentTF.getText()));
        Product C = produitDBX.getSelectionModel().getSelectedItem();
        p.setIdProduit(C.getId());
        p.setDateReservation(Date.valueOf(datePICK.getValue()));
        p.setEtat(etatDBX.getSelectionModel().getSelectedItem());

         
        
       if (C.getQuantite()<=p.getQuantite())
        {
             Notifications no;
               Image img = new Image("/edusex/gui/error.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Quantité non disponible !")
                        .text("La quantiter demander n'est pas disponible en stock")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
               
        }
        
       else if(selected.isBefore(today))
        {
             Notifications no;
               Image img = new Image("/edusex/gui/error.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Date invalide !")
                        .text("La date de reservation doit etre supperieur a la date d'aujourdhui")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
        
        }
        
        else{    
               ps.addReservation(p);
               getEvents();
               reset();
               C.setQuantite(C.getQuantite()-p.getQuantite());
               sp.updateProduct(C);
               Notifications no;
              
               Image img = new Image("/edusex/gui/ok.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Reservation Ajoutée avec succee !")
                        .text("Reservation en cours de traitement")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
        }

               //   addcombox((ActionEvent) CatFid.getItems());
         }      
        
    }

    @FXML
    private void supReservation(ActionEvent event) {
         Reservation e = (Reservation) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
      
        ps.removeReservation(e);   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Reservation supprimer");
        alert.setContentText("Reservation supprimer avec succee!");
        alert.showAndWait();
        getEvents();
        reset();
    }

    @FXML
    private void modifierReservation(ActionEvent event) {
         Reservation p = (Reservation) tab.getItems().get(tab.getSelectionModel().getSelectedIndex()); 
         ServiceProduct sp = new ServiceProduct();
          LocalDate today = LocalDate.now();
        LocalDate selected = datePICK.getValue();
//Product e = new Product();
       // e.setId(Integer.valueOf(idmodifierField.getText()));
       
       if ( (quentTF.getText().length() == 0)) 
            {
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
            else if (!quentTF.getText().matches("[0-9]+"))
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
                
            p.setQuantite(Integer.parseInt((String) quentTF.getText()));
        Product C = produitDBX.getSelectionModel().getSelectedItem();
        p.setIdProduit(C.getId());
        p.setDateReservation(Date.valueOf(datePICK.getValue()));
        p.setEtat(etatDBX.getSelectionModel().getSelectedItem());

         
        
       if (C.getQuantite()<=p.getQuantite())
        {
             Notifications no;
               Image img = new Image("/edusex/gui/error.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Quantité non disponible !")
                        .text("La quantiter demander n'est pas disponible en stock")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
               
        }
        
       else if(selected.isBefore(today))
        {
             Notifications no;
               Image img = new Image("/edusex/gui/error.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Date invalide !")
                        .text("La date de reservation doit etre supperieur a la date d'aujourdhui")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
        
        }
        
        else{    
               ps.updateReservation(p);
               getEvents();
               reset();
               C.setQuantite(C.getQuantite()-p.getQuantite());
               sp.updateProduct(C);
               Notifications no;
              
               Image img = new Image("/edusex/gui/ok.png");
               
               no = Notifications.create()
                       .darkStyle()
                        .title("Reservation Modifier !")
                        .text("Reservation Modifier !")
                        .graphic(new ImageView(img))
                        .position(Pos.TOP_RIGHT)
                        .hideAfter(Duration.seconds(6));
                no.show();
        }

               //   addcombox((ActionEvent) CatFid.getItems());
         }      
        
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
    
}
