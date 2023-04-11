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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getEvents();
        ComboxClient();
        
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
    }


    @FXML
    private void reset() {
        quentTF.setText("");
        produitDBX.setValue(null);
        etatDBX.setValue(null);
        datePICK.setValue(null);
        
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
           
           
            if ( (quentTF.getText().length() == 0)) {
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
         p.setQuantite(Integer.parseInt((String) quentTF.getText()));
        Product C = produitDBX.getSelectionModel().getSelectedItem();
        p.setIdProduit(C.getId());
        p.setDateReservation(Date.valueOf(datePICK.getValue()));
        p.setEtat(etatDBX.getSelectionModel().getSelectedItem());

               //   addcombox((ActionEvent) CatFid.getItems());
               ps.addReservation(p);
               getEvents();
               reset();
        }
    }

    @FXML
    private void supReservation(ActionEvent event) {
         Reservation e = (Reservation) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
      
        ps.removeReservation(e);   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        getEvents();
    }

    @FXML
    private void modifierReservation(ActionEvent event) {
         Reservation p = (Reservation) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());        
//Product e = new Product();
       // e.setId(Integer.valueOf(idmodifierField.getText()));
        
        if ( (quentTF.getText().length() == 0)) {
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
         p.setQuantite(Integer.parseInt((String) quentTF.getText()));
        Product C = produitDBX.getSelectionModel().getSelectedItem();
        p.setIdProduit(C.getId());
        p.setDateReservation(Date.valueOf(datePICK.getValue()));
        p.setEtat(etatDBX.getSelectionModel().getSelectedItem());
        //e.setDateFormatiob(Integer.parseInt(dateTF.getText()));
                 
        ps.updateReservation(p);
        getEvents();
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
    
}
