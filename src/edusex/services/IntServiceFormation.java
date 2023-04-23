/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.Formation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IntServiceFormation<F> {
    public abstract void addFormation(F f);
    //public abstract void AjouterPersonne2(T t);
    public void removeFormation(F f);
    public void updateFormation(F f);
    public ArrayList<F> showFormation();
    public void exportToExcel(List<Formation> formations, String fileName)throws IOException;
}
