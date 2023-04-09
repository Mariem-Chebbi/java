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
    
}
