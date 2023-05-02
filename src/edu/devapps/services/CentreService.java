/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.services;

import com.mysql.jdbc.Connection;
import edu.devapps.entity.Centre;
import edu.devapps.entity.Reclamation;
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
public class CentreService {
   
    
      Connection cnx =MaConnexion.getInstance().getCnx();
   

  
    public void ajouterReclamation(Centre r) {
        String req ="INSERT INTO `centre`( `nom_social`, `aderesse`, `ville`,  `logo`, `tel1`, `tel2`, `description` ) VALUES"+ " ('"+r.getNom_social()+"','"+r.getAderesse()+"','"+r.getVille()+"','"+r.getLogo()+"','"+r.getTel1()+"','"+r.getTel2()+"','"+r.getDescription()+"')";

          try {
              Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("centre ajouté");
              
            
              
              
          } catch (SQLException ex) {
              
              System.out.println(ex.getMessage());
          }
              
          }
   
    public List<Centre> afficherCentre() {
        //var
        
       
        List<Centre> centres =new ArrayList<>();
        //requette
        String req ="SELECT * FROM centre";
          try {
              Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(req);
              while (rs.next()){
                  centres.add(new Centre(rs.getInt(1), rs.getString(2),rs.getString(3) ,rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
              }
          } catch (SQLException ex) {
              }
    
        return centres;

    
       
    }
    

    public void supprimercentre( Centre r  ) {
 String req="DELETE FROM `centre` WHERE id="+r.getId() ;
 
           try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("centre supprimer avec succes");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }  
             
          
    }
    
    

 
    public void modifiercentre( Centre r ) {
        
        String req=null;
        if(r.getId()!=0)
        {   req="UPDATE `centre` SET nom_social='"+r.getNom_social()+"',aderesse='"+r.getAderesse()+"',ville='"+r.getVille()+"',description='"+r.getDescription()+"' where id ="+r.getId();
       
            try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("centre modifier avec succes");
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
        String req ="SELECT COUNT(id) as nb FROM centre";
          try {
              Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(req);
              while (rs.next()){
                i=rs.getInt(1);              }
          } catch (SQLException ex) {
              }
    
        return i;

    
       
    }
    
    
     public List<Centre> rechercher(String s) {
        //var
        
       
        List<Centre> centres =new ArrayList<>();
        //requette
        String req ="SELECT * FROM centre  where nom_social like '%"+s+"%' OR aderesse like  '%"+s+"%' OR description like '%"+s+"%' OR  ville like '%"+s+"%'";
          try {
              Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(req);
              while (rs.next()){
                  centres.add(new Centre(rs.getInt(1), rs.getString(2),rs.getString(3) ,rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
              }
          } catch (SQLException ex) {
              }
    
        return centres;

    
       
    }
}


