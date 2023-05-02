/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.services;

import com.mysql.jdbc.Connection;
import edu.devapps.entity.Reclamation;
import edu.devapps.entity.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utilites.MaConnexion;

/**
 *
 * @author ala20
 */
public class ServiceService {
   
    
      Connection cnx =MaConnexion.getInstance().getCnx();
   

  
    public void ajouterservice(Service r) {
        String req ="INSERT INTO `services`( `libelle`, `description`, `icone` ) VALUES"+ " ('"+r.getLibelle()+"','"+r.getDescription()+"','"+r.getIcone()+"')";

          try {
              Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("service ajouté");
              
            
              
              
          } catch (SQLException ex) {
              
              System.out.println(ex.getMessage());
          }
              
          }
   
    public List<Service> afficherservice() {
        //var
        
       
        List<Service> services =new ArrayList<>();
        //requette
        String req ="SELECT * FROM services";
          try {
              Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(req);
              while (rs.next()){
                  services.add(new Service(rs.getInt(1), rs.getString(2),rs.getString(3) ,rs.getString(4)));
              }
          } catch (SQLException ex) {
              }
    
        return services;

    
       
    }
    

    public void supprimerservice( Service r  ) {
 String req="DELETE FROM `services` WHERE id="+r.getId();
 
           try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("service supprimer avec succes");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }  
             
          
    }
    
    

 
    public void modifierservice( Service r ) {
        
        String req=null;
        if(r.getId()!=0)
        {   req="UPDATE `services` SET libelle='"+r.getLibelle()+"',description='"+r.getDescription()+"',icone='"+r.getIcone()+"' where id ="+r.getId();
       
            try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("service modifier avec succes");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
            
        }
       
    }
    
    
    
    
   

    


    
      public int nbrec() {
        //var
        
       
        List<Reclamation> films =new ArrayList<>();
        //requette
        int i=0;
        String req ="SELECT COUNT(id) as nb FROM services";
          try {
              Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(req);
              while (rs.next()){
                i=rs.getInt(1);              }
          } catch (SQLException ex) {
              }
    
        return i;

    
       
    }
    
    
     public List<Service> rechercher(String s) {
        //var
        
       
        List<Service> services =new ArrayList<>();
        //requette
        String req ="SELECT * FROM services  where libelle like '%"+s+"%' OR description like  '%"+s+"%' OR icone like '%"+s+"%'";
          try {
              Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(req);
              while (rs.next()){
                  services.add(new Service(rs.getInt(1), rs.getString(2),rs.getString(3) ,rs.getString(4)));
              }
          } catch (SQLException ex) {
              }
    
        return services;

    
       
    }
}
