/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.gui;

import edusex.entities.Product;
import edusex.services.ServiceProduct;
import edusex.services.ServiceReservation;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Créer une liste de données pour le graphique
        ServiceProduct sp=new ServiceProduct();
        ServiceReservation sr = new ServiceReservation();
        ArrayList<Product> all=sp.showProduct();
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
    
);
        for(int i=0;i<all.size();i++)
        {
            //float stat=(sp.showProductStat(all.get(i)).size()*100)/sr.showReservation().size();
            pieChartData = FXCollections.observableArrayList(
   // new PieChart.Data(all.get(i).getLibelle(), stat)
    
);
        }

pieChart.setData(pieChartData);
// Créer le graphique
//PieChart pieChart = new PieChart(pieChartData);

// Ajouter le graphique à un conteneur approprié
//VBox container = new VBox(pieChart);

    }    
    
}
