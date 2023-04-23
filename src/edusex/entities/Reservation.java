/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.entities;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Reservation {
    
    private int userId,id,quantite,idProduit;
    private String etat;
    private Date dateReservation;

    public Reservation() {
    }

    public Reservation( int quantite, Date dateReservation,String etat, int idProduit, int userId) {
        this.userId = userId;
        this.quantite = quantite;
        this.idProduit = idProduit;
        this.etat = etat;
        this.dateReservation = dateReservation;
    }

    public Reservation(int id, int quantite,  Date dateReservation, String etat, int idProduit,int userId) {
        this.userId = userId;
        this.id = id;
        this.quantite = quantite;
        this.idProduit = idProduit;
        this.etat = etat;
        this.dateReservation = dateReservation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public String toString() {
        return "Reservation{" + "userId=" + userId + ", id=" + id + ", quantite=" + quantite + ", idProduit=" + idProduit + ", etat=" + etat + ", dateReservation=" + dateReservation + '}';
    }
    
    
}
