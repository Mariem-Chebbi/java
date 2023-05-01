/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.blog;
import edusex.entities.categorie;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public interface Iblog<R> {
                     
    
    public abstract void addBlog(blog r);
    //public abstract void AjouterPersonne2(T t);
    public void removeBlog(blog r);
    public void updateBlog(blog r);
    public ArrayList<R> showBlog();
    
}
