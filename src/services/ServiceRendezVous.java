/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CreneauHoraire;
import entities.RendezVous;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.EmailSender;
import utils.MyDB;

/**
 *
 * @author Chebbi_Mariem
 */
public class ServiceRendezVous implements IService<RendezVous> {

    Connection maCon;
    Statement ste;

    public ServiceRendezVous() {
        maCon = MyDB.getInstance().getcon();
    }

    @Override
    public void ajouter(RendezVous rdv) {
        String requete = "INSERT INTO `rendez_vous` (`date_rdv`,`personnel_id`,`heure`,`client_id`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setString(1, rdv.getDate_rdv().toString());
            ps.setLong(2, rdv.getPersonnel_id().getId());
            ps.setInt(3, rdv.getHeure());
            ps.setLong(4, rdv.getClient_id().getId());
            int n = ps.executeUpdate();
            if (n >= 1) {
                System.out.println("le rendez-vous a été ajouté avec succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(RendezVous rdv) {
        String requete = "delete from `rendez_vous` where `rendez_vous`.`id`=? ";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, rdv.getId());
            int n = ps.executeUpdate();
            if (n >= 1) {
                //EmailSender es = new EmailSender();
                //es.sendEmail(Date.valueOf(LocalDate.now()));
                System.out.println("suppression réussie");
            }
        } catch (SQLException ex) {
            System.out.println("problème de requête de suppression" + ex.getMessage());
        }
    }

    @Override
    public List<RendezVous> afficher(Long id) {
        ArrayList<RendezVous> list = new ArrayList<>();
        String requete = "SELECT * FROM `rendez_vous` WHERE `client_id` = ? and `date_rdv` > ? ORDER BY `date_rdv` ASC;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                Date date_rdv = rs.getDate(2);
                LocalDate localDate = date_rdv.toLocalDate();
                Long psy_id = rs.getLong(3);
                int heure = rs.getInt(4);
                Long client_id = rs.getLong(5);

                ServiceUser u = new ServiceUser();
                User psy = u.getUserById(psy_id);
                User client = new User(client_id);

                RendezVous rdv = new RendezVous(id_, localDate, heure, psy, client);
                list.add(rdv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<RendezVous> historique(Long id) {
        ArrayList<RendezVous> list = new ArrayList<>();
        String requete = "SELECT * FROM `rendez_vous` WHERE `client_id` = ? and `date_rdv` < ? ORDER BY `date_rdv` DESC;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                Date date_rdv = rs.getDate(2);
                LocalDate localDate = date_rdv.toLocalDate();
                Long psy_id = rs.getLong(3);
                int heure = rs.getInt(4);
                Long client_id = rs.getLong(5);

                ServiceUser u = new ServiceUser();
                User psy = u.getUserById(psy_id);
                User client = new User(client_id);

                RendezVous rdv = new RendezVous(id, localDate, heure, psy, client);
                list.add(rdv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void modifier(RendezVous o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
