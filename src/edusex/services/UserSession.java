/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import edusex.entities.User;

    

import edusex.services.UserService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class UserSession {
     public static String userMail= "";
    public static String userString;
    public static Date userDate;
    private final UserService userService = new UserService() {};


    public void setUserId(String email,String mail)
    {
        if(Objects.equals(email, "")) return ;
        UserSession.userMail = email ;
        UserSession.userString=mail;
    }
    
    public String getUserEmail() {
        return userMail;
    }

    public void setUserEmail(String userIntEmail) {
    if (userIntEmail != null) {
        UserSession.userMail = userIntEmail;
    }
}
    

    public static Date getUserDate() {
        return userDate;
    }

    public static void setUserDate(Date userDate) {
        UserSession.userDate = userDate;
    }

    public static String getUserString() {
        return userString;
    }

    public static void setUserString(String userString) {
        UserSession.userString = userString;
    }
       
    public User getUser()
    {
        return userService.GetUserByMailSession(userMail);
    }

  


    }
  
   


    

    

