/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.Controller;

import edu.devapps.entity.Centre;
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
public class OnecentreviewController implements Initializable {

   @FXML
   private Label nom_social;
   @FXML
   private Label aderesse;
   @FXML
   private Label tel1;
   @FXML
   private Label description;
   @FXML
   private Label ville;
 private Centre centre;
    private MyListener myListener;
   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }   

   @FXML
   private void onclick(MouseEvent event) {
                    myListener.onClickListener(centre);

   }
   
   
      public void setData(Centre rep, MyListener myListener) {
        this.centre = rep;
        this.myListener = myListener;
        nom_social.setText(rep.getNom_social());
        aderesse.setText(""+rep.getAderesse());
        tel1.setText(""+rep.getTel1());
          description.setText(""+rep.getDescription());
               ville.setText(""+rep.getVille());
    }
   
}
