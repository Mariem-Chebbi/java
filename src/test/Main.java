/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entity.Evenement;
import Entity.Sponsor;

import Service.IService_evenement;
import Service.ServiceEvenement;
import Service.Iservice_sponsor;
import Service.ServiceSponsor;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author achrafzribi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
             

       //  Covoiturage c3 = new Covoiturage(3,1 ,"rades ", "gahzela",java.sql.Date.valueOf("2023-02-16"),5,7,"mohamed","24076282");
               Evenement c3 = new Evenement(5 ,"sex ", "gahzela","hhhhhhh",java.sql.Date.valueOf("2023-02-16"),java.sql.Date.valueOf("2023-02-16"));

       Sponsor c8 = new Sponsor(c3.getId(),"ssss", "sssss");

        ServiceEvenement ser = new ServiceEvenement();
                ServiceSponsor serv = new ServiceSponsor();

   
/*try {
            ser.ajouter(c3);
     } catch (SQLException ex) {
         System.out.println(ex);
      }*/
        
         //  does_reservation_cov_exist(3, 827);
       /*  
         List<reservation_covoiturage> l1 = null;
         try {
                    l1 =     serv.find_reservation_cov_of_user_rech(827,3);
             System.out.println("HAHAHAHAH");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         if (l1.size() == 0 )  { 
          System.out.println("zzaaa")    ; 
         }
        
          l1.forEach(e -> {
            System.out.println(e);
        });*/
        /* try {
        serv.ajouter_sponsor(c8);
 } catch (SQLException ex) {
            System.out.println(ex);
                        System.out.println("letsgoooo");

        } */

         //Covoiturage c2 = new Covoiturage(7,1 ,"rades ", "gahzela","22-02-2021"  ,5,5);

        
     /*   try {
            ser.ajouter(c2);
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
*/
        try{
                    ser.update(c3);
        }catch (SQLException ex) {
            System.out.println(ex);}
       
      /*try{
                ser.supprime(827);
       }catch (SQLException ex) {
          System.out.println(ex);
}*/
       
        
/*List<Sponsor> l1 = null;

       try {
            l1 = serv.readAll_sponsor();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> {
            System.out.println(e);
        });
      */

    }


}
