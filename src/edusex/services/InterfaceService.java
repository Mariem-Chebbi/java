/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 * @param <U>

 */
public interface InterfaceService<U> {
    
    public abstract void Ajouter(U u);
    public abstract void Ajouter2(U u);
    public void supprimer (U u);
    public void modifier( U u);
    public List<U> afficher() throws SQLException ;;
    public List<U> findById(int id);
    
}
