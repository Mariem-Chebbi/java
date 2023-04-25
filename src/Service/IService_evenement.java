/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Evenement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sanabenfadhel
 * @param <Evenement>
 */
public interface IService_evenement<Evenement> {

    boolean ajouter(Evenement t) throws SQLException;

    void update(Evenement t) throws SQLException;
   List<Evenement> sortbydate() throws SQLException;


    boolean supprime(int t) throws SQLException;
    List<Evenement> readAll() throws SQLException;

    List<Evenement> findById(int id) throws SQLException;
    
}
