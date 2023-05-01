package edusex.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edusex.entities.Role;
import edusex.entities.User;
import static edusex.entities.Role.ADMIN;
import static edusex.entities.Role.CLIENT;
//import static edusex.entitiesRole.PERSONNEL;
//import static edusex.entitiesCLIENT;

import java.awt.Image;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import edusex.services.UserService;

/**
 *
 * @author user
 */
public class CRUD {



    

       public static void main(String[] args) throws SQLException {
           java.sql.Date d =java.sql.Date.valueOf(LocalDate.of(2022, Month.MARCH, 17));
        UserService userService= new UserService() {}  ;

     User p1=new User(8,"ikr","zdzjed","mohamed@esprit.tn","d",111111,"mmlmm",d,"rtyg",CLIENT);
       
userService.Ajouter(p1);
 //   userService.modifier(p1);
//userService.supprimer(p2);



    
}

 
}