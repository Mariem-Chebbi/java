/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.services;

import edusex.entities.Formation;
import edusex.utils.MyDB;
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


/**
 *
 * @author ASUS
 */
public class ServiceFormation implements IntServiceFormation<Formation>{
    Statement Ste;
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
    Sheet sheet = workbook.createSheet("Formations");

    // créer une ligne pour les en-têtes de colonne
    Row headerRow = sheet.createRow(0);
    // ajouter les en-têtes de colonne
    String[] headers = {"Nom", "Durée", "Prix"};
    for (int i = 0; i < headers.length; i++) {
      Cell cell = headerRow.createCell(i);
      cell.setCellValue(headers[i]);
    }

    // ajouter les données de la collection dans les lignes suivantes
    int rowIndex = 1;
    for (Formation formation : formations) {
      Row row = sheet.createRow(rowIndex++);
      row.createCell(0).setCellValue(formation.getLibelle());
      row.createCell(1).setCellValue(formation.getDescription());
      row.createCell(2).setCellValue(formation.getDateFormatiob());
      row.createCell(2).setCellValue(formation.getImage());
    }

    // enregistrer le classeur Excel dans un fichier
    FileOutputStream outputStream = new FileOutputStream(fileName);
    workbook.write(outputStream);
    workbook.close();
    outputStream.close();
  }
    
    }
    

