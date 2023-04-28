/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Chebbi_Mariem
 */
import java.time.LocalDate;
import utils.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import services.ServiceRendezVous;
import utils.RappelRendezVous;

public class TestRappel {

    public static void main(String[] args) throws SchedulerException {
        LocalDate localDate = LocalDate.of(2023, 4, 24);
        services.ServiceRendezVous serv = new ServiceRendezVous();
        ServiceRendezVous.getReminderDate(localDate);
        //RappelRendezVous.reminder();
    }
}
