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
public interface IntServiceCertification<C> {
        public abstract void addCertification(C C);
    //public abstract void AjouterPersonne2(T t);
    public void removeCertification(C C);
    public void updateCertification(C C);
    public ArrayList<C> showCertification();
        public ArrayList<C> showCertificationUser();

}
