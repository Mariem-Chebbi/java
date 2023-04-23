/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.Certification;
import edusex.entities.Formation;
import edusex.entities.Inscription;
import edusex.entities.Reservation;
import edusex.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Alert;

/**
 *
 * @author ASUS
 */
public class ServiceInscription  implements IntServiceInscription<Inscription>{
    
    Statement Ste;
    Connection con;

    public ServiceInscription() {
        
        con = MyDB.getInstance().getcon();
    }
    @Override
    public void addInscription(Inscription i) {
        try {
            PreparedStatement recherche = con.prepareStatement("SELECT * FROM `edusex`.`Inscription` WHERE `Inscription`.`id_personnel_id`='"+i.getIdPersonnel()+"' AND `Inscription`.`id_formation_id`='"+i.getIdFormation()+"';");
            ResultSet rs = recherche.executeQuery();
            if (rs.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information ");
                alert.setHeaderText("Event inscrit");
                alert.setContentText("vous etes deja inscrit!");
                alert.showAndWait();
            
            } 
            else 
            {            
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`Inscription` (`id_personnel_id`,`id_formation_id`,`present`) VALUES (?,?,?);");  
            pre.setInt(1, i.getIdPersonnel());
            pre.setInt(2, i.getIdFormation());
            //pre.setInt(3,i.getPresent());
            pre.setInt(3,0);
            pre.executeUpdate(); } 
            
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        }
    }

    @Override
    public void removeInscription(Inscription i) {
        try {
            Ste= con.createStatement();
       
        String req = "DELETE FROM `edusex`.`Inscription` WHERE `Inscription`.`id`='"+i.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    @Override
    public void updateInscription(Inscription i) {
        String req="";
         try {
            Ste= con.createStatement();
       
        
        if (i.getPresent()=="Present"){
        req = "UPDATE `edusex`.`Inscription`SET`present`='"+"1"+"' WHERE `Inscription`.`id`='"+i.getId()+"';";

        }else{
        req = "UPDATE `edusex`.`Inscription`SET`present`='"+"0"+"' WHERE `Inscription`.`id`='"+i.getId()+"';";
        };


            Ste.executeUpdate(req);
            
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    @Override
    public ArrayList<Inscription> showInscription() {
              ArrayList<Inscription> pers = new ArrayList<>();
              Inscription i=null;
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Inscription`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                int idPersonnel = res.getInt(2);
                int idFormation = res.getInt(3);
                int Present = res.getInt(4);
                if (Present==1){
                    
                    i =new Inscription(id,  idPersonnel, idFormation, "Present");
                }else{
                 i =new Inscription(id,  idPersonnel, idFormation, "Absent");};
                
                pers.add(i);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    

 public Formation getFormationById(int FormationId) {
    Formation f = null;
    
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Formation` WHERE `Formation`.`id`='"+FormationId+"'; ";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                //int certificationId = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                String image = res.getString(5);
                java.sql.Date dateFormation = res.getDate(4);
                
                
                 f =new Formation(id, libelle, description,image, dateFormation);
                
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return f;
    }
    public String getUserById(int UserId) {
    Formation f = null;
    String nom="";
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`User` WHERE `User`.`id`='"+UserId+"'; ";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){

                //int certificationId = res.getInt(1);
                
                nom = res.getString(5);
                      
            }            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return nom;
    }

    
}
