/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.categorie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
 */
public interface Icategorie {
                public boolean ajoutercategorie(categorie c)throws SQLException;
        public boolean updatecategorie(categorie c)throws SQLException;
    public boolean suprimecategorie(categorie c)throws SQLException;
    public List<categorie> getAll()throws SQLException;
    
    
}
