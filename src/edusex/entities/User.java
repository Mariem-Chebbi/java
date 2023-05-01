/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.entities;

import edusex.entities.Role;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class User {

    private int id;
    private String email;

    private String password;
    private String nom;
    private String prenom;
    private int num_tel;
    private String ville;
    private Date date_naissance;
    private String image;
    private Role roles ;

    public User(int id, String email, String password, String nom, String prenom, int num_tel, String ville, Date date_naissance, String image, Role roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.ville = ville;
        this.date_naissance = date_naissance;
        this.image = image;
        this.roles = roles;
    }

    public User(String email, String password, String nom, String prenom, int num_tel, String ville, Date date_naissance, String image, Role roles) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.ville = ville;
        this.date_naissance = date_naissance;
        this.image = image;
        this.roles = roles;
    }

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    

//    public User(String string, String string0, String string1, String string2, String string3, String string4, String string5, Role valueOf) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  //  }

   // public User(String email, String password) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }

    public User(String string, String string0, String string1, String mail, String pass, int aInt, Date date, String string2, Role valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(int aInt, String string, String string0, String string1, String string2, int aInt0, String string3, Date date, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(String string, String string0, String string1, String string2, int aInt, String string3, Date date, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(int id, String email, String password, String nom, String prenom, String num_tel, String ville, LocalDate date_naissance, String image, String roles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(int id, String email, String password, String nom, String prenom, int num_tel, String ville, LocalDate date_naissance, String image, String roles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(String email, String password, String nom, String prenom, int num_tel, String ville, LocalDate date_naissance, String image, String roles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(String string, String mail, Role valueOf, String pass, String string0, String string1, int aInt, String string2, Date date, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance (Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return nom + " " + prenom ;
    }

    public void add(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}