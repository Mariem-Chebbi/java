/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.entities;

/**
 *
 * @author LENOVO
 */
public class Product {
    private int id,quantite;
    private String libelle,description,image;

    public Product(String libelle, String description, int quantite, String image) {
        this.quantite = quantite;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public Product() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Product(String text, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", quantite=" + quantite + ", libelle=" + libelle + ", description=" + description + ", image=" + image + '}';
    }

    public Product(int id, String libelle, String description, int quantite, String image) {
        this.id = id;
        this.quantite = quantite;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }
    
}