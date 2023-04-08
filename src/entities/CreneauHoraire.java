/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Chebbi_Mariem
 */
public class CreneauHoraire {
    private Long id;
    private String jour;
    private int heure_debut;
    private int heure_fin;
    private boolean etat;
    private User psy;

    public CreneauHoraire() {
    }

    public CreneauHoraire(String jour, int heure_debut, int heure_fin, boolean etat, User psy) {
        this.jour = jour;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.etat = etat;
        this.psy = psy;
    }

    public CreneauHoraire(Long id, String jour, int heure_debut, int heure_fin, boolean etat, User psy) {
        this.id = id;
        this.jour = jour;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.etat = etat;
        this.psy = psy;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public int getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(int heure_debut) {
        this.heure_debut = heure_debut;
    }

    public int getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(int heure_fin) {
        this.heure_fin = heure_fin;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public User getPsy() {
        return psy;
    }

    public void setPsy(User psy) {
        this.psy = psy;
    } 

    @Override
    public String toString() {
        return "CreneauHoraire{" + "id=" + id + ", jour=" + jour + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + ", etat=" + etat + '}';
    }
    
    
}
