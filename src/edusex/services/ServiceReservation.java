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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`Reservation` (`quantite`,`date_reservation`,`etat`,`user_id`) VALUES (?,?,?,?);");
            PreparedStatement pre2 = con.prepareStatement("INSERT INTO `edusex`.`Reservation_product` (`reservation_id`,`product_id`) VALUES (?,?);");
            pre.setInt(1, r.getQuantite());
            pre.setDate(2, (java.sql.Date) r.getDateReservation());
            pre.setString(3,r.getEtat());
            pre.setInt(5, r.getUserId());
            
            
            pre2.setInt(0, r.getId());
            pre2.setInt(1, r.getIdProduit());
            
            
           
            pre.executeUpdate();
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
    public void updateReservation(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
