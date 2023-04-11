/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author Chebbi_Mariem
 */
public class RendezVous {
    private Long id;
    private LocalDate date_rdv;
    private int heure;
    private User personnel_id;
    private User client_id;

    public RendezVous() {
    }

    public RendezVous(LocalDate date_rdv, int heure, User personnel_id, User client_id) {
        this.date_rdv = date_rdv;
        this.heure = heure;
        this.personnel_id = personnel_id;
        this.client_id = client_id;
    }

    public RendezVous(Long id, LocalDate date_rdv, int heure, User personnel_id, User client_id) {
        this.id = id;
        this.date_rdv = date_rdv;
        this.heure = heure;
        this.personnel_id = personnel_id;
        this.client_id = client_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(LocalDate date_rdv) {
        this.date_rdv = date_rdv;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public User getPersonnel_id() {
        return personnel_id;
    }

    public void setPersonnel_id(User personnel_id) {
        this.personnel_id = personnel_id;
    }

    public User getClient_id() {
        return client_id;
    }

    public void setClient_id(User client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", date_rdv=" + date_rdv + ", heure=" + heure + ", personnel_id=" + personnel_id + ", client_id=" + client_id + '}';
    }
    
    
}
