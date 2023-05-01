/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.RendezVous;
import edusex.entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.SchedulerException;
import edusex.utils.MyDB;
import edusex.utils.RappelRendezVous;

/**
 *
 * @author Chebbi_Mariem
 */
public class ServiceRendezVous implements IService<RendezVous> {

    Connection maCon;
    Statement ste;
    User us;

    public ServiceRendezVous() {
        maCon = MyDB.getInstance().getcon();
        us = new User(2);
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
    public List<RendezVous> afficher(int id) {
        ArrayList<RendezVous> list = new ArrayList<>();
        String requete = "SELECT * FROM `rendez_vous` WHERE `client_id` = ? and `date_rdv` > ? ORDER BY `date_rdv` , `heure` ASC;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                Date date_rdv = rs.getDate(3);
                LocalDate localDate = date_rdv.toLocalDate();
                int psy_id = rs.getInt(2);
                int heure = rs.getInt(4);
                int client_id = rs.getInt(5);

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

    public List<RendezVous> historique(int id) {
        ArrayList<RendezVous> list = new ArrayList<>();
        String requete = "SELECT `id`,`date_rdv`,`personnel_id`,`heure`,`client_id` FROM `rendez_vous` WHERE `client_id` = ? and `date_rdv` < ? ORDER BY `date_rdv`, `heure` DESC;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                Date date_rdv = rs.getDate(2);
                LocalDate localDate = date_rdv.toLocalDate();
                int psy_id = rs.getInt(3);
                int heure = rs.getInt(4);
                int client_id = rs.getInt(5);

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

    @Override
    public void modifier(RendezVous o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void rappeler() throws SchedulerException {

        LocalDate rDate = null;

        for (RendezVous rdv : afficher(us.getId())) {
            LocalDate date = rdv.getDate_rdv();
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            if (date.isEqual(tomorrow)) {
                System.out.println("The date is equal to tomorrow");
                rDate = date;
            } else {
                System.out.println("The date is not equal to tomorrow");
            }
        }
        if (rDate != null) {
            RappelRendezVous.reminder(getReminderDate(rDate));
        }
    }

    public static java.util.Date getReminderDate(LocalDate date) {
        // set the time for the reminder to one day before the appointment
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE) + 1);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        //System.out.println(cal.getTime());
        return cal.getTime();
    }

    public List<RendezVous> triASC(int id) {
        ArrayList<RendezVous> list = new ArrayList<>();
        String requete = "SELECT * FROM `rendez_vous` WHERE `client_id` = ? and `date_rdv` > ? ORDER BY `date_rdv` ASC, `heure` ASC;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                Date date_rdv = rs.getDate(3);
                LocalDate localDate = date_rdv.toLocalDate();
                int psy_id = rs.getInt(2);
                int heure = rs.getInt(4);
                int client_id = rs.getInt(5);

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

    public List<RendezVous> triDESC(int id) {
        ArrayList<RendezVous> list = new ArrayList<>();
        String requete = "SELECT * FROM `rendez_vous` WHERE `client_id` = ? and `date_rdv` > ? ORDER BY `date_rdv` DESC, `heure` DESC;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                Date date_rdv = rs.getDate(3);
                LocalDate localDate = date_rdv.toLocalDate();
                int psy_id = rs.getInt(2);
                int heure = rs.getInt(4);
                int client_id = rs.getInt(5);

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

    public List<RendezVous> historiqueASC(int id) {
        ArrayList<RendezVous> list = new ArrayList<>();
        String requete = "SELECT * FROM `rendez_vous` WHERE `client_id` = ? and `date_rdv` < ? ORDER BY `date_rdv`  ASC;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                Date date_rdv = rs.getDate(3);
                LocalDate localDate = date_rdv.toLocalDate();
                int psy_id = rs.getInt(2);
                int heure = rs.getInt(4);
                int client_id = rs.getInt(5);

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

    public List<RendezVous> historiqueDESC(int id) {
        ArrayList<RendezVous> list = new ArrayList<>();
        String requete = "SELECT * FROM `rendez_vous` WHERE `client_id` = ? and `date_rdv` < ? ORDER BY `date_rdv` DESC;";
        try {
            PreparedStatement ps = maCon.prepareStatement(requete);
            ps.setLong(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id_ = rs.getLong(1);
                Date date_rdv = rs.getDate(3);
                LocalDate localDate = date_rdv.toLocalDate();
                int psy_id = rs.getInt(2);
                int heure = rs.getInt(4);
                int client_id = rs.getInt(5);

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

}
