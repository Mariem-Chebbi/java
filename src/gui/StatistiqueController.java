/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Chebbi_Mariem
 */
public class StatistiqueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection maCon;
    PreparedStatement ste;
    User us;
    @FXML
    private AnchorPane root;

    @FXML
    private BarChart<?, ?> barChart;

    public StatistiqueController() {
        maCon = MyDB.getInstance().getcon();
        us = new User(1L);
    }

    @FXML
    private void goToList(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("ListCreneauHoraire.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }

    @FXML
    private void goToAjout(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("AjoutCreneauHoraire.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();

    }

    @FXML
    private void goToAjoutRDV(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("AjoutRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    @FXML
    private void goToListRDV(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("ListRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    @FXML
    private void goToHistorique(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("HistoriqueRendezVous.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    @FXML
    private void goToStatistique(ActionEvent event) throws IOException {
        Parent settingsParent = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        Scene settingsScene = new Scene(settingsParent);
        Stage appStage = (Stage) root.getScene().getWindow();
        appStage.setScene(settingsScene);
        System.out.println("succes");
        appStage.show();
    }

    public void chart() {
        String requete = "SELECT YEAR(date_rdv), QUARTER(date_rdv),DATE(date_rdv) AS start_date, \n"
                + "       DATE_ADD(DATE(date_rdv), INTERVAL 3 MONTH) AS end_date, COUNT(*) \n"
                + "FROM `rendez_vous` \n"
                + "where `personnel_id` = ? \n"
                + "GROUP BY YEAR(date_rdv), QUARTER(date_rdv) ;";

        try {
            XYChart.Series chartData = new XYChart.Series();
            ste = maCon.prepareStatement(requete);
            ste.setLong(1, us.getId());
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                chartData.getData().add(new XYChart.Data(rs.getString(3) + "-" + rs.getString(4), rs.getInt(5)));
            }
         
            barChart.getData().add(chartData);

        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chart();
    }

}
