/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.Controller;

import edu.devapps.entity.Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import yassine.MyListener;

/**
 * FXML Controller class
 *
 * @author ala20
 */
public class OneserviceviewController implements Initializable {

   @FXML
   private Label description;
   @FXML
   private Label icone;
 private Service rep;
    private MyListener myListener;
   @FXML
   private Label libelle;
   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }   

     public void setData(Service rep, MyListener myListener) {
        this.rep = rep;
        this.myListener = myListener;
        description.setText(rep.getDescription());
        icone.setText(""+rep.getIcone());
        libelle.setText(""+rep.getLibelle());
    }
   @FXML
   private void onclick(MouseEvent event) {
                            myListener.onClickListener(rep);

   }
   
}
