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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import edusex.entities.Role;
import edusex.entities.User;
import edusex.services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierUserController implements Initializable {

    @FXML
    private DatePicker tfdate_naissance;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
private TextField tfpassword;   
    @FXML
    private TextField tfnum_tel;
    @FXML
    private TextField tfid;
    @FXML
    private TextField ville;
    @FXML
    private TextField tfimage;
    @FXML
    private Button btnmodifier;
  @FXML
    private ComboBox<Role> comborole;
    UserService userService = new UserService();
    String erreur;
    List<Role> rolesList = Arrays.asList(Role.ADMIN, Role.PERSONNEL,Role.CLIENT);
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     */
     private String selectedUserId;
    @FXML
    private Button imageBTN;

    public void initData(String selectedUserId) {
        this.selectedUserId = selectedUserId;
        // Get the selected user's data
        User selectedUser = userService.findByEmail(selectedUserId);
        if (selectedUser != null) {
            // Populate the form with the selected user's data
            tfid.setText(Integer.toString(selectedUser.getId()));

            tfemail.setText(selectedUser.getEmail());
            tfpassword.setText(selectedUser.getPassword());
            tfnom.setText(selectedUser.getNom());
            tfprenom.setText(selectedUser.getPrenom());
            tfnum_tel.setText(Integer.toString(selectedUser.getNum_tel()));
            ville.setText(selectedUser.getVille());
            tfdate_naissance.setValue(selectedUser.getDate_naissance().toLocalDate());
            tfimage.setText(selectedUser.getImage());
            comborole.setValue(selectedUser.getRoles());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
        // ChoiceBox<Role> comborole = new ChoiceBox<>();
        comborole.getItems().addAll(rolesList);
        initData(selectedUserId);
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
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
        u.setId(Integer.parseInt(tfid.getText()));
        u.setNom(tfnom.getText());
        u.setPrenom(tfprenom.getText());
        u.setEmail(tfemail.getText());
        u.setPassword(tfpassword.getText());
        u.setNum_tel(Integer.parseInt(tfnum_tel.getText()));
        u.setDate_naissance(java.sql.Date.valueOf(tfdate_naissance.getValue()));
        u.setVille(ville.getText());
        u.setImage(tfimage.getText());
        u.setRoles(comborole.getValue());
            
            User s = userService.findByEmail(selectedUserId);
            userService.modifier(u);
            try {
                //navigation
                Parent loader = FXMLLoader.load(getClass().getResource("AfficherUser.fxml"));
                btnback.getScene().setRoot(loader);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
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
