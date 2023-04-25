/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entity.Sponsor;
import Entity.Evenement;
import java.time.Clock;

/**
 *
 * @author abbes
 */
public class ServiceSponsor implements Iservice_sponsor<Sponsor> {
     Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public ServiceSponsor() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
public List<Sponsor> sortbynom_sponsor() throws SQLException {
        ArrayList<Sponsor> listper = new ArrayList<>();

        String req = "select * from Sponser ORDER by nom_sponser";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
           int id = res.getInt(1);
            
            String nom_sponsor = res.getString("nom_sponser");
                String type = res.getString("type");
                    int id_event = res.getInt(2);
          
            
            Sponsor p = new Sponsor(id, id_event,nom_sponsor, type );
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    @Override
    public void ajouter_sponsor(Sponsor d) throws SQLException {
        String req = "INSERT INTO `sponser` ( `id`, `evenement_id`, `nom_sponser`, `type`) VALUES ( ?, ?, ?, ?);";
 

        PreparedStatement pre = con.prepareStatement(req);
    

        pre.setInt(1, d.getId());
        pre.setInt(2, d.getId_event());
                
pre.setString(3, d.getNom_sponsor());
pre.setString(4, d.getType());

   
       

        pre.executeUpdate();
       
    }

    @Override
    public void update_sponsor(Sponsor d) throws SQLException {
        String req = " UPDATE sponser SET   evenement_id  = ?,nom_sponser = ?,type = ?  ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, d.getId_event());
      pre.setString(2, d.getNom_sponsor());
pre.setString(3, d.getType());
     
        pre.executeUpdate();
        System.out.println("reservation modifié !");
    }

    public boolean supprime_sponsor(int id) throws SQLException {
        String req = "DELETE FROM `sponser` WHERE id = " + id + ";";
   
        PreparedStatement pre = con.prepareStatement(req);

        if ((ste.executeUpdate(req)) == 1) {
                    pre.executeUpdate(); 

            return true;
                

        }

        return false;
    }

    @Override
    public List<Sponsor> readAll_sponsor() throws SQLException {
        ArrayList<Sponsor> listper = new ArrayList<>();

        String req = "select * from sponser";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id = res.getInt("Id");
            int id_event = res.getInt("evenement_id");
           
           String nom_sponsor = res.getString("nom_sponser"); 
           String type = res.getString("type"); 
  
           
            Sponsor p = new Sponsor(id, id_event, nom_sponsor, type );
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    @Override
    public List<Sponsor>  findById_sponsor(int id) throws SQLException {
                ArrayList<Sponsor> listper = new ArrayList<>();

        String req = "select * FROM `reservation_covoiturage` WHERE id= " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int id_event = res.getInt("id_event");
           
           String nom_sponsor = res.getString("nom_sponsor"); 
           String type = res.getString("type"); 
            
         Sponsor    d = new Sponsor(id_event, nom_sponsor, type);
     listper.add(d);
        }

        return listper;
    }
/*
    @Override
    public List<reservation_covoiturage> find_reservation_cov_of_user(int id) throws SQLException {
        ArrayList<reservation_covoiturage> listper = new ArrayList<>();

        String req = "select * from reservation_covoiturage where id_utilisateur =" + id + ";";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
          int id_reserv_cov = res.getInt(1);
            int id_cov = res.getInt(2);
            int id_utilisateur = res.getInt(3);
            int prix_covoiturage = res.getInt(4);
            String depart = res.getString(5); 
           String destination = res.getString(6); 
               String nom = res.getString(7); 
           String telephone = res.getString(8); 
            reservation_covoiturage p = new reservation_covoiturage(id_reserv_cov, id_cov, id_utilisateur, prix_covoiturage, depart , destination, nom , telephone);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
public List<reservation_covoiturage> find_reservation_cov_of_user_rech(int id , int ide ) throws SQLException {
        ArrayList<reservation_covoiturage> listper = new ArrayList<>();

       // String req = "select * from reservation_covoiturage where id_utilisateur =" + id + "AND id_cov =" + ide + ";";
String req = "SELECT * FROM reservation_covoiturage WHERE id_cov = ? AND id_utilisateur = ?";
PreparedStatement stmt = con.prepareStatement(req);
stmt.setInt(1, id);
stmt.setInt(2, ide);
ResultSet res = stmt.executeQuery();
      

        while (res.next()) {
          int id_reserv_cov = res.getInt(1);
            int id_cov = res.getInt(2);
            int id_utilisateur = res.getInt(3);
            int prix_covoiturage = res.getInt(4);
            String depart = res.getString(5); 
           String destination = res.getString(6); 
               String nom = res.getString(7); 
           String telephone = res.getString(8); 
            reservation_covoiturage p = new reservation_covoiturage(id_reserv_cov, id_cov, id_utilisateur, prix_covoiturage, depart , destination, nom , telephone);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
       
    }
   
   */

    @Override
    public boolean supprime_sponsor(int id, int idc) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sponsor> find_sponsor_of_event(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
