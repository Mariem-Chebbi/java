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
public class Centre {
   int id ;
   String nom_social;
    String aderesse;
     String ville;
     String logo;
     String tel1;
     String tel2;
     String description;

   public Centre() {
   }

   public Centre(int id, String nom_social, String aderesse, String ville, String logo, String tel1, String tel2, String description) {
      this.id = id;
      this.nom_social = nom_social;
      this.aderesse = aderesse;
      this.ville = ville;
      this.logo = logo;
      this.tel1 = tel1;
      this.tel2 = tel2;
      this.description = description;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNom_social() {
      return nom_social;
   }

   public void setNom_social(String nom_social) {
      this.nom_social = nom_social;
   }

   public String getAderesse() {
      return aderesse;
   }

   public void setAderesse(String aderesse) {
      this.aderesse = aderesse;
   }

   public String getVille() {
      return ville;
   }

   public void setVille(String ville) {
      this.ville = ville;
   }

   public String getLogo() {
      return logo;
   }

   public void setLogo(String logo) {
      this.logo = logo;
   }

   public String getTel1() {
      return tel1;
   }

   public void setTel1(String tel1) {
      this.tel1 = tel1;
   }

   public String getTel2() {
      return tel2;
   }

   public void setTel2(String tel2) {
      this.tel2 = tel2;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
   public String toString() {
      return "Centre{" + "id=" + id + ", nom_social=" + nom_social + ", aderesse=" + aderesse + ", ville=" + ville + ", logo=" + logo + ", tel1=" + tel1 + ", tel2=" + tel2 + ", description=" + description + '}';
   }
             
             
             
             
             
             
}
