/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import edusex.entities.Role;
import edusex.entities.User;
import edusex.services.UserService;
import edusex.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherUserController implements Initializable{

   
    @FXML
    private TableColumn<User, String> nomcol;
    @FXML
    private TableColumn<User, String> prenomcol;
    @FXML
    private TableColumn<User, String> emailcol;
    @FXML
    private TableColumn<User, String> passwordcol;
    @FXML
    private TableColumn<User,Integer> num_telcol;
    @FXML
    private TableColumn<User, Date> date_naissancecol;
    @FXML
    private TableColumn<User, String> villecol;
    @FXML
    private TableColumn<User, String> imagecol;
   @FXML
    private TableView<User> usertable;
    Connection con;
    UserService us = new UserService();
    @FXML
    private TableColumn<User, Role> rolescol;
    ObservableList<User> data = FXCollections.observableArrayList();
    ObservableList<User> userList = FXCollections.observableArrayList();
    User user = null;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnrefresh;
    @FXML
    private Button btnlogout;
    @FXML
    private TextField recherche;
    @FXML
    private Button btnmodifier;
   @FXML
    private Button stat;
    
    @FXML
    private ComboBox<String> cbx_search;
     //private AnchorPane displayArea;
    //private UserService sus = new UserService();

  int id_d;
    @FXML
    private ImageView imageView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> searchOptions = Arrays.asList("Roles", "Nom/Prenom");
        cbx_search.setItems(FXCollections.observableArrayList(searchOptions));
        try {
            UserService us = new UserService();
            List<User> user = us.afficher();
            ObservableList<User> list = FXCollections.observableArrayList(user);

            Connection con = MyDB.getInstance().getcon();
            ResultSet rs = con.createStatement().executeQuery("select * from user");

            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("id"),
                       rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getInt("num_Tel"),


                   
                        
                        

                        rs.getString("ville"),
                       rs.getDate("date_naissance"),

                        rs.getString("Image"),
                        Role.valueOf(rs.getString("roles"))));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           loadDate();
        usertable.setItems(userList);
    }
private void loadDate() {
       
        emailcol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<>("Password"));
                nomcol.setCellValueFactory(new PropertyValueFactory<>("Nom"));

                prenomcol.setCellValueFactory(new PropertyValueFactory<>("Prenom"));

        num_telcol.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        villecol.setCellValueFactory(new PropertyValueFactory<>("Ville"));
                date_naissancecol.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));

        imagecol.setCellValueFactory(new PropertyValueFactory<>("Image"));
        rolescol.setCellValueFactory(new PropertyValueFactory<>("roles"));

    }
    @FXML
    private void ajouter(ActionEvent event) {
           try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterUser.fxml"));
            btnajouter.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    } 
   @FXML
    private void gotostat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Chart_1.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    @FXML
    private void supprimer(ActionEvent event) {
          user = usertable.getSelectionModel().getSelectedItem();
        UserService ds = new UserService();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            us.supprimer(user);

        } else if (input == 1) {

        }
    }
    

    @FXML
    private void refreshlist(ActionEvent event) throws SQLException {
        data.clear();
        data = FXCollections.observableArrayList(us.afficher());
         emailcol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<>("Password"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        
        num_telcol.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        villecol.setCellValueFactory(new PropertyValueFactory<>("Ville"));

                

        date_naissancecol.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        imagecol.setCellValueFactory(new PropertyValueFactory<>("Image"));
        rolescol.setCellValueFactory(new PropertyValueFactory<>("roles"));

        usertable.setItems(data);
    }

    @FXML
    private void Logout(ActionEvent event)throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void rechercheavance(KeyEvent event) {
              
        if(cbx_search.getValue()=="Nom/Prenom"){
        
        FilteredList<User> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherche.getText());
        recherche.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (user.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getPrenom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getEmail().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getPassword().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(user.getNum_tel()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getImage().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;

                } else if (String.valueOf(user.getDate_naissance()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<User> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(usertable.comparatorProperty());
        usertable.setItems(filtereddata);}
        else{
            //System.out.println(id.departement);
        FilteredList<User> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherche.getText());
        recherche.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                 if (String.valueOf(user.getRoles()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<User> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(usertable.comparatorProperty());
        usertable.setItems(filtereddata);
        }
    
          
//    }
//    @FXML
//    private List<User> triDesc(ActionEvent event) throws SQLException {
//            Statement Ste;
//
//        ArrayList<User> user = new ArrayList<>();
//     try {
//        Ste = con.createStatement();
//        String req = "SELECT * FROM user ORDER BY `user`.`ville` DESC;";
//        ResultSet res = Ste.executeQuery(req);
//        while(res.next()){
//              User u = new User();
//            u.setId(res.getInt("id"));
//            u.setEmail(res.getString("email"));
//            u.setPassword(res.getString("password"));
//            u.setNom(res.getString("nom"));
//            u.setPrenom(res.getString("prenom"));
//            u.setNum_tel(res.getInt("num_tel"));
//            u.setVille(res.getString("ville"));
//            u.setDate_naissance(res.getDate("date_naissance"));
//            u.setImage(res.getString("image"));
//            u.setRole(Role.valueOf(res.getString("role")));
//            user.add(u);
//           
//
//         
//        }
//    } catch (SQLException ex) {
//        System.out.println("err" + ex.getMessage());
//    }
//    return user;
//} 
//        @FXML
//    private void triAsc(ActionEvent event) throws SQLException {
//        ObservableList<User> list = FXCollections.observableArrayList();
//        try {
//            
//            String requete = "String requete = \"SELECT * FROM user ORDER BY `user`.`ville` ASC\";";
//           
//              PreparedStatement pst = con.prepareStatement(requete);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//              
//   
//             list.add(new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getDate(8), rs.getString(9))); 
//       
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        } 
//      usertable.setItems(list);
////col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
//       data = FXCollections.observableArrayList(us.afficher());
//         emailcol.setCellValueFactory(new PropertyValueFactory<>("Email"));
//        passwordcol.setCellValueFactory(new PropertyValueFactory<>("Password"));
//        nomcol.setCellValueFactory(new PropertyValueFactory<>("Nom"));
//        prenomcol.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
//        
//        num_telcol.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
//        villecol.setCellValueFactory(new PropertyValueFactory<>("Ville"));
//
//                
//
//        date_naissancecol.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
//        imagecol.setCellValueFactory(new PropertyValueFactory<>("Image"));
//        rolecol.setCellValueFactory(new PropertyValueFactory<>("role"));
//
//        usertable.setItems(data);
//    
//
//    
//    }
//    
       

 


    }


     @FXML
    private void update(ActionEvent event) {
        // Get the selected user
        User selectedUser = usertable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                 
            
                // Pass the selected user's ID to the ModifierUserController
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
                Parent root = loader.load();
                ModifierUserController controller = loader.getController();
                controller.initData(selectedUser.getEmail());
                System.out.println("email dans update est:" + selectedUser.getEmail());

                // Navigate to the ModifierUserController
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    
    
    @FXML
    private void choisirUser(MouseEvent event) {
        User e = (User) usertable.getItems().get(usertable.getSelectionModel().getSelectedIndex());
       // idmodifierField.setText(String.valueOf(e.getId()));
        //idLabel.setText(String.valueOf(e.getId_event()));
        
        
        String path = "C:\\\\Users\\\\user\\\\Documents\\\\NetBeansProjects\\\\image\\\\"  +e.getImage();
               File file=new File(path);
              Image imge = new Image(file.toURI().toString());
            imageView.setImage(imge);
                          
    }
    
    
    
    
    
    
    
    
       @FXML
private void tri(ActionEvent event) throws SQLException {
    data.clear();
    try {
        String requete = "SELECT * FROM user ORDER BY ville ASC";
        PreparedStatement pst = con.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
           // int id = rs.getInt("id");
            
            String email = rs.getString("Email");
            String password = rs.getString("Password");
            
                        String nom = rs.getString("Nom");
                                    String prenom = rs.getString("Prenom");
                                               int num_tel = rs.getInt("num_tel");
            String ville = rs.getString("ville");

                        LocalDate date_naissance = rs.getDate("date_naissance").toLocalDate();


          
            String image = rs.getString("Image");
                        Role.valueOf(rs.getString("roles"));
            String roles = null;
            User ur = new User( email, password, nom, prenom, num_tel, ville, date_naissance, image, roles);
            data.add(ur);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
//            id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));

   emailcol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<>("Password"));
                nomcol.setCellValueFactory(new PropertyValueFactory<>("Nom"));

                prenomcol.setCellValueFactory(new PropertyValueFactory<>("Prenom"));

        num_telcol.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        villecol.setCellValueFactory(new PropertyValueFactory<>("ville"));
                date_naissancecol.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));

        imagecol.setCellValueFactory(new PropertyValueFactory<>("Image"));
        rolescol.setCellValueFactory(new PropertyValueFactory<>("roles"));

    usertable.setItems(data);
}}
