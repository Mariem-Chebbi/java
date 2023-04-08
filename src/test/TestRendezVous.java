/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.RendezVous;
import entities.User;
import java.sql.Date;
import java.time.LocalDate;
import services.ServiceRendezVous;
import utils.MyDB;

/**
 *
 * @author Chebbi_Mariem
 */
public class TestRendezVous {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyDB.getInstance();

        User psy = new User(1L);        
        User client = new User(2L);
        
        ServiceRendezVous service = new ServiceRendezVous();
        
        RendezVous rdv = new RendezVous(new Date(123, 3, 10), 12, client, psy);
        RendezVous rdv2 = new RendezVous(220L,new Date(123, 3, 10), 12, client, psy);
        service.ajouter(rdv);
        service.supprimer(rdv2);
        System.out.println(service.afficher(1L));
        
        System.out.println(service.historique(1L));
        
        

        
    }
    
}
