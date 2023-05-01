/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edusex.services.UserService;
import edusex.services.UserService;
import edusex.gui.ForgetpwdController;



/**
 * FXML Controller class
 *
 * @author user
 */
import static edusex.gui.ForgetpwdController.cc;
public class ChangerpwdController implements Initializable {
    public static String code;
public static String c;
    
    @FXML
    private TextField pass1;
    @FXML
    private TextField pass2;
    private Label err;
    @FXML
    private Button cha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //err.setText(cc);
    }    
 public void closeChangerpwd() {
        Stage Acceuil2Stage = (Stage) pass1.getScene().getWindow();
        Acceuil2Stage.hide();
    }
    
    
    @FXML
    private void changer(ActionEvent event) throws SQLException, IOException {
         String p1 = pass1.getText();
        String p2 = pass2.getText();
        
        UserService crud = new UserService();
        System.out.println(cc);
        

        
        if(p1.equals(p2)){
            System.out.println(cc);
            crud.updatemdp(cc, p1);
            //closeChangerpwd();
           // Notifications.create()
           //   .title("Welcome To SEIZE-IT")
             // .text("Password Was Changed Successfully")
          // //   .hideAfter(Duration.seconds(6))
            //  .position(Pos.TOP_RIGHT)
               //     .showInformation();
            //closeChangerpwd;
            
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }
            
        }else{
            err.setText("Password False !!");
        }
    
    
}

  
    

    
}
