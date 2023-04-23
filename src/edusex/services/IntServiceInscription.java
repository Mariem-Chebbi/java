/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface IntServiceInscription<I>  {
    public abstract void addInscription(I i);
    public void removeInscription(I i);
    public void updateInscription(I i);
    public ArrayList<I> showInscription();
    public ArrayList<I> showInscriptionUser();
}
