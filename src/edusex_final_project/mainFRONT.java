/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex_final_project;

import edusex.services.ServiceRendezVous;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.quartz.SchedulerException;

/**
 *
 * @author LENOVO
 */
public class mainFRONT extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../edusex/gui/AjoutCreneauHoraire.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("Personnes");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SchedulerException {
        ServiceRendezVous serviceRendezVous = new ServiceRendezVous();
        serviceRendezVous.rappeler();
        launch(args);
    }
    
}
