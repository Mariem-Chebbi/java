/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import java.util.List;
import java.util.stream.Collectors;

import edusex.entities.Role;
import edusex.entities.User;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import edusex.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javax.imageio.ImageIO;
import edusex.utils.BCryptPass;

/**
 *
 * @author user
 */
public class UserService implements InterfaceService<User> {
          public static UserSession userSession;

   
    Statement Ste;
    Connection con;
    private ResultSet rs ;
    private PreparedStatement pst ;

    public UserService() {
       
        con = MyDB.getInstance().getcon();
    }
       @Override
    public void Ajouter(User u) {
    
        try {
            Statement st;
            st = con.createStatement();
            String query = "INSERT INTO user( email,password,nom,prenom,num_tel,ville,date_naissance,image,roles) "
                    + "VALUES ('" + u.getEmail()+ "','" + u.getPassword()+ "','" + u.getNom() + "','" + u.getPrenom()+ "','" + u.getNum_tel() + "','" + u.getVille() + "','" + u.getDate_naissance() + "','" + u.getImage() + "','" + u.getRoles() + "')";
            st.executeUpdate(query);
            System.out.println("user ajouter avec success");
   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   
    @Override
    public  void Ajouter2(User u){

     try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`user` (`email`,`password`, `nom`, `prenom`,`num_tel`, `ville`, `date_naissance`, `image`,`roles`) VALUES (?,?,?,?,?,?,?,?,?,?);");
            pre.setString(1, u.getEmail());
           pre.setString(3,BCryptPass.cryptMDP(u.getPassword()));
            pre.setString(3, u.getNom());
            pre.setString(4, u.getPrenom());
            pre.setInt(5, u.getNum_tel());

            pre.setString(6, u.getVille());

            pre.setObject(7, u.getDate_naissance());

            pre.setObject(8, u.getImage());
           
            pre.setString(9, u.getRoles().toString());

            pre.executeUpdate();
                   
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        } 
    }
      @Override
 public void supprimer(User u) {
    String requete = "DELETE FROM user WHERE id = ?";
    try {
        PreparedStatement ps = con.prepareStatement(requete);
        ps.setInt(1, u.getId());
        int n = ps.executeUpdate();
        if (n >= 1) {
            System.out.println("Suppression réussie");
        }
    } catch (SQLException ex) {
        System.out.println("Problème de requête de suppression : " + ex.getMessage());
    }
}




@Override
public void modifier( User u) {
    String requete = "UPDATE `user` SET `email` = ?,  `password` = ?, `nom` = ?, `prenom` = ?, `num_tel` = ?, `ville` = ?, `date_naissance` = ?, `image` = ?, `roles` = ?"
            + " WHERE `user`.`id` = ? ;";
    try {
        PreparedStatement ps = con.prepareStatement(requete);
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getNom());
        ps.setString(4, u.getPrenom());
        ps.setInt(5, u.getNum_tel());
        ps.setString(6, u.getVille());
        ps.setDate(7,new java.sql.Date(u.getDate_naissance().getTime()));  
        ps.setString(8, (String) u.getImage());
        ps.setString(9, u.getRoles().toString());
        ps.setInt(10, u.getId());
        int n = ps.executeUpdate();
        if (n >= 1) {
            System.out.println("l'utilisateur a été modifié avec succès");
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   
}


     @Override
    public List<User> afficher() throws SQLException{
    ArrayList<User> user = new ArrayList<>();
     try {
        Ste = con.createStatement();
        String req = "SELECT * FROM `edusex`.`user`";
        ResultSet res = Ste.executeQuery(req);
        while(res.next()){
              User u = new User();
            u.setId(res.getInt("id"));
            u.setEmail(res.getString("email"));
            u.setPassword(res.getString("password"));
            u.setNom(res.getString("nom"));
            u.setPrenom(res.getString("prenom"));
            u.setNum_tel(res.getInt("num_tel"));
            u.setVille(res.getString("ville"));
            u.setDate_naissance(res.getDate("date_naissance"));
            u.setImage(res.getString("image"));
            u.setRoles(Role.valueOf(res.getString("roles")));
            user.add(u);
           

         
        }
    } catch (SQLException ex) {
        System.out.println("err" + ex.getMessage());
    }
    return user;
} 
     
     public boolean checklogin(String email, String password) {
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM `user` WHERE `email`='" + email + "' AND `password`='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
     
    @Override
  
    public List<User> findById(int id) {
       List<User> user = new ArrayList<>();
    String requete = "select * from user where id = ?";
    try {
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setNum_tel(rs.getInt("num_tel"));
            u.setVille(rs.getString("ville"));
            u.setDate_naissance(rs.getDate("date_naissance"));
            u.setImage(rs.getString("image"));
            u.setRoles(Role.valueOf(rs.getString("roles")));
            user.add(u);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;
    }
  

public User findByEmail(String email) {
User u = null;
try {
Statement st = con.createStatement();
String query = "SELECT * FROM user WHERE email='" + email + "'";
ResultSet rs = st.executeQuery(query);
if (rs.next()) {
u = new User();
u.setId(rs.getInt("id"));
u.setEmail(rs.getString("email"));
u.setPassword(rs.getString("password"));


u.setNom(rs.getString("nom"));
u.setPrenom(rs.getString("prenom"));

            u.setNum_tel(rs.getInt("num_tel"));
u.setVille(rs.getString("ville"));
u.setDate_naissance(rs.getDate("date_naissance"));
u.setImage(rs.getString("image"));
u.setRoles(Role.valueOf(rs.getString("roles")));
}
} catch (SQLException ex) {
Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
}
return u;
}
    public List<User> findByEmaill(String email) throws SQLException {
        List<User> users = afficher();
        List<User> resultat = users.stream().filter(user -> email.equals(user.getEmail())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }
   
 
  public User GetUserByMailSession(String mail) {
        User user = null;


        String pass = "";
        try {
            String requete = "Select id,email,roles, password, nom, prenom,  num_tel,ville, date_naissance, image  from user where email = ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, mail);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getString("id"),
                                                mail,
                        Role.valueOf(rs.getString("roles")),
pass,
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        
                        rs.getInt("num_tel"),
                                                rs.getString("ville"),

                        rs.getDate("date_naissance"),
                                                rs.getString("image")

                        
                );

                System.out.println(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (StringIndexOutOfBoundsException ex) {
            /*Login_pageController lc = new Login_pageController();
            new animatefx.animation.Shake(lc.getPasswordtxt()).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            lc.getPasswordtxt().setEffect(in);
             */
        }
        return user;
    }
   public void updatemdp(String email, String mdp) throws SQLException {
         Statement stm = con.createStatement();
        String query = "UPDATE user SET password= '"+mdp+"' WHERE email='"+email+"'";
        stm.executeUpdate(query); 
        
    }
   public Boolean VerifyUserByEmail(String email) throws SQLException {
		User u = new User();
		//Boolean found = false;  Statement stm = conn.createStatement();
            
                 Statement stm = con.createStatement();

            
		
		String query = "select * from user where email = '" + email + "'";
		try {
			 ResultSet rst = stm.executeQuery(query);
			if (rst.next()){ 
			return true;
                        }
		} catch (SQLException ex) {
                         System.out.println("erreur" + ex.getMessage());
                }
       return false;
	};
       
           public boolean updateByemail(String email, String mdp) throws SQLException {
        String qry = "UPDATE user SET  password = '"+mdp+"' WHERE email = '"+email+"'";
         Statement stm = con.createStatement();
        try {
            if (stm.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
                                   

        }
        return false;
    }
 public User login(String email,String mdp) throws SQLException{
        return afficher().stream().filter(
                u->u.getEmail().equals(email)
        ).filter(u->BCryptPass.checkPass(mdp, u.getPassword())).findFirst().orElse(null);
        
    }
}