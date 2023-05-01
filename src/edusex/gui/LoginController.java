/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;
//import static edusex.entities.Role.CLIENT;
import javafx.scene.control.CheckBox;

import javafx.util.Duration;
import javafx.scene.image.Image;
import java.awt.Desktop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.embed.swing.SwingFXUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import edusex.gui.AfficherUserController;
import edusex.gui.CaptchaController;
//import nl.captcha.Captcha;
//import nl.captcha.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.PasswordField;

import edusex.utils.BCryptPass;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import edusex.entities.User;
import edusex.services.UserService;
import edusex.services.UserSession;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.CheckBox;
import edusex.entities.Role;
import nl.captcha.Captcha;
/**
 * FXML Controller class
 *
 * @author user
 */
        public class LoginController implements Initializable {
private static String currentMailReset;
    @FXML
    private TextField tfemail;
 @FXML
    private PasswordField tfpassword;

    private Label errorLabel;
  @FXML
   private CheckBox cbox;
    private Connection con;
    UserService us =new UserService() ;
     User u2,u3,u4;
     String codeV;
     Parent root; 
      Stage stage;
      int btn=0;
    private Scene scene;
    private TextField resetcodeField;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btnsignup;
    @FXML
    private Label ForgotPassword;   
            public static String mail;
            @FXML
private ImageView cap;
            @FXML
private TextField code;
 //@FXML
 //   private ImageView cap;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
         

    public Captcha setCaptcha() {
      Captcha captcha = new Captcha.Builder(250, 200)
                .addText()
               .addBackground()
               .addNoise()
               .gimp()
                .addBorder()
               .build();

       System.out.println(captcha.getImage());
        Image image = SwingFXUtils.toFXImage(captcha.getImage(), null);

        cap.setImage(image);

        return captcha;
    }
    Captcha captcha;

   public void initialize(URL url, ResourceBundle rb) {

     captcha = setCaptcha();

    }
   
    @FXML
    private void checkbox(ActionEvent event) {
        
         if (cbox.isSelected()){
             tfpassword.setVisible(true);
             tfpassword.setDisable(true);
            tfpassword.setPromptText(tfpassword.getText());
            tfpassword.setText("");
        }else {
            tfpassword.setText(tfpassword.getPromptText());
            tfpassword.setPromptText("");
            tfpassword.setDisable(false);
        }
    }
     
    @FXML
    private void login(ActionEvent event) throws IOException {
        String email = tfemail.getText();
    String password = tfpassword.getText();
    if (email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        // Email format is invalid
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Login fail");
        alert.setContentText("Invalid email format");
        alert.showAndWait();
        return;
    }
    if (password.isEmpty() || !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
        // Password is not complex enough
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Login fail");
        alert.setContentText("Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces");
        alert.showAndWait();
        return;
    }

        if (us.checklogin(tfemail.getText(), tfpassword.getText())) {
            UserService.userSession =new UserSession();
            UserService.userSession.setUserEmail(tfemail.getText());
            System.out.println(UserService.userSession.getUser());
        
            User u = us.findByEmail(tfemail.getText());
            switch (u.getRoles()) {
                case ADMIN:
                    try {
               
                    Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stageclose.close();
                  
                    Parent root = FXMLLoader.load(getClass().getResource("AfficherUser.fxml"));

                    Stage stage = new Stage();

                    Scene scene = new Scene(root);

                    stage.setTitle("Dashbord Admin");
                    stage.setScene(scene);
                    stage.show();
                    
                } 
                    catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
             
                case PERSONNEL:
                    System.out.println("bienveunue personnel");
                    try {
                    
                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("EDUSEX");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    case CLIENT:
                    System.out.println("bienveunue client");
                    try {
                    
                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("EDUSEX");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    break;
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login fail");
            alert.setContentText("Username or password invalid");
            alert.showAndWait();
        }
    }
   

    

    @FXML
    private void signup(ActionEvent event) {
    
    System.out.print("hi");
    try {
        // Charger le fichier FXML de la page d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterUser.fxml"));
        Parent root = loader.load();
        // CrÃ©er un nouveau stage pour la fenÃªtre d'inscription
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inscription");
        stage.show();
       
    } catch (IOException e) {
    }
     
            //navigation
           
}

    @FXML
     public void LinkToResetPassword()  throws IOException {
   try {
                Parent page1 = FXMLLoader.load(getClass().getResource("forgetpwd.fxml"));
               Stage mainStage = new Stage();
              Scene scene = new Scene(page1);
               mainStage.setScene(scene);
           mainStage.show();
          } catch (IOException ex) {
                 System.out.println("Erreur mdp ob !!!");
           }
    
      //  CRUUD2.primaryStage.setScene(new Scene(page));
       // CRUUD2.primaryStage.show();

   

     }
     @FXML
    private void youtube(ActionEvent event) {
         try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.youtube.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void facebook(ActionEvent event) {
         try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.facebook.com/profile.php?id=100092343753385"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void instagram(ActionEvent event) {
         try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.instagram.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   public void setPasswordField(String password) {
       if (cbox.isSelected()){
           tfpassword.setVisible(true);
           tfpassword.setText(password);
           tfpassword.setVisible(true);
       }
       else{
           tfpassword.setText(password);
       }
    
}
  
 
    @FXML
   private void reseting(ActionEvent event) {
       captcha = setCaptcha();
        code.setText("");
    }

}