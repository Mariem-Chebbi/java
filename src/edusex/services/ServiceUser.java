/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.User;
import java.sql.Connection;
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
public class ServiceUser {

    Connection maCon;
    Statement ste;

    public ServiceUser() {
        maCon = MyDB.getInstance().getcon();
    }

    public List<User> getPersonnels() {
        ArrayList<User> list = new ArrayList<>();
        String requete = "SELECT * from `user` WHERE `roles` like 'PERSONNEL';";
        try {
            ste = maCon.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                int id = rs.getInt(1);
                String nom = rs.getString(5);
                String prenom = rs.getString(6);

                User user = new User(id, nom, prenom);
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public User getUserById(int id) {
        User user = new User();
        String requete = "SELECT * from `user` WHERE `id` =" + id;
        try {
            ste = maCon.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                String nom = rs.getString(5);
                String prenom = rs.getString(6);

                user = new User(id, nom, prenom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCreneauHoraire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
