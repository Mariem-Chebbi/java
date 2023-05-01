/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.entities;

import java.sql.Date;



/**
 *
 * @author ASUS
 */
public class Formation {
    private int id,certificationId;
    private String libelle,description,image;
    private Date dateFormatiob;

    public Formation(int id, String libelle, String description, String image, Date dateFormatiob) {
        this.id = id;
        //this.certificationId = certificationId;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.dateFormatiob = dateFormatiob;
    }

    public Formation( String libelle, String description, String image, Date dateFormatiob) {
                this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.dateFormatiob = dateFormatiob;
    }

    

    public Formation() {
    }
    
    public Formation(String text, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(int certificationId) {
        this.certificationId = certificationId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateFormatiob() {
        return dateFormatiob;
    }

    public void setDateFormatiob(Date dateFormatiob) {
        this.dateFormatiob = dateFormatiob;
    }

    @Override
    public String toString() {
        return  libelle ;
    }
    
    

}
