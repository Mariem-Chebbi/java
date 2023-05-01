/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex_final_project;

import edusex.entities.Certification;
import edusex.services.ServiceCertification;
import edusex.utils.MyDB;

/**
 *
 * @author LENOVO
 */
public class EdusexProjectv0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyDB.getInstance();
        
        Certification c = new Certification();
        ServiceCertification sc = new ServiceCertification();
       //sc.showCertificationUser();
       System.out.println(sc.showCertificationUser());
        
        
//        Formation p = new Formation(14,"test3","test3","test2",d);
//        ServiceFormation sf = new ServiceFormation();
//        ServiceCertification sc = new ServiceCertification();
//        System.out.println(sc.showCertification());
        
        
        
        //sc.removeInscription(c);
        //sp.updateProduct(p);
        //System.out.println(sp.showProduct());
        //System.out.println(sc.showCertification());
        
        
        
      

        
        //sr.addReservation(r);
        
    }
    
}
 