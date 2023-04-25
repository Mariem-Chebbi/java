/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author abbes
 */
public class Sponsor {
    private int id;
   private int id_event;

  private String nom_sponsor ;
  private String type ; 

    public Sponsor(int id, int id_event, String nom_sponsor, String type) {
        this.id = id;
        this.id_event = id_event;
        this.nom_sponsor = nom_sponsor;
        this.type = type;
    }

    public Sponsor(int id_event, String nom_sponsor, String type) {
        this.id_event = id_event;
        this.nom_sponsor = nom_sponsor;
        this.type = type;
    }

    public Sponsor(int id, int id_event) {
        this.id = id;
        this.id_event = id_event;
    }

    public Sponsor(String nom_sponsor, String type) {
        this.nom_sponsor = nom_sponsor;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_sponsor() {
        return nom_sponsor;
    }

    public void setNom_sponsor(String nom_sponsor) {
        this.nom_sponsor = nom_sponsor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", id_event=" + id_event + ", nom_sponsor=" + nom_sponsor + ", type=" + type + '}';
    }

 
  
  
   

  

   

   
 

}
