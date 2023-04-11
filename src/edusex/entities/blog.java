/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.entities;

import java.sql.Date;



/**
 *
 * @author USER
 */
public class blog {
    private int id ;
    private String libelle;
    private String description;
    private Date date;
    private String auteur ;
    private String Image ;
    int idCategorie;

    public blog() {
    }

    public blog(int id, String libelle, String description, Date date, String auteur, String Image,int idCategorie) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.date = date;
        this.auteur = auteur;
        this.Image = Image;
        this.idCategorie = idCategorie;
    }

    public blog(String libelle, String description, Date date, String auteur, String Image,int idCategorie) {
        this.libelle = libelle;
        this.description = description;
        this.date = date;
        this.auteur = auteur;
        this.Image = Image;
        this.idCategorie = idCategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "blog{" + "id=" + id + ", libelle=" + libelle + ", description=" + description + ", date=" + date + ", auteur=" + auteur + ", Image=" + Image + ", idCategorie=" + idCategorie + '}';
    }

    


    
    
    
}
