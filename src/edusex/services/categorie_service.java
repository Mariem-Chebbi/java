/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edusex.utils.MyDB;

/**
 *
 * @author USER
 */
public class categorie_service implements Icategorie{
    Connection cnx;

    public categorie_service() {
        cnx =MyDB.getInstance().getcon();
    }

    @Override
    public boolean ajoutercategorie(categorie c) throws SQLException {
    String request = "INSERT INTO categorie (libelle) VALUES (?)";
    PreparedStatement pst = cnx.prepareStatement(request);
    pst.setString(1, c.getLibelle());
    pst.executeUpdate();
    System.out.println("Categorie added");
    return true;
    }

    @Override
    public boolean updatecategorie(categorie c) throws SQLException {
    String request = "UPDATE categorie SET libelle=? WHERE id=?";
    PreparedStatement pst = cnx.prepareStatement(request);
    pst.setString(1, c.getLibelle());
    pst.setInt(2, c.getId());
    pst.executeUpdate();
    System.out.println("Categorie updated");
    return true;    }

    @Override
    public boolean suprimecategorie(categorie c) throws SQLException {

    String request = "DELETE FROM categorie WHERE id=?";
    PreparedStatement pst = cnx.prepareStatement(request);
    pst.setInt(1, c.getId());
    pst.executeUpdate();
    System.out.println("Categorie deleted");
    return true;
    }

    @Override
    public List<categorie> getAll() throws SQLException {
    List<categorie> categories = new ArrayList<>();
    String request = "SELECT * FROM categorie";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(request);
    while (rs.next()) {
        categorie c = new categorie();
        c.setId(rs.getInt("id"));
        c.setLibelle(rs.getString("libelle"));
        categories.add(c);
    }
    return categories;
    }
    
}
