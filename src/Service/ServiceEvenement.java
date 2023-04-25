/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Evenement;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
/**
 *
 * @author sanabenfadhel
 */
public class ServiceEvenement implements IService_evenement<Evenement>{
 
    Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;
    private Evenement p;

    public ServiceEvenement() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public boolean  ajouter(Evenement c) throws SQLException
    {
                boolean verif=true;

    try {   String req = "INSERT INTO `Evenement`(`id`, `nom_event`, `lieu_event`, `description`, `date_debut` , `date_fin`) VALUES ( ?,?,?,?,?,?);";

     PreparedStatement pre=con.prepareStatement(req);
            pre.setInt(1, c.getId());

    
        pre.setString(2, c.getNom_event());
        pre.setString(3, c.getLieu_event());
                pre.setString(4, c.getDescription());

         pre.setDate(5,(c.getDate_debut()));
          pre.setDate(6,(c.getDate_fin()));
        
       
         int rowsInserted = pre.executeUpdate();
     if (rowsInserted > 0) {
         
                           }
            
     }catch(SQLException e){
            System.out.println(e);
            verif=false;
        }
    return verif;
    }
    

    @Override
    public void update(Evenement c) throws SQLException {
        String req="UPDATE `Evenement` SET `nom_event`=?,`lieu_event`=?,`description`=?,`date_debut`=?,`date_fin`=?  ";
        
        PreparedStatement pre=con.prepareStatement(req);

    
        pre.setString(1, c.getNom_event());
        pre.setString(2, c.getLieu_event());
        pre.setString(3, c.getDescription());
         pre.setDate(4,(c.getDate_debut()));
         
          pre.setDate(5,(c.getDate_fin()));
        
        
        
        pre.executeUpdate();
        System.out.println("evenement modifié !");

    }

    @Override
public boolean supprime(int id) throws SQLException {

        String req = "DELETE FROM `Evenement` WHERE id = " + id + ";";

        if ((ste.executeUpdate(req)) == 1) {
            return true;
        }

        return false;

    }
    public List<Evenement> readAll() throws SQLException {
        ArrayList<Evenement> listper = new ArrayList<>();

        String req = "select * from Evenement";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id = res.getInt(1);
          
            String nom_event = res.getString("nom_event");
            String lieu_event = res.getString("lieu_event");
            String description = res.getString("description");
            Date date_debut = res.getDate("date_debut");
            Date date_fin = res.getDate("date_fin");
          
 
            Evenement c = new Evenement(id, nom_event, lieu_event, description ,date_debut , date_fin );
            // System.out.println(p);
            listper.add(c);
        }
        return listper;
    }

   /* @Override
    public List<Evenement> findById(int id) throws SQLException {
        ArrayList<Evenement> listper = new ArrayList<>();
        String req = "select * FROM Evenement where id = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
       int id = res.getInt("id");
            int id_utilisateur = res.getInt(2);
            String depart = res.getString("depart");
            String destination = res.getString(4);
            Date date_covoiturage = res.getDate(5);
            int Prix = res.getInt(6);
            int nbrplace = res.getInt(7);
            String nom = res.getString(8);
            String telephone = res.getString(9);
       Evenement  d = new Evenement(id, nom_event, lieu_event, description ,date_debut , date_fin);
  listper.add(d);
    }
        
        return listper;
    }*/
    
    
      public List<Evenement> sortbydate() throws SQLException {
        ArrayList<Evenement> listper = new ArrayList<>();

        String req = "select * from Evenement ORDER by date_debut";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
           int id = res.getInt(1);
            
            String nom_event = res.getString("nom_event");
                String lieu_event = res.getString("lieu_event");
                    String description = res.getString("description");
            
            Date date_debut = res.getDate("date_debut");
            Date date_fin = res.getDate("date_fin");
            
          
            
            Evenement p = new Evenement(id, nom_event, lieu_event, description ,date_debut , date_fin);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    @Override
    public List<Evenement> findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}