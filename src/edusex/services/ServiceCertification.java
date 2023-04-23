 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.Certification;
import edusex.entities.Formation;
import edusex.entities.Inscription;

import edusex.utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class ServiceCertification implements IntServiceCertification<Certification>{
Statement Ste;
Statement SteF;
Statement SteC;
    Connection con;

    public ServiceCertification() {
        
        con = MyDB.getInstance().getcon();
    }
    

    @Override
    public void addCertification(Certification C) {
         try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`Certification` (`id_formation_id`,`image`) VALUES (?,?);");
            
            pre.setInt(1, C.getFormationId());       
            pre.setString(2,C.getImage());
            
                     
            pre.executeUpdate();     
            
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        }
    }

    @Override
    public void removeCertification(Certification C) {
        try {
            Ste= con.createStatement();
       
        String req = "DELETE FROM `edusex`.`Certification` WHERE `Certification`.`id`='"+C.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    @Override
    public void updateCertification(Certification C) {
       try {
            Ste= con.createStatement();
       
        String req = "UPDATE `edusex`.`Certification`SET`id_formation_id`='"+C.getFormationId()+"',`image`='"+C.getImage()+"' WHERE `Certification`.`id`='"+C.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    @Override
    public ArrayList<Certification> showCertification() {
         ArrayList<Certification> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Certification`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                int formationId = res.getInt(2);
                String image = res.getString(3);

                
                
                Certification c =new Certification(formationId,  id, image);
                pers.add(c);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
    public Formation getClientById(int clientId) {
    Formation f = null;
    
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Formation` WHERE `Formation`.`id`='"+clientId+"'; ";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                //int certificationId = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                String image = res.getString(5);
                Date dateFormation = res.getDate(4);
                
                
                 f =new Formation(id, libelle, description,image, dateFormation);
                
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return f;
    }

    @Override
    public ArrayList<Certification> showCertificationUser() {
        String p="";
        ArrayList<Certification> pers = new ArrayList<>();
        ArrayList<Formation> formations = new ArrayList<>();
        ArrayList<Inscription> in = new ArrayList<>();
        try {
            Ste= con.createStatement();            
            String req = "SELECT * FROM `edusex`.`Inscription` "
                + "WHERE `Inscription`.`id_personnel_id`='"+2+"' "
                + "AND `Inscription`.`present`='"+1+"' ; ";
            ResultSet res= Ste.executeQuery(req);            
            while(res.next()){              
                int id = res.getInt(1);
                int idPersonnel = res.getInt(2);
                int idFormation = res.getInt(2);
                int present = res.getInt(req);
                if (present == 0){p="Absent";}else{p="Present";}
                String Present = p;                
                Inscription c =new Inscription( id, idPersonnel, idFormation, Present);
                in.add(c);
            }
            
            for (int i=0;i<in.size();i++){
            SteF= con.createStatement();            
            String req1 = "SELECT * FROM `edusex`.`Formation`"
                + "WHERE `Formation`.`id`='"+in.get(i).getIdFormation()+"' ; ";
            ResultSet res1= SteF.executeQuery(req1);            
            while(res1.next()){              
                int id = res1.getInt(1);
                String libelle = res1.getString(2);
                String description = res1.getString(3);
                String image = res1.getString(5);
                Date dateFormation = res1.getDate(4);               
                Formation f =new Formation(id, libelle, description,image, dateFormation);
                formations.add(f);
            }
            
            for (int j=0;j<formations.size();j++){
            SteC= con.createStatement();            
            String req2 = "SELECT * FROM `edusex`.`Certification`"
                + "WHERE `Certification`.`id_formation_id`='"+formations.get(i).getId()+"' ; ";
            ResultSet res2= SteC.executeQuery(req2);            
            while(res2.next()){              
                int id = res.getInt(1);
                int formationId = res2.getInt(2);
                String image = res2.getString(3);                
                Certification c =new Certification(formationId,  id, image);
                pers.add(c);
            }
            }
            
            
            }
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers; //To change body of generated methods, choose Tools | Templates.
    }
    
}
