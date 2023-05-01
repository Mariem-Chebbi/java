/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.CreneauHoraire;
import edusex.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edusex.utils.MyDB;

/**
 *
 * @author Chebbi_Mariem
 */
public class ServiceCreneauHoraire implements IService<CreneauHoraire> {

    Connection maCon;
    Statement ste;

    public ServiceCreneauHoraire() {
        maCon = MyDB.getInstance().getcon();
    }

    @Override
    public void ajouter(CreneauHoraire c) {
        String requete = "INSERT INTO `creneau_horaire` (`jour`,`heure_debut`,`heure_fin`,`etat`,`psy_id`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setString(1, c.getJour());
            ps.setInt(2, c.getHeure_debut());
            ps.setInt(3, c.getHeure_fin());
            ps.setBoolean(4, c.isEtat());
            ps.setLong(5, c.getPsy().getId());
            int n = ps.executeUpdate();
            if (n >= 1) {
                System.out.println("le creneau a été ajouté avec succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(CreneauHoraire c) {
        String requete = "UPDATE `creneau_horaire` SET `heure_debut` = ?, `heure_fin` = ?, `etat` = ?"
                + " WHERE `creneau_horaire`.`jour` = ? ;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setInt(1, c.getHeure_debut());
            ps.setInt(2, c.getHeure_fin());
            ps.setBoolean(3, c.isEtat());
            ps.setString(4, c.getJour());
            int n = ps.executeUpdate();
            if (n >= 1) {
                System.out.println("le creneau a été modifié avec succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<CreneauHoraire> afficher(int id) {
        ArrayList<CreneauHoraire> list = new ArrayList<>();
        String requete = "SELECT * from `creneau_horaire` WHERE `psy_id` = " + id;
        try {
            ste = maCon.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                int heure_debut = rs.getInt(2);
                String jour = rs.getString(3);
                Boolean etat = rs.getBoolean(4);
                int heure_fin = rs.getInt(5);
                int psy_id = rs.getInt(6);

                User user = new User(psy_id);
                CreneauHoraire cr = new CreneauHoraire(id_, jour, heure_debut, heure_fin, etat, user);
                list.add(cr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public CreneauHoraire getCreneauHoraire (String jour, int id){
        CreneauHoraire cr = new CreneauHoraire();
        String requete = "SELECT * from `creneau_horaire` WHERE `psy_id` = ? AND `jour` = ?";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1,id);
            ps.setString(2,jour);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                int heure_debut = rs.getInt(2);
                String jourr = rs.getString(3);
                Boolean etat = rs.getBoolean(4);
                int heure_fin = rs.getInt(5);
                int psy_id = rs.getInt(6);
                
                User user = new User(psy_id);
                cr = new CreneauHoraire(id_, jour, heure_debut, heure_fin, etat, user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cr;
    }
    
    @Override
    public void supprimer(CreneauHoraire o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
