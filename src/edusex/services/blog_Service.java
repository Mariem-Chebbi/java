/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.blog;
import edusex.entities.categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import edusex.utils.MyDB;
import java.sql.Date;

/**
 *
 * @author USER
 */
public class blog_Service implements Iblog{
    Statement Ste;
    Connection con;

    public blog_Service() {
        
        con = MyDB.getInstance().getcon();
    }
    
    
    

    @Override
    public void addBlog(blog r) {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`Blog` (`libelle`,`description`,`date`,`auteur`,`image`) VALUES (?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pre2 = con.prepareStatement("INSERT INTO `edusex`.`blog_categorie` (`blog_id`,`categorie_id`) VALUES (?,?);");
            pre.setString(1, r.getLibelle());
            pre.setString(2, r.getDescription());
            pre.setDate(3, (java.sql.Date) r.getDate());
            pre.setString(4,r.getAuteur());
            pre.setString(5,r.getImage());
            
           // pre.setInt(4, r.getUserId());
            pre.executeUpdate();
            
            ResultSet generatedKeys = pre.getGeneratedKeys();
            while(generatedKeys.next()){
            r.setId(generatedKeys.getInt(1));}
            pre2.setInt(1, r.getId());
            pre2.setInt(2, r.getIdCategorie());
            
            
           
            
            
            
            pre2.executeUpdate();
                    
            
            
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        }
    }

    
    
    
    
    
    @Override
    public void removeBlog(blog r) {
        try {
            Ste= con.createStatement();
       
        String req = "DELETE FROM `edusex`.`Blog` WHERE `Blog`.`id`='"+r.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    
    
    
    
    
    @Override
    public void updateBlog(blog p) {
        try {
            Ste= con.createStatement();
       
        //String req = "UPDATE `edusex01`.`blog` SET `libelle`='"+p.getLibelle()+"',`description`='"+p.getDescription()+"',`date`='"+p.getDate()+"',`auteur`='"+p.getAuteur()+"',`image`='"+p.getImage()+"' WHERE `Blog`.`id`='"+p.getId()+"';";
       // String req2 = "UPDATE `edusex01`.`blog_categorie`SET`categorie_id`='"+p.getIdCategorie()+"' WHERE `blog_categorie`.`blog_id`='"+p.getId()+"';";
            //Ste.executeUpdate(req);
           // Ste.executeUpdate(req2);
           String request = "UPDATE blog SET libelle=?,description=?,date=?,auteur=?,image=? WHERE blog.id=?";
           String request2 = "UPDATE blog_categorie SET categorie_id=? WHERE blog_categorie.blog_id=?";
    PreparedStatement pst = con.prepareStatement(request);
    PreparedStatement pst2 = con.prepareStatement(request2);
    pst.setString(1, p.getLibelle());
    pst.setString(2, p.getDescription());
    pst.setDate(3, p.getDate());
    pst.setString(4, p.getAuteur());
    pst.setString(5, p.getImage());
    pst.setInt(6, p.getId());
    
    pst2.setInt(1, p.getIdCategorie());
    pst2.setInt(2, p.getId());
    pst.executeUpdate();
    pst2.executeUpdate();
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    
    
    
    
    
    @Override
    public ArrayList<blog> showBlog() {
       ArrayList<blog> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`blog`JOIN`edusex`.`blog_categorie`ON`id`=`blog_id`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                Date date = res.getDate(4);
                String auteur = res.getString(5);
                String image = res.getString(6);
                int idCat = res.getInt(8);
                
                
                
                blog p =new blog(id, libelle, description, date, auteur, image,idCat);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public ArrayList<blog> triedate() {
       ArrayList<blog> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`blog`JOIN`edusex`.`blog_categorie`ON`id`=`blog_id` order by date";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                Date date = res.getDate(4);
                String auteur = res.getString(5);
                String image = res.getString(6);
                int idCat = res.getInt(8);
                
                
                
                blog p =new blog(id, libelle, description, date, auteur, image,idCat);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public ArrayList<blog> trielib() {
       ArrayList<blog> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`blog`JOIN`edusex`.`blog_categorie`ON`id`=`blog_id` order by libelle";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                Date date = res.getDate(4);
                String auteur = res.getString(5);
                String image = res.getString(6);
                int idCat = res.getInt(8);
                
                
                
                blog p =new blog(id, libelle, description, date, auteur, image,idCat);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public ArrayList<blog> getsingle(int idd) {
       ArrayList<blog> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`blog`JOIN`edusex`.`blog_categorie`ON`id`=`blog_id` WHERE id="+idd;
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                Date date = res.getDate(4);
                String auteur = res.getString(5);
                String image = res.getString(6);
                int idCat = res.getInt(8);
                
                
                
                blog p =new blog(id, libelle, description, date, auteur, image,idCat);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public categorie getClientById(int clientId) {
    categorie f = null;
    
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Categorie` WHERE `Categorie`.`id`='"+clientId+"'; ";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                //int certificationId = res.getInt(1);
                String libelle = res.getString(2);
                
                
                
                 f =new categorie(id, libelle);
                
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return f;
    }
    
    
    public ResultSet Selectionner() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `Blog`";
            PreparedStatement st = con.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    
    }
    
    public ArrayList<blog> chercher(String str) {
       ArrayList<blog> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`blog`JOIN`edusex`.`blog_categorie`ON`id`=`blog_id` WHERE `libelle` like '%"+str+"%'";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                Date date = res.getDate(4);
                String auteur = res.getString(5);
                String image = res.getString(6);
                int idCat = res.getInt(8);
                
                
                
                blog p =new blog(id, libelle, description, date, auteur, image,idCat);
                pers.add(p);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    
    public blog getBlogById(int clientId) {
    
        
    blog p=new blog();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`blog`JOIN`edusex`.`blog_categorie`ON`id`=`blog_id` WHERE `blog`.`id`='"+clientId+"';";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                Date date = res.getDate(4);
                String auteur = res.getString(5);
                String image = res.getString(6);
                int idCat = res.getInt(8);
                
                
                
                p =new blog(id, libelle, description, date, auteur, image,idCat);
                
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return p;
    }
}
