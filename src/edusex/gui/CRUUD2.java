/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;
import edusex.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edusex.utils.MyDB;
import java.time.LocalDate;
import java.time.Month;


/**
 *
 * @author user
 */
public class CRUUD2 extends Application  {
       public static Stage primaryStage;
   //  public static Stage stage = null;
       java.sql.Date d =java.sql.Date.valueOf(LocalDate.of(2022, Month.MARCH, 17));
        UserService userService= new UserService() {}  ;

    @Override
    public void start(Stage primaryStage) throws Exception {
     
    // chargement de l'interface graphique depuis le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        // création de la scène
        Scene scene = new Scene(root);
        
        // configuration de la scène et affichage
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Login Application");
        primaryStage.show();
    }

    

    public static void main(String[] args) {
        launch(args);
      MyDB mo = MyDB.getInstance();

    }
}


