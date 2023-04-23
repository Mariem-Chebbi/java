/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.entities;

/**
 *
 * @author ASUS
 */
public class Inscription {
    int id, idPersonnel,idFormation;
    String Present;

    public Inscription(int id, int idPersonnel, int idFormation, String Present) {
        this.id = id;
        this.idPersonnel = idPersonnel;
        this.idFormation = idFormation;
        this.Present = Present;
    }

    public Inscription(int idPersonnel, int idFormation, String Present) {
        this.idPersonnel = idPersonnel;
        this.idFormation = idFormation;
        this.Present = Present;
    }

    public Inscription() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public String getPresent() {
        return Present;
    }

    public void setPresent(String Present) {
        this.Present = Present;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", idPersonnel=" + idPersonnel + ", idFormation=" + idFormation + ", Present=" + Present + '}';
    }
 

    
}
