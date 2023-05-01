/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Chebbi_Mariem
 */
public class MyJob implements Job  {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Reminder: You have an appointment tomorrow.");
        WhatsAppSender.sendWhatsApp("Bonjour, Nous voulions juste vous rappeler notre rendez-vous prévu pour demain.");
        SMSSender.sendSMS("Bonjour, Nous voulions juste vous rappeler notre rendez-vous prévu pour demain.", "20744656");
    }
}
