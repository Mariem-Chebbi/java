/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;
import edusex.entities.Formation;
import edusex.entities.Inscription;
import edusex.utils.MyDB;
import facebook4j.FacebookFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import facebook4j.*;
import com.restfb.Version;
import java.text.SimpleDateFormat;
//import facebook4j.auth.AccessToken;

/**
 *
 * @author ASUS
 */
public class ServiceFormation implements IntServiceFormation<Formation>{
    Statement Ste;
    Statement SteF;
    Connection con;

    public ServiceFormation() {
        
        con = MyDB.getInstance().getcon();
    }
    
    @Override
    public void addFormation(Formation f) {
        
        try {
             LocalDate dateAujourdhui = LocalDate.now();
        if (f.getDateFormatiob().toLocalDate().isBefore(dateAujourdhui)) {
            // Affichage d'un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La date de formation doit être supérieure à la date d'aujourd'hui.");
            alert.showAndWait();
            return; // On sort de la fonction sans effectuer l'ajout
            }
            
            
            PreparedStatement pre = con.prepareStatement("INSERT INTO `edusex`.`Formation` (`libelle`,`description`,`image`,`date_formation`) VALUES (?,?,?,?);");
            pre.setString(1, f.getLibelle());
            pre.setString(2,f.getDescription());
            pre.setString(3, f.getImage());
            //pre.setInt(3, f.getCertificationId());
            pre.setDate(4,f.getDateFormatiob());
           
            pre.executeUpdate();
                    
            facebookShare("Nouvelle formation ajoutée : " + f.getLibelle() + "\n" + f.getDescription() + "\n" + f.getDateFormatiob());
            
        } catch (SQLException ex) {
            System.out.println("err"+ex.getMessage());
        }
    }

    @Override
    public void removeFormation(Formation f) {
         try {
            Ste= con.createStatement();
       
        String req = "DELETE FROM `edusex`.`Formation` WHERE `Formation`.`id`='"+f.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
    }

    @Override
    public void updateFormation(Formation f) {
         try {
            Ste= con.createStatement();
       
        String req = "UPDATE `edusex`.`Formation`SET`libelle`='"+f.getLibelle()+"',`description`='"+f.getDescription()+"',`image`='"+f.getImage()+"' WHERE `Formation`.`id`='"+f.getId()+"';";
            Ste.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }

    }

    @Override
    public ArrayList<Formation> showFormation() {
        ArrayList<Formation> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Formation`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                //int certificationId = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                String image = res.getString(5);
                Date dateFormation = res.getDate(4);
                
                
                Formation f =new Formation(id, libelle, description,image, dateFormation);
                pers.add(f);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }

    @Override
    public void exportToExcel(List<Formation> formations, String fileName)throws IOException {
       XSSFWorkbook workbook = new XSSFWorkbook();
    // créer une nouvelle feuille dans le classeur
    
      int cellWidth = 5000;
      
      
      Sheet sheet = workbook.createSheet("Formations");
      
      sheet.setColumnWidth(0, cellWidth);
    // créer une ligne pour les en-têtes de colonne
    Row headerRow = sheet.createRow(0);
    // ajouter les en-têtes de colonne
    String[] headers = {"Libelle", "Description", "getDateFormatiob"};
    for (int i = 0; i < headers.length; i++) {
    sheet.setColumnWidth(i, cellWidth);
      Cell cell = headerRow.createCell(i);
      cell.setCellValue(headers[i]);
    }

    // ajouter les données de la collection dans les lignes suivantes
    int rowIndex = 1;
    for (Formation formation : formations) {

      Row row = sheet.createRow(rowIndex++);
      row.createCell(0).setCellValue(formation.getLibelle());
      row.createCell(1).setCellValue(formation.getDescription());
      
      Date currentDate = formation.getDateFormatiob();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String excelDate = dateFormat.format(currentDate);
      
      row.createCell(2).setCellValue(excelDate);
      
    }

    // enregistrer le classeur Excel dans un fichier
    FileOutputStream outputStream = new FileOutputStream(fileName);
    workbook.write(outputStream);
    workbook.close();
    outputStream.close();
  }
    @Override
    public ArrayList<Formation> showInscriptionUser(int idUser) {
          ArrayList<Inscription> inscriptionUser = new ArrayList<>();
              Inscription i=null;
            ArrayList<Formation> formationUser = new ArrayList<>();  
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Inscription` WHERE `Inscription`.`id_personnel_id`='"+idUser+"';";
            System.out.println(req);
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                int idPersonnel = res.getInt(2);
                int idFormation = res.getInt(3);
                int Present = res.getInt(4);
                if (Present==1){                   
                    i =new Inscription(id,  idPersonnel, idFormation, "Present");
                    inscriptionUser.add(i);
                }else{
                 i =new Inscription(id,  idPersonnel, idFormation, "Absent");
                inscriptionUser.add(i);
                }
                System.out.println(inscriptionUser);
            }
              for (int j=0;j<inscriptionUser.size();j++){
                  SteF= con.createStatement();
                  String reqF = "SELECT * FROM `edusex`.`Formation`WHERE `Formation`.`id`='"+inscriptionUser.get(j).getIdFormation()+"';";
                  ResultSet resF= SteF.executeQuery(reqF);
                  while(resF.next()){
                    int idF = resF.getInt(1);
                    //int certificationId = res.getInt(1);
                    String libelle = resF.getString(2);
                    String description = resF.getString(3);
                    String image = resF.getString(5);
                    Date dateFormation = resF.getDate(4);
                Formation f =new Formation(idF, libelle, description,image, dateFormation);
                formationUser.add(f);
                }                            
            }  
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return formationUser;
    }
        
    public void facebookShare(String message)throws FacebookException{
          // Create a new instance of Facebook
              String accessToken = "EAAUPYSqh2qoBAKF0nYajnTZBT9YagZBBUxXRxBxMRZBJbsWD5kCUVtYAyLRnbgL1QcoJZCRywOZCCQHHjG4A7RtTuTlK9JUXZAYyT7dMaSZBkoa733MAOVbPOj3vtWBVEoM9ApCZBSSSylDxt33uGfjKZA7fGSP00Xs34YTuz5bI6HMvMaphCm3WqjCak3HwLs906tnQZCc0JarQZDZD";
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_12_0);
        
        FacebookType publishMessageResponse =
                facebookClient.publish("me/feed", FacebookType.class,
                        Parameter.with("message", message));
        
        System.out.println("Published message ID: " + publishMessageResponse.getId());
    }
        public ArrayList<Formation> showFormationTrier() {
        ArrayList<Formation> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `edusex`.`Formation` ORDER BY `Formation`.`libelle` ASC";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int id = res.getInt(1);
                //int certificationId = res.getInt(1);
                String libelle = res.getString(2);
                String description = res.getString(3);
                String image = res.getString(5);
                Date dateFormation = res.getDate(4);
                
                
                Formation f =new Formation(id, libelle, description,image, dateFormation);
                pers.add(f);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    }
    
    
    
   
    

