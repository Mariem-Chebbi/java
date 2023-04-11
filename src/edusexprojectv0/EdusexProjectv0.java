/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusexprojectv0;

import edusex.entities.Product;
import edusex.entities.Reservation;
import edusex.entities.blog;
import edusex.services.ServiceProduct;
import edusex.services.ServiceReservation;
import edusex.utils.MyDB;
import java.util.Date;

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
        
        Product p = new Product(47,"test2","test2",22,"test2");
       //log b = new blog(1,
        //rviceProduct sp = new ServiceProduct();
       //erviceReservation sr = new ServiceReservation();
        //sp.addProduct(p);
        //sp.removeProduct(p);
        //sp.updateProduct(p);
        //System.out.println(sp.showProduct());
        
        
        
        
       //ate d = new Date(121, 2, 26, 10, 30, 0);

       // Reservation r = new Reservation(2,2023-03-10,"en cours",10,22);
        //sr.addReservation(r);
       //ystem.out.println(sr.showReservation());
                }
    
}
