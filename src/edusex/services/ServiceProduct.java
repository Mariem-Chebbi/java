/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.Product;
import edusex.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ServiceProduct implements IntServiceProduct<Product>{
    
    Statement Ste;
    Connection con;

    public ServiceProduct() {
        
        con = MyDB.getInstance().getcon();
    }
    
    
    
    
    

    @Override
    public void addProduct(Product p) {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`Product` (`libelle`,`description`,`image`,`quantite`) VALUES (?,?,?,?);");
            pre.setString(1, p.getLibelle());
            pre.setString(2,p.getDescription());
            pre.setString(3, p.getImage());
            pre.setInt(4, p.getQuantite());
           
            pre.executeUpdate();
                    
            
            
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        }
    }
    
    
    
    
    
    

    @Override
    public void removeProduct(Product p) {
        try {
            Ste= con.createStatement();
       
        String req = "DELETE FROM `edusex`.`Product` WHERE `Product`.`id`='"+p.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }
    
    
    
    
    

    @Override
    public void updateProduct(Product p) {
        try {
            Ste= con.createStatement();
       
        String req = "UPDATE `edusex`.`Product`SET`libelle`='"+p.getLibelle()+"',`description`='"+p.getDescription()+"',`image`='"+p.getImage()+"',`quantite`='"+p.getQuantite()+"' WHERE `Product`.`id`='"+p.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }
    
    
    
    
    
    

    @Override
    public ArrayList<Product> showProduct() {
        ArrayList<Product> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Product`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                String image = res.getString(4);
                int quantite = res.getInt(5);
                
                Product p =new Product(id, libelle, description, quantite, image);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public ArrayList<Product> showProductTrier() {
        ArrayList<Product> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Product` ORDER BY `Product`.`libelle` ASC";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                String image = res.getString(4);
                int quantite = res.getInt(5);
                
                Product p =new Product(id, libelle, description, quantite, image);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public void addToFavorite(Product p,int userId)
    {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`favorite` (`user_id`,`product_id`) VALUES (?,?);");
            pre.setInt(1, userId);
            pre.setInt(2,p.getId());
            
           
            pre.executeUpdate();
                    
            
            
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        }
    
    }
    
    public void removeFromFavorite(Product p,int userId)
    {
        try {
            Ste= con.createStatement();
       
        String req = "DELETE FROM `edusex`.`favorite` WHERE `favorite`.`product_id`='"+p.getId()+"' AND `favorite`.`user_id`='"+userId+"';";
            System.out.println(req);
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    
    }
    
    public ArrayList<Product> showFavorit(int userId) {
        ArrayList<Product> pers = new ArrayList<>();
        ServiceReservation sr = new ServiceReservation();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`favorite` WHERE `favorite`.`user_id`='"+userId+"';";
            System.out.println(req);
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                Product P=sr.getClientById(res.getInt(3));
//                int id = res.getInt(1);
//                String libelle = res.getString(2);
//                String description = res.getString(3);
//                String image = res.getString(4);
//                int quantite = res.getInt(5);
                
//                Product p =new Product(id, libelle, description, quantite, image);
                pers.add(P);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public ArrayList<Product> showFavoritTrier(int userId) {
        ArrayList<Product> pers = new ArrayList<>();
        ServiceReservation sr = new ServiceReservation();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`favorite` WHERE `favorite`.`user_id`='"+userId+"' ORDER BY `Product`.`libelle` ASC;";
            System.out.println(req);
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                Product P=sr.getClientById(res.getInt(3));
//                int id = res.getInt(1);
//                String libelle = res.getString(2);
//                String description = res.getString(3);
//                String image = res.getString(4);
//                int quantite = res.getInt(5);
                
//                Product p =new Product(id, libelle, description, quantite, image);
                pers.add(P);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public boolean isFavorit(Product p,int userId) throws SQLException
    {
        String req = "SELECT * FROM `edusex`.`favorite` WHERE `favorite`.`product_id`=? AND `favorite`.`user_id`=?";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setInt(1, p.getId());
        pstmt.setInt(2, userId);
        ResultSet res = pstmt.executeQuery();
        return res.next();

            
    }
    
    public boolean ratingExist(Product p,int userId) throws SQLException
    {
        String req = "SELECT * FROM `edusex`.`raiting` WHERE `raiting`.`product_id`=? AND `raiting`.`user_id`=?";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setInt(1, p.getId());
        pstmt.setInt(2, userId);
        ResultSet res = pstmt.executeQuery();
        return res.next();

            
    }
    
    
     public void addRating(Product p,int userId,int rating)
    {
        
        try {
            if (ratingExist(p, userId))
            {
                System.out.println("test");
                Ste= con.createStatement();
        
                String req = "UPDATE `edusex`.`raiting`SET`raiting`='"+rating+"' WHERE `raiting`.`product_id`='"+p.getId()+"' AND `raiting`.`user_id`='"+userId+"';";
                Ste.executeUpdate(req);
            
            }
            else {
                PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`raiting` (`user_id`,`product_id`,`raiting`) VALUES (?,?,?);");
               
                pre.setInt(1, userId);
                pre.setInt(2,p.getId());
                pre.setInt(3,rating);


                pre.executeUpdate();
            }
            
            
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        }
    
    }
     
     public int getRatingUserProduct(Product p,int userId) throws SQLException
     {
         String req = "SELECT * FROM `edusex`.`raiting` WHERE `raiting`.`product_id`=? AND `raiting`.`user_id`=?";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setInt(1, p.getId());
        pstmt.setInt(2, userId);
        ResultSet res = pstmt.executeQuery();
        if(res.next())
        {
           return res.getInt(4); 
        }
        else return 0;
     }
     
     
     public float getRatingAVGProduct(Product p) throws SQLException
     {
         String req = "SELECT * FROM `edusex`.`raiting` WHERE `raiting`.`product_id`=?";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setInt(1, p.getId());
        ResultSet res = pstmt.executeQuery();
        if(res.next())
        {
            int somme=0;
            int length=1;
           while (res.next())
           {
               somme+=res.getInt(4);
               System.out.println("la somme est"+somme);
               length++;
           }
           return somme/length;
        }
        else return 0;
     }
    public ArrayList<Integer> showRating(Product p) {
        ArrayList<Integer> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        
            
        String req = "SELECT * FROM `edusex`.`raiting` WHERE `raiting`.`product_id` = '"+p.getId()+"' ;";
//        PreparedStatement pstmt = con.prepareStatement(req);
//        pstmt.setInt(1, p.getId());
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                
                int rating = res.getInt(4);
                
                
                pers.add(rating);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        System.out.println(pers);
        
        return pers;
    }
    
//    public ArrayList<Product> showProductStat(Product e) {
//        ArrayList<Product> pers = new ArrayList<>();
//        try {
//            Ste= con.createStatement();
//       
//        String req = "SELECT * FROM `edusex`.`reservation_product` WHERE `reservation_product`.`product_id` ='"+e.getId()+"';";
//            ResultSet res= Ste.executeQuery(req);
//            while(res.next()){
//                int id = res.getInt(1);
//                String libelle = res.getString(2);
//                String description = res.getString(3);
//                String image = res.getString(4);
//                int quantite = res.getInt(5);
//                
//                Product p =new Product(id, libelle, description, quantite, image);
//                pers.add(p);
//            }
//            
//            
//         } catch (SQLException ex) {
//             System.out.println("err"+ex.getMessage());
//        }
//        return pers;
//    }
}
