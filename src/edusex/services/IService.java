/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import java.util.List;

/**
 *
 * @author Chebbi_Mariem
 */
public interface IService<Obj> {

    public void ajouter(Obj o);

    public void modifier(Obj o);

    public void supprimer(Obj o);

    public List<Obj> afficher(int id);

}
