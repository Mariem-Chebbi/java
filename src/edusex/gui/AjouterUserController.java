/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import edusex.entities.Role;
import edusex.entities.User;
import edusex.services.UserService;
import javafx.util.Duration;
import static edusex.utils.BCryptPass.cryptMDP;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterUserController implements Initializable {

    @FXML
    private DatePicker tfdate_naissance;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpassword;   
    @FXML
    private TextField tfnum_tel;
    @FXML
    private TextField ville;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfimage;
    @FXML
    private ComboBox<Role> comborole;
    UserService userService  = new UserService();
    String   erreur  ;
List<Role> rolesList = Arrays.asList(Role.ADMIN,Role.PERSONNEL, Role.CLIENT);
    @FXML
    
    private Button btnback;
    @FXML
    private Button imgBTN;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             // TODO
      // ChoiceBox<Role> comborole = new ChoiceBox<>();
  comborole.getItems().addAll(rolesList);
    }    
@FXML
private void ajouter(ActionEvent event) throws IOException {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);
    StringBuilder errors = new StringBuilder();
    String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    if (tfnom.getText().trim().isEmpty()) {
        errors.append("- Please enter un Nom\n");
    }
    if (tfimage.getText().trim().isEmpty()) {
        errors.append("- Please enter un Image\n");
    }
    if (ville.getText().trim().isEmpty()) {
        errors.append("- Please enter un Ville\n");
    }
    if (tfpassword.getText().isEmpty() || !tfpassword.getText().matches(passwordRegex)) {
        errors.append("- Le mot de passe doit contenir au moins 8 caractères, avec au moins une minuscule, une majuscule, un chiffre et un caractère spécial.\n");
    }
    if (tfprenom.getText().trim().isEmpty()) {
        errors.append("- Please enter un prenom\n");
    }
    if (tfemail.getText().trim().isEmpty()) {
        errors.append("- Please enter un Email\n");
    } else if (!pat.matcher(tfemail.getText()).matches()) {
        errors.append("- Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
    }
    if (tfnum_tel.getText().trim().isEmpty()) {
        errors.append("- Please enter un numéro de téléphone\n");
    } else if (tfnum_tel.getText().trim().length() != 8 || !tfnum_tel.getText().trim().matches("\\d+")) {
        errors.append("- Le numéro de téléphone doit comporter 8 chiffres.\n");
    }
    if (tfdate_naissance.getValue() == null) {
        errors.append("- Please enter a Date\n");
    } else {
        LocalDate currentDate = LocalDate.now();
        if (tfdate_naissance.getValue().isAfter(currentDate.minusYears(18))) {
            errors.append("- Veuillez saisir une date de naissance valide pour un utilisateur âgé d'au moins 18 ans.\n");
        }
    }
    if (ville.getText().trim().isEmpty()) {
        errors.append("- Please enter a Ville\n");
    }
    if (comborole.getSelectionModel().isEmpty()) {
        errors.append("- Veuillez sélectionner un rôles.\n");
    }

    if (errors.length() > 0) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errors");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else {
        User u = new User();
        u.setNom(tfnom.getText());
        u.setPrenom(tfprenom.getText());
        u.setEmail(tfemail.getText());
        u.setPassword(cryptMDP(tfpassword.getText()));
        u.setNum_tel(Integer.parseInt(tfnum_tel.getText()));
        u.setDate_naissance(java.sql.Date.valueOf(tfdate_naissance.getValue()));
        u.setVille(ville.getText());
        u.setImage(tfimage.getText());
        u.setRoles(comborole.getValue());
        userService.Ajouter(u);
        Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
        btnajouter.getScene().setRoot(loader);
    }

        
    }
    public void setTextField( String Nom, String Prenom, String Email,  String num_tel, Date date_naissance, String Ville,String Image,Role roles ) {
        
        tfnom.setText(Nom);
        tfprenom.setText(Prenom);
        tfemail.setText(Email);
        tfnum_tel.setText(num_tel);
        LocalDate localDate = date_naissance.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        tfdate_naissance.setValue(localDate);
        ville.setText(Ville);
        tfimage.setText(Image);
        comborole.setValue(roles);
        
    } 
    public void setPasswordField(String password) {
    tfpassword.setText(password);
}
    
    

    @FXML
    private void back(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherUser.fxml"));
            btnback.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void uploadImage(ActionEvent event)throws FileNotFoundException, IOException  {
        
        
        
        
               Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\Users\\\\user\\\\Documents\\\\NetBeansProjects\\\\image\\\\"  + x + ".jpg";
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
            tfimage.setText(Path);
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
    
}
