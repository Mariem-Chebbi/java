/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.Product;
import edusex.entities.Reservation;
import edusex.utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author LENOVO
 */
public class ServiceReservation implements IntServiceReservation<Reservation>{
    
    Statement Ste;
    Connection con;

    public ServiceReservation() {
        
        con = MyDB.getInstance().getcon();
    }
    
    
    

    @Override
    public void addReservation(Reservation r) {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`Reservation` (`quantite`,`date_reservation`,`etat`) VALUES (?,?,?);",Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pre2 = con.prepareStatement("INSERT INTO `edusex`.`Reservation_product` (`reservation_id`,`product_id`) VALUES (?,?);");
            pre.setInt(1, r.getQuantite());
            pre.setDate(2, (java.sql.Date) r.getDateReservation());
            pre.setString(3,r.getEtat());
           // pre.setInt(4, r.getUserId());
            pre.executeUpdate();
            
            ResultSet generatedKeys = pre.getGeneratedKeys();
            while(generatedKeys.next()){
            r.setId(generatedKeys.getInt(1));}
            pre2.setInt(1, r.getId());
            pre2.setInt(2, r.getIdProduit());
            
            
           
            
            
            
            pre2.executeUpdate();
                    
            
            
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        }
    }

    
    
    
    
    
    @Override
    public void removeReservation(Reservation r) {
        try {
            Ste= con.createStatement();
       
        String req = "DELETE FROM `edusex`.`Reservation` WHERE `Reservation`.`id`='"+r.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    
    
    
    
    
    @Override
    public void updateReservation(Reservation p) {
        try {
            Ste= con.createStatement();
       
        String req = "UPDATE `edusex`.`Reservation`SET`quantite`='"+p.getQuantite()+"',`date_reservation`='"+p.getDateReservation()+"',`etat`='"+p.getEtat()+"' WHERE `Reservation`.`id`='"+p.getId()+"';";
        String req2 = "UPDATE `edusex`.`Reservation_product`SET`product_id`='"+p.getIdProduit()+"' WHERE `Reservation_product`.`reservation_id`='"+p.getId()+"';";
            Ste.executeUpdate(req);
            Ste.executeUpdate(req2);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    
    
    
    
    
    @Override
    public ArrayList<Reservation> showReservation() {
       ArrayList<Reservation> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Reservation`JOIN`edusex`.`Reservation_Product`ON`id`=`Reservation_id`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                int quantite = res.getInt(2);
                Date date = res.getDate(3);
                String etat = res.getString(4);
                int idProd = res.getInt(7);
                int idUser = res.getInt(5);
                
                
                Reservation p =new Reservation(id, quantite, date, etat, idProd, idUser);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    
    public ArrayList<Reservation> showReservationUser(int userId) {
       ArrayList<Reservation> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Reservation`JOIN`edusex`.`Reservation_Product`ON`id`=`Reservation_id` WHERE `user_id`='"+userId+"'";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                int quantite = res.getInt(2);
                Date date = res.getDate(3);
                String etat = res.getString(4);
                int idProd = res.getInt(7);
                int idUser = res.getInt(5);
                
                
                Reservation p =new Reservation(id, quantite, date, etat, idProd, idUser);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public Product getClientById(int clientId) {
    Product f = null;
    
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Product` WHERE `Product`.`id`='"+clientId+"'; ";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                //int certificationId = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                String image = res.getString(4);
                int quantite = res.getInt(5);
                
                
                 f =new Product(id, libelle, description, quantite,image);
                
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return f;
    }
    
    public String getUserById(int usertId) {
    String nom = "";
    
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`User` WHERE `User`.`id`='"+usertId+"'; ";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                //int id = res.getInt(1);
                //int certificationId = res.getInt(1);
                 nom = res.getString(5);
                //String description = res.getString(3);
                //String image = res.getString(4);
                //int quantite = res.getInt(5);
                
                
                // f =new Product(id, libelle, description, quantite,image);
                
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return nom;
    }
    
}
