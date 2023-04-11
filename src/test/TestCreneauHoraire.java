/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.CreneauHoraire;
import entities.User;
import services.ServiceCreneauHoraire;
import utils.MyDB;

/**
 *
 * @author Chebbi_Mariem
 */
public class TestCreneauHoraire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyDB.getInstance();

        User u = new User(1L);

        //CreneauHoraire cr = new CreneauHoraire("Lundi", 8, 17, true, u);
        ServiceCreneauHoraire sc = new ServiceCreneauHoraire();
        
        CreneauHoraire cr = new CreneauHoraire("Lundi", 12, 17, true, u);
        
        
        //sc.ajouter(cr);
        sc.modifier(cr);
        //System.out.println(sc.afficher(1L));
        
        System.out.println(sc.getCreneauHoraire("lundi", 1l));
    }

}
