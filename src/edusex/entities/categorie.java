/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.entities;

/**
 *
 * @author USER
 */
public class categorie {
    private int id ;
    private String libelle;

    public categorie() {
    }

    public categorie(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public categorie(String libelle) {
        this.libelle = libelle;
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

    @Override
    public String toString() {
        return libelle;
    }
    
    
}
