/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
    
    public interface IntServiceProduct<P> {
    
    public abstract void addProduct(P p);
    //public abstract void AjouterPersonne2(T t);
    public void removeProduct(P p);
    public void updateProduct(P p);
    public ArrayList<P> showProduct();
    
}
