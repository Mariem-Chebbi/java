/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.devapps.entity;

/**
 *
 * @author ala20
 */
public class Service {
   
   int id ;
   String libelle;
   String description;
   String icone;

   public Service() {
   }

   public Service(int id, String libelle, String description, String icone) {
      this.id = id;
      this.libelle = libelle;
      this.description = description;
      this.icone = icone;
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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getIcone() {
      return icone;
   }

   public void setIcone(String icone) {
      this.icone = icone;
   }

   @Override
   public String toString() {
      return "Service{" + "id=" + id + ", libelle=" + libelle + ", description=" + description + ", icone=" + icone + '}';
   }
   
   
   
}
