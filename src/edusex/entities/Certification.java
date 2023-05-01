/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Certification {
    private int formationId,id;
    private String image;

    public Certification(int formationId, int id, String image) {
        this.formationId = formationId;
        this.id = id;
        this.image = image;
    }

    public Certification(int formationId, String image) {
        this.formationId = formationId;
        this.image = image;
    }

    public Certification() {
      
    }

    public int getFormationId() {
        return formationId;
    }

    public void setFormationId(int formationId) {
        this.formationId = formationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Certification{" + "formationId=" + formationId + ", id=" + id + ", image=" + image + '}';
    }
    
    
    
}
