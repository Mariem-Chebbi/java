/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date ; 
/**
 *
 * @author abbes
 */
public class Evenement {
    
   private String nom_event ;
   private String lieu_event ;
  private String description ;
    private int id;
   
     Date date_debut,date_fin; 
  // Date date= new Date();
 //SimpleDateFormat formatter= new SimpleDateFormat("yyyy/mm/dd");
 // private String date_covoiturage= formatter.format(date);

    public Evenement( int id,String nom_event, String lieu_event, String description, Date date_debut, Date date_fin) {
        this.nom_event = nom_event;
        this.lieu_event = lieu_event;
        this.description = description;
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Evenement() {
    }

    public Evenement(String nom_event, String lieu_event, String description, Date date_debut, Date date_fin) {
        this.nom_event = nom_event;
        this.lieu_event = lieu_event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Evenement{" + "nom_event=" + nom_event + ", lieu_event=" + lieu_event + ", description=" + description + ", id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

  
    
   
    
     

   

   
   

 
    
    
}
