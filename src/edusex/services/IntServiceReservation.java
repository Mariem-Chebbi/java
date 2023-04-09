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
    
    public interface IntServiceReservation<R> {
    
    public abstract void addReservation(R r);
    //public abstract void AjouterPersonne2(T t);
    public void removeReservation(R r);
    public void updateReservation(R r);
    public ArrayList<R> showReservation();
    
}
